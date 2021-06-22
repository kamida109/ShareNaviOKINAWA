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

@Repository
public class AddStoreDaoImpl implements AddStoreDao {

	private static final String SELECT_ALL_CITIES = "SELECT * FROM cities ";
	private static final String SELECT_MAIN_CATEGORY = "SELECT * FROM category WHERE main_category IS NULL";
	private static final String SELECT_CHILD_CATEGORY= "SELECT * FROM category WHERE main_category = :main_category";
	private static final String SELECT_ALL_CHILD_CATEGORY= "SELECT * FROM category WHERE main_category IS NOT NULL";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<City> allCity() {

		String sql = SELECT_ALL_CITIES;
		List<City> cityList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<City>(City.class));

		return cityList;

	}



	//親カテゴリのみ抽出
	public List<Category> selectMainCategory() {

		String sql = SELECT_MAIN_CATEGORY;
		List<Category> cateList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));

		return cateList;
	}


	//子カテゴリのみ抽出
	public List<Category> selectAllChildCategory() {

		String sql = SELECT_ALL_CHILD_CATEGORY;
		List<Category> cateList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));

		return cateList;
	}




	//親カテゴリに対応した子カテゴリのみ抽出
	public List<Category> selectChildCategory(int mainCategory) {

		String sql = SELECT_CHILD_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("main_category", mainCategory);
		List<Category> cateList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));

		return cateList.isEmpty() ? null : cateList;
	}

}
