package jp.co.group_c.category_process.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.category_process.dao.CategoryProcessDao;
import jp.co.group_c.entity.Category;

@Repository
public class CategoryProcessDaoImpl implements CategoryProcessDao {

	private static final String SELECT_MAIN_CATEGORY = 		"SELECT * FROM category WHERE main_category IS NULL";
	private static final String SELECT_SUB_CATEGORY= 		"SELECT * FROM category WHERE main_category = :main_category";
	private static final String SELECT_ALL_SUB_CATEGORY=	"SELECT * FROM category WHERE main_category IS NOT NULL";
	private static final String SELECT_CATEGORY = 			"SELECT * FROM category ";

	private static final String INSERT_SUB_CATEGORY = 	"INSERT INTO category (category_name, main_category) VALUES(:category_name, :main_category)";
	private static final String INSERT_MAIN_CATEGORY  = 	"INSERT INTO category (category_name) VALUES(:category_name)";

	private static final String UPDATE_CATEGORY = 			"UPDATE category SET category_name = :category_name "+
																"WHERE category_id = :category_id";

	private static final String DELETE_CATEGORY =			"DELETE FROM category WHERE category_id = :category_id";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Category> selectMainCategory() {
		String sql = SELECT_MAIN_CATEGORY;
		List<Category> cateList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));

		return cateList;
	}

	@Override
	public List<Category> selectAllChildCategory() {
		String sql = SELECT_ALL_SUB_CATEGORY;
		List<Category> cateList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));

		return cateList;
	}

	//親カテゴリ追加
	@Override
	public void insertCategory(String categoryName) {
		String sql = INSERT_MAIN_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("category_name", categoryName);
		jdbcTemplate.update(sql, param);

	}
	//子カテゴリ追加
	@Override
	public void insertCategory(String categoryName, Integer mainCategoryId) {
		String sql = INSERT_SUB_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("category_name", categoryName);
		param.addValue("main_category", mainCategoryId);
		jdbcTemplate.update(sql, param);

	}
	//カテゴリの編集
	@Override
	public void updateCategory(Integer categoryId, String categoryName) {
		String sql = UPDATE_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("category_id", categoryId);
		param.addValue("category_name", categoryName);
		jdbcTemplate.update(sql, param);
	}
	//カテゴリの削除
	@Override
	public void deleteCategory(Integer categoryId) {
		String sql = DELETE_CATEGORY;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("category_id", categoryId);
		jdbcTemplate.update(sql, param);
	}

	//入力したカテゴリが存在するか確認
	@Override
	public boolean findExistCategory(String pCateName) {
		String sql = SELECT_CATEGORY + "WHERE category_name = :category_name";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("category_name", pCateName);
		List<Category> cateList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Category>(Category.class));

		return !cateList.isEmpty() ? true : false;
	}

	@Override
	public Category findCategory(String cateName) {
		String sql = SELECT_CATEGORY + "WHERE category_name = :category_name";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("category_name", cateName);
		List<Category> cateList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Category>(Category.class));

		return !cateList.isEmpty() ? cateList.get(0) : null;
	}
	@Override
	public Category findCategory(Integer cateId) {
		String sql = SELECT_CATEGORY + "WHERE category_id = :category_id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("category_id", cateId);
		List<Category> cateList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Category>(Category.class));

		return !cateList.isEmpty() ? cateList.get(0) : null;
	}



}
