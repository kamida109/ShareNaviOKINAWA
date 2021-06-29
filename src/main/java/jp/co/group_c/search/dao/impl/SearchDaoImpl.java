package jp.co.group_c.search.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.entity.Category;
import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.Store;
import jp.co.group_c.search.dao.SearchDao;
import jp.co.group_c.update.entity.Favorite;
import jp.co.group_c.update.entity.Review;

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

	private static final String SQL_SEARCH = "SELECT DISTINCT s.store_id, store_name, cities_name, avg(hyouka) AS hyouka\n"
												+ "FROM store AS s\n"
												+ "JOIN store_category AS sc ON s.store_id = sc.store_id\n"
												+ "JOIN category AS c ON sc.category_id = c.category_id\n"
												+ "JOIN cities AS city ON s.cities_id = city.cities_id\n"
												+ "JOIN review AS r ON s.store_id = r.store_id\n"
												+ "WHERE 1=1";

	private static final String STORE_CATEGORY = "SELECT sc.store_id, category_name\n"
													+ "FROM store_category AS sc\n"
													+ "JOIN category AS c ON sc.category_id = c.category_id";

	private static final String STORE_DITAILS = "SELECT DISTINCT s.store_id, store_name, business_hours, s.cities_id, address, tel, cities_name,"
													+ " avg(hyouka) as hyouka \n"
													+ "FROM store AS s\n"
													+ "JOIN cities AS city ON s.cities_id = city.cities_id\n"
													+ "LEFT JOIN review AS r ON s.store_id = r.store_id\n"
													+ "WHERE s.store_id = :storeId\n";

	private static final String NEW_REVIEW ="UPDATE review\n"
												+ "SET review = :review\n"
												+ "WHERE review_id = :reviewId";

	private static final String STORE_DELETE = "DELETE FROM store WHERE store_id = :storeId";

	private static final String FAVORITE_STORE ="SELECT * FROM favorite";

	private static final String INSERT_REVIEW = "INSERT INTO review(store_id, user_id, review, review_date)\n"
													+ "VALUES (:storeId, :userId, :review, CURRENT_DATE)";

	private static final String GET_REVIEW = "SELECT * FROM review AS r\n"
												+ "JOIN users AS u ON r.user_id = u.user_id\n"
												+ "WHERE store_id = :storeId";

	// 市町村テーブル全件取得
	@Override
	public List<Cities> cities() {
		List<Cities> citiesList = jdbcTemplate.query(SQL_CITEIS, new BeanPropertyRowMapper<Cities>(Cities.class));
		return citiesList;
	}

	// カテゴリメインカテゴリ取得
	@Override
	public List<Category> mainCategory() {
		String mainCategory = SQL_CATEGORY + "\nWHERE main_category IS null ORDER BY category_id";
		List<Category> mainCategoryList = jdbcTemplate.query(mainCategory, new BeanPropertyRowMapper<Category>(Category.class));
		return mainCategoryList;
	}

	// 子カテゴリ取得
	@Override
	public List<Category> subCategory(Integer mainId) {
		String subCategory = SQL_CATEGORY;
		subCategory = SQL_CATEGORY + "\nWHERE main_category IS NOT null AND main_category = :mainId";

		param.addValue("mainId", mainId);
		List<Category> subCategoryList = jdbcTemplate.query(subCategory, param, new BeanPropertyRowMapper<Category>(Category.class));
		return subCategoryList;
	}

	// 店舗検索
	@Override
	public List<Store> storeSearch(String storeName, Integer subCategory, Integer cityId, boolean hyouka) {
		String storeSearch = SQL_SEARCH;

		List<Store> storeList = new ArrayList<Store>();

		// 全入力
		if(!storeName.isEmpty() && subCategory!=null && cityId!=null) {
			System.out.println("完全一致検索");
			storeSearch += " AND store_name = :storeName AND c.category_id = :subCategory AND city.cities_id = :city\n";
			param.addValue("storeName", storeName);
			param.addValue("subCategory", subCategory);
			param.addValue("city", cityId);
		}

		// 店舗名+カテゴリ
		if(!storeName.isEmpty() && subCategory!=null && cityId==null) {
			System.out.println("店舗名+カテゴリ");
			storeSearch += " AND store_name = :storeName AND c.category_id = :subCategory\n";
			param.addValue("storeName", storeName);
			param.addValue("subCategory", subCategory);
		}

		// 店舗名+市町村
		if(!storeName.isEmpty() && subCategory==null && cityId!=null) {
			System.out.println("店舗名+市町村");
			storeSearch += " AND store_name = :storeName AND city.cities_id = :city\n";
			param.addValue("storeName", storeName);
			param.addValue("city", cityId);
		}

		// カテゴリ+市町村
		if(storeName.isEmpty() && subCategory!=null && cityId!=null) {
			System.out.println("カテゴリ+市町村");
			storeSearch += " AND c.category_id = :subCategory AND city.cities_id = :city\n";
			param.addValue("subCategory", subCategory);
			param.addValue("city", cityId);
		}

		// 店舗名+カテゴリ
		if(!storeName.isEmpty() && subCategory!=null && cityId==null) {
			System.out.println("店舗名+カテゴリ");
			storeSearch += " AND store_name = :storeName AND c.category_id = :subCategory\n";
			param.addValue("storeName", storeName);
			param.addValue("subCategory", subCategory);
		}

		// 店舗名のみ
		if(!storeName.isEmpty() && subCategory==null && cityId==null) {
			System.out.println("店舗名のみ");
			storeSearch += " AND store_name = :storeName\n";
			param.addValue("storeName", storeName);
		}

		// カテゴリのみ
		if(storeName.isEmpty() && subCategory!=null && cityId==null) {
			System.out.println("カテゴリのみ");
			storeSearch += " AND c.category_id = :subCategory\n";
			param.addValue("subCategory", subCategory);
		}

		// 市町村のみ
		if(storeName.isEmpty() && subCategory==null && cityId!=null) {
			System.out.println("市町村のみ");
			storeSearch += " AND city.cities_Id = :city\n";
			param.addValue("city", cityId);
		}

		storeSearch += "GROUP BY s.store_id, store_name, cities_name, hyouka";

		// 評価3以上
		if(hyouka) {
			storeSearch += "\nHAVING avg(hyouka) >= 3";
		}

//		storeSearch += "\nORDER BY city.cities_id";

		storeList = jdbcTemplate.query(storeSearch, param, new BeanPropertyRowMapper<Store>(Store.class));
		return storeList;

	}

	// 店舗のカテゴリを取得
	@Override
	public List<Store> storeCategory() {
		List<Store> storeCategory = jdbcTemplate.query(STORE_CATEGORY, new BeanPropertyRowMapper<Store>(Store.class));
		return storeCategory;
	}

	// あいまい検索
	@Override
	public List<Store> partStoreSearch(String storeName, boolean hyouka) {
		String partSearch = SQL_SEARCH + "AND store_name LIKE :storeName\n"
							+ "GROUP BY s.store_id, store_name, cities_name";
		param.addValue("storeName", storeName);

		if(hyouka) {
			partSearch += "\n HAVING avg(hyouka) >= 3";
		}

		List<Store> partStore = jdbcTemplate.query(partSearch, param, new BeanPropertyRowMapper<Store>(Store.class));

		return partStore;
	}

	// 店舗詳細用の検索メソッド
	@Override
	public List<Store> storeDitails(Integer id) {
		String ditails = STORE_DITAILS;
		param.addValue("storeId", id);
		ditails += "GROUP BY s.store_id, s.cities_id, cities_name";
		List<Store> storeDitails = jdbcTemplate.query(ditails, param, new BeanPropertyRowMapper<Store>(Store.class));

		return storeDitails;
	}

	// レビューの編集
	@Override
	public void reviewUpdate(Integer id, String review) {
		String strReview = NEW_REVIEW;

		param.addValue("reviewId", id);
		param.addValue("review", review);

		jdbcTemplate.update(strReview, param);
	}

	// レビュー削除
	@Override
	public void reviewDelete(Integer id) {
		String reviewDel = "DELETE FROM review WHERE review_id = :storeId";
		param.addValue("storeId", id);

		jdbcTemplate.update(reviewDel, param);
	}

	// 店舗の削除
	@Override
	public void storeDelete(Integer id) {
		String delete = STORE_DELETE;
		param.addValue("storeId", id);

		jdbcTemplate.update(delete, param);
	}


	// お気に入り店舗の取得
	@Override
	public List<Favorite> favoriteStore() {
		List<Favorite> favoriteStore = jdbcTemplate.query(FAVORITE_STORE, new BeanPropertyRowMapper<Favorite>(Favorite.class));
		return favoriteStore;
	}

	// レビューの追加
	@Override
	public void insertReview(Integer storeId, Integer userId, String review) {
		param.addValue("storeId", storeId);
		param.addValue("userId", userId);
		param.addValue("review", review);

		jdbcTemplate.update(INSERT_REVIEW, param);
	}

	// レビューの取得
	@Override
	public List<Review> reviewList(Integer storeId) {
		param.addValue("storeId", storeId);
		List<Review> reviewList = jdbcTemplate.query(GET_REVIEW, param, new BeanPropertyRowMapper<Review>(Review.class));
		return reviewList;
	}

}