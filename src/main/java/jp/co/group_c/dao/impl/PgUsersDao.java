package jp.co.group_c.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.controller.form.SignInForm;
import jp.co.group_c.controller.form.SignUpForm;
import jp.co.group_c.controller.form.UserInfoForm;
import jp.co.group_c.dao.UsersDao;
import jp.co.group_c.entity.FavoriteCategory;
import jp.co.group_c.entity.Users;

@Repository
public class PgUsersDao implements UsersDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	// findByLoginIdAndPass
	private static final String USERS_SELECT_LOGINID_PASSWORD = "SELECT * FROM users INNER JOIN cities ON users.cities_id = cities.cities_id WHERE login_id = :loginId and password = :password";

	// findByLoginId
	private static final String USERS_SELECT_LOGINID = "SELECT * FROM users INNER JOIN cities ON users.cities_id = cities.cities_id WHERE login_id = :loginId";

	// insertUserInfo
	private static final String USERS_INSERT = "INSERT INTO users(user_name, cities_id, authority_id, login_id, password) VALUES (:userName, :citiesId, 2, :loginId, :password)";

	// findFavoriteCategory
	private static final String FAVORITRECATEGORY_SERECT_USERID = "SELECT * FROM favorite_category INNER JOIN category ON favorite_category.category_id = category.category_id WHERE user_id = :userId";

	// updateUserInfo
	private static final String USERS_UPDATE = "UPDATE users SET login_id = :loginId, user_name = :userName, cities_id = :citiesId WHERE user_id = :userId";
	private static final String FAVORITRECATEGORY_DELETE = "DELETE FROM favorite_category WHERE user_id = :userId";

	// insertUserInfo updateUserInfo
	private static final String FAVORITRECATEGORY_INSERT = "INSERT INTO favorite_category VALUES (:userId, :categoryId1), (:userId, :categoryId2), (:userId, :categoryId3)";

	public Users findByLoginIdAndPass(SignInForm form) {
		Users userInfo;
		try{
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("loginId", form.getLoginId());
			param.addValue("password", form.getPassword());
			userInfo = jdbcTemplate.queryForObject(USERS_SELECT_LOGINID_PASSWORD, param, new BeanPropertyRowMapper<Users>(Users.class));
		} catch (Exception e) {
			userInfo = null;
		}
		return userInfo;
	}

	public Users findByLoginId(String loginId) {
		Users userInfo;
		try{
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("loginId", loginId);
			userInfo = jdbcTemplate.queryForObject(USERS_SELECT_LOGINID, param, new BeanPropertyRowMapper<Users>(Users.class));
		} catch (Exception e) {
			userInfo = null;
		}
		return userInfo;
	}

	public List<FavoriteCategory> findFavoriteCategory(Integer userId) {
		List<FavoriteCategory> categoryList = null;
		try{
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("userId", userId);
			categoryList = jdbcTemplate.query(FAVORITRECATEGORY_SERECT_USERID, param, new BeanPropertyRowMapper<FavoriteCategory>(FavoriteCategory.class));
		} catch (Exception e) {
			categoryList = null;
		}
		return categoryList;
	}

	public void insertUserInfo(SignUpForm form) {

		MapSqlParameterSource param1 = new MapSqlParameterSource();
		param1.addValue("userName", form.getUserName());
		param1.addValue("citiesId", form.getCitiesId());
		param1.addValue("loginId", form.getLoginId());
		param1.addValue("password", form.getPassword());
		jdbcTemplate.update(USERS_INSERT, param1);

		MapSqlParameterSource param2 = new MapSqlParameterSource();
		param2.addValue("userId", ((findByLoginId(form.getLoginId())).getUserId()));
		param2.addValue("categoryId1", form.getCategoryId1());
		param2.addValue("categoryId2", form.getCategoryId2());
		param2.addValue("categoryId3", form.getCategoryId3());
		jdbcTemplate.update(FAVORITRECATEGORY_INSERT, param2);
	}

	public Users updateUserInfo(Integer userId, UserInfoForm form) {

		MapSqlParameterSource param1 = new MapSqlParameterSource();
		param1.addValue("userId", userId);
		param1.addValue("loginId", form.getLoginId());
		param1.addValue("userName", form.getUserName());
		param1.addValue("citiesId", form.getCitiesId());
		jdbcTemplate.update(USERS_UPDATE, param1);

		MapSqlParameterSource param2 = new MapSqlParameterSource();
		param2.addValue("userId", userId);
		jdbcTemplate.update(FAVORITRECATEGORY_DELETE, param2);

		MapSqlParameterSource param3 = new MapSqlParameterSource();
		param3.addValue("userId", userId);
		param3.addValue("categoryId1", form.getCategoryId1());
		param3.addValue("categoryId2", form.getCategoryId2());
		param3.addValue("categoryId3", form.getCategoryId3());
		jdbcTemplate.update(FAVORITRECATEGORY_INSERT, param3);

		return findByLoginId(form.getLoginId());
	}

}
