package jp.co.group_c.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.controller.form.UserInfoForm;
import jp.co.group_c.dao.UserDao;
import jp.co.group_c.entity.FavoriteCategory;
import jp.co.group_c.entity.Users;

@Repository
public class PgUserDao implements UserDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String USERS_SELECT_LOGINID = "SELECT * FROM users INNER JOIN cities ON users.cities_id = cities.cities_id WHERE login_id = :loginId";

	private static final String FAVORITRECATEGORY_SERECT_USERID = "SELECT user_id, category.category_id, category_name, main_category FROM favorite_category INNER JOIN category ON favorite_category.category_id = category.category_id WHERE user_id = :userId";

	private static final String USERS_UPDATE = "UPDATE users SET login_id = :loginId, user_name = :userName, cities_id = :citiesId WHERE user_id = :userId";
	private static final String FAVORITRECATEGORY_DELETE = "DELETE FROM favorite_category WHERE user_id = :userId";
	private static final String FAVORITRECATEGORY_INSERT = "INSERT INTO favorite_category VALUES (:userId, :categoryId1), (:userId, :categoryId2), (:userId, :categoryId3)";

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

	public Users updateUserInfo(Integer userId, UserInfoForm userInfo) {

		MapSqlParameterSource param1 = new MapSqlParameterSource();
		param1.addValue("userId", userId);
		param1.addValue("loginId", userInfo.getLoginId());
		param1.addValue("userName", userInfo.getUserName());
		param1.addValue("citiesId", userInfo.getCitiesId());
		jdbcTemplate.update(USERS_UPDATE, param1);

		MapSqlParameterSource param2 = new MapSqlParameterSource();
		param2.addValue("userId", userId);
		jdbcTemplate.update(FAVORITRECATEGORY_DELETE, param2);

		MapSqlParameterSource param3 = new MapSqlParameterSource();
		param3.addValue("userId", userId);
		param3.addValue("categoryId1", userInfo.getCategoryId1());
		param3.addValue("categoryId2", userInfo.getCategoryId2());
		param3.addValue("categoryId3", userInfo.getCategoryId3());
		jdbcTemplate.update(FAVORITRECATEGORY_INSERT, param3);

		return findByLoginId(userInfo.getLoginId());
	}

}
