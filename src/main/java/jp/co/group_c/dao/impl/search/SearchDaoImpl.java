package jp.co.group_c.dao.impl.search;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.dao.SearchDao;
import jp.co.group_c.entity.Category;
import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.Store;

@Repository
public class SearchDaoImpl implements SearchDao{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	HttpSession session;

	// 入力値の埋め込み
    MapSqlParameterSource param = new MapSqlParameterSource();

    private static final String SQL_CITEIS = "SELECT * FROM cities";

    private static final String SQL_CATEGORY = "SELECT * FROM category";

	private static final String SQL_SEARCH = "SELECT store_name, category_name, cities_name, avg(hyouka) AS hyouka, string_agg(paths, '')"
												+ "FROM store AS s\n"
												+ "JOIN store_category AS sc ON s.store_id = sc.store_id\n"
												+ "JOIN category AS c ON sc.category_id = c.category_id\n"
												+ "JOIN cities AS city ON s.cities_id = city.cities_id\n"
												+ "JOIN review AS r ON s.store_id = r.store_id\n"
												+ "JOIN images AS i ON s.store_id = i.store_id\n"
												+ "WHERE 1=1";

	// 市町村テーブル全件取得
	@Override
	public List<Cities> cities() {
		List<Cities> citiesList = jdbcTemplate.query(SQL_CITEIS, new BeanPropertyRowMapper<Cities>(Cities.class));
		return citiesList;
	}

	// カテゴリメインカテゴリ取得
	@Override
	public List<Category> mainCategory() {
		String mainCategory = SQL_CATEGORY + "\nWHERE main_category IS null";
		List<Category> mainCategoryList = jdbcTemplate.query(mainCategory, new BeanPropertyRowMapper<Category>(Category.class));
		return mainCategoryList;
	}

	// カテゴリメインカテゴリ取得
	@Override
	public List<Category> subCategory(Integer mainId) {
		String subCategory = SQL_CATEGORY;
		if(mainId==null) {
			subCategory = SQL_CATEGORY + "\nWHERE main_category IS NOT null";
		}else {
			subCategory = SQL_CATEGORY + "\nWHERE main_category IS NOT null AND main_category = :mainId";
		}
		param.addValue("mainId", mainId);
		List<Category> subCategoryList = jdbcTemplate.query(subCategory, param, new BeanPropertyRowMapper<Category>(Category.class));
		return subCategoryList;
	}

	// 店舗検索
	@Override
	public List<Store> storeSearch(String storeName, Integer categoryId, Integer cityId, boolean hyouka) {
		String storeSearch = SQL_SEARCH;

		List<Store> storeList = new ArrayList<Store>();

		// 全入力
		if(!storeName.isEmpty() && categoryId!=null && cityId!=null) {
			storeSearch += " AND store_name = :storeName AND c.category_id = :category AND city.cities_id = :city\n"
						+ "GROUP BY store_name, category_name, cities_name";
			param.addValue("storeName", storeName);
			param.addValue("category", categoryId);
			param.addValue("city", cityId);
		}

		// 店舗名+カテゴリ
		if(!storeName.isEmpty() && categoryId!=null && cityId==null) {
			storeSearch += " AND store_name = :storeName AND c.category_id = :category\n"
						+ "GROUP BY store_name, category_name, cities_name";
			param.addValue("storeName", storeName);
			param.addValue("category", categoryId);
		}

		// 店舗名+市町村
		if(!storeName.isEmpty() && categoryId==null && cityId!=null) {
			storeSearch += " AND store_name = :storeName AND city.cities_id = :city\n"
						+ "GROUP BY store_name, category_name, cities_name";
			param.addValue("storeName", storeName);
			param.addValue("city", cityId);
		}

		// カテゴリ+市町村
		if(storeName.isEmpty() && categoryId!=null && cityId!=null) {
			storeSearch += " AND c.category_id = :category AND city.cities_id = :city\n"
						+ "GROUP BY store_name, category_name, cities_name";
			param.addValue("category", categoryId);
			param.addValue("city", cityId);
		}

		// 店舗名のみ
		if(!storeName.isEmpty() && categoryId==null && cityId==null) {
			storeSearch += " AND store_name = :storeName\n"
						+ "GROUP BY store_name, category_name, cities_name";
			param.addValue("storeName", storeName);
		}

		// カテゴリのみ
		if(storeName.isEmpty() && categoryId!=null && cityId==null) {
			storeSearch += " AND c.category_id = :category\n"
						+ "GROUP BY store_name, category_name, cities_name";
			param.addValue("category", categoryId);
		}

		// 市町村のみ
		if(storeName.isEmpty() && categoryId==null && cityId!=null) {
			storeSearch += " AND city.cities_Id = :city\n"
						+ "GROUP BY store_name, category_name, cities_name";
			param.addValue("city", cityId);
		}

		// 評価3以上
		if(hyouka) {
			storeSearch += "\nHAVING avg(hyouka) >= 3";
		}

		storeList = jdbcTemplate.query(storeSearch, param, new BeanPropertyRowMapper<Store>(Store.class));
		return storeList;

	}

	// あいまい検索
	@Override
	public List<Store> partStoreSearch(String storeName, boolean hyouka) {
		String partSearch = SQL_SEARCH + "AND store_name LIKE :storeName\n"
							+ "GROUP BY store_name, category_name, cities_name";
		param.addValue("storeName", storeName);

		if(hyouka) {
			partSearch += "\n HAVING avg(hyouka) >= 3";
		}

		List<Store> partStore = jdbcTemplate.query(partSearch, param, new BeanPropertyRowMapper<Store>(Store.class));;

		return partStore;
	}

}