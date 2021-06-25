package jp.co.group_c.add_store_dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.add_store_dao.AddStoreDao;
import jp.co.group_c.add_store_entity.Category;
import jp.co.group_c.add_store_entity.City;
import jp.co.group_c.add_store_entity.Store;

@Repository
public class AddStoreDaoImpl implements AddStoreDao {

	private static final String SELECT_CITIES = "SELECT * FROM cities ";
	private static final String WHERE_CITIES_ID = "WHERE cities_id = :cities_id";

	private static final String SELECT_MAIN_CATEGORY = "SELECT * FROM category WHERE main_category IS NULL";
	private static final String SELECT_CHILD_CATEGORY= "SELECT * FROM category WHERE main_category = :main_category";
	private static final String SELECT_ALL_CHILD_CATEGORY= "SELECT * FROM category WHERE main_category IS NOT NULL";
	private static final String SELECT_CATEGORY = "SELECT * FROM category ";

	private static final String INSERT_STORE = "INSERT INTO store (store_name, business_hours, cities_id, address, tel, insert_day) "+
												   "VALUES (:store_name, :business_hours, :cities_id, :address, :tel, CURRENT_DATE)";
	private static final String INSERT_IMAGES = "INSERT INTO images (store_id, paths) VALUES (:store_id, :paths)";

	private static final String INSERT_STORE_CATEGORY = "INSERT INTO store_category VALUES (:store_id, :category_id)";
	private static final String SELECT_STORE = "SELECT * FROM store ";
	private static final String WHERE_STORE = "WHERE store_name = :store_name "+
												  "AND cities_id = :cities_id ";


	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<City> allCity() {

		String sql = SELECT_CITIES;
		List<City> cityList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<City>(City.class));

		return cityList;

	}

	@Override
	public City findCity(Integer citiesId) {

		String sql = SELECT_CITIES + WHERE_CITIES_ID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("cities_id", citiesId);
		List<City> cityList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<City>(City.class));

		return cityList.isEmpty() ? null : cityList.get(0);

	}



	//親カテゴリのみ抽出
	@Override
	public List<Category> selectMainCategory() {

		String sql = SELECT_MAIN_CATEGORY;
		List<Category> cateList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));

		return cateList;
	}


	//子カテゴリのみ抽出
	@Override
	public List<Category> selectAllChildCategory() {

		String sql = SELECT_ALL_CHILD_CATEGORY;
		List<Category> cateList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));

		return cateList;
	}

	//カテゴリIDで検索
	@Override
	public Category searchCategory(Integer categoryId){

		String sql = SELECT_CATEGORY + "WHERE category_id = :category_id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("category_id", categoryId);
		List<Category> cateList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Category>(Category.class));

		return cateList.isEmpty() ? null : cateList.get(0);

	}
	//カテゴリ名で検索
	@Override
	public Category searchCategory(String categoryName){

		String sql = SELECT_CATEGORY + "WHERE category_name = :category_name";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("category_name", categoryName);
		List<Category> cateList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Category>(Category.class));

		return cateList.isEmpty() ? null : cateList.get(0);
	}


	//親カテゴリに対応した子カテゴリのみ抽出
	@Override
	public List<Category> selectChildCategory(int mainCategory) {

		String sql = SELECT_CHILD_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("main_category", mainCategory);
		List<Category> cateList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));

		return cateList.isEmpty() ? null : cateList;
	}


	//店舗検索
	@Override
	public Store findStore(String storeName, Integer citiesId) {
		String sql = SELECT_STORE + WHERE_STORE + "ORDER BY store_id DESC";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("store_name", storeName);
		param.addValue("cities_id", citiesId);
		List<Store> storeList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Store>(Store.class));

		return storeList.isEmpty() ? null : storeList.get(0);

	}

	//新規店舗追加
	@Override
	public void insertStore(String storeName, String businessHours, Integer citiesId, String address, String tel) {

		String sql = INSERT_STORE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("store_name", storeName);
		param.addValue("business_hours", businessHours);
		param.addValue("cities_id", citiesId);
		param.addValue("address", address);
		param.addValue("tel", tel);
		jdbcTemplate.update(sql, param);

	}


	//画像アップロード
	@Override
	public void insertImages(int storeId, String paths) {
		String sql = INSERT_IMAGES;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("store_id", storeId);
		param.addValue("paths", paths);
		jdbcTemplate.update(sql, param);
	}

	@Override
	public void addStoreCategory(int nowStoreId, Integer categoryId) {
		String sql = INSERT_STORE_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("store_id", nowStoreId);
		param.addValue("category_id", categoryId);
		jdbcTemplate.update(sql, param);
	}
}
