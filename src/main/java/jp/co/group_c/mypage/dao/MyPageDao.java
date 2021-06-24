package jp.co.group_c.mypage.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import jp.co.group_c.controller.form.UserInfoForm;
import jp.co.group_c.entity.FavoriteCategory;
import jp.co.group_c.entity.Store;
import jp.co.group_c.entity.Users;

public class MyPageDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;


	private static final String STORE_SELECT_FAVORITRE = "SELECT s.store_id, store_name, cities_name, hyouka, paths FROM store AS s\r\n"
															+ "JOIN store_category AS sc ON s.store_id = sc.store_id\r\n"
															+ "JOIN category AS c ON sc.category_id = c.category_id\r\n"
															+ "JOIN cities AS city ON s.cities_id = city.cities_id\r\n"
															+ "JOIN review AS r ON s.store_id = r.store_id\r\n"
															+ "JOIN images AS i ON s.store_id = i.store_id\r\n"
															+ "WHERE s.store_id IN(SELECT store_id FROM favorite WHERE user_id = :userId)\r\n"
															+ "GROUP BY s.store_id, store_name, cities_name, hyouka, paths";
	private static final String STORE_SELECT_REVIEW = "SELECT s.store_id, store_name, cities_name, hyouka, paths FROM store AS s\r\n"
															+ "JOIN store_category AS sc ON s.store_id = sc.store_id\r\n"
															+ "JOIN category AS c ON sc.category_id = c.category_id\r\n"
															+ "JOIN cities AS city ON s.cities_id = city.cities_id\r\n"
															+ "JOIN review AS r ON s.store_id = r.store_id\r\n"
															+ "JOIN images AS i ON s.store_id = i.store_id\r\n"
															+ "WHERE r.user_id = :userId\r\n"
															+ "GROUP BY s.store_id, store_name, cities_name, hyouka, paths";

	//お気に入り表示メソッド
	public List<Store> favoriteStore(int userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		List<Store> storeList = jdbcTemplate.query(STORE_SELECT_FAVORITRE, param, new BeanPropertyRowMapper<Store>(Store.class));
		return storeList;
	}

	//レビュー履歴表示メソッド
	public List<Store> reviewStore(int userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		List<Store> storeList = jdbcTemplate.query(STORE_SELECT_REVIEW, param, new BeanPropertyRowMapper<Store>(Store.class));
		return storeList;
	}


	private static final String USERS_SELECT_LOGINID = "SELECT * FROM users INNER JOIN cities ON users.cities_id = cities.cities_id WHERE login_id = :loginId";

	private static final String FAVORITRECATEGORY_SERECT_USERID = "SELECT * FROM favorite_category INNER JOIN category ON favorite_category.category_id = category.category_id WHERE user_id = :userId";

	private static final String USERS_UPDATE = "UPDATE users SET login_id = :loginId, user_name = :userName, cities_id = :citiesId WHERE user_id = :userId";
	private static final String FAVORITRECATEGORY_DELETE = "DELETE FROM favorite_category WHERE user_id = :userId";
	private static final String FAVORITRECATEGORY_INSERT = "INSERT INTO favorite_category VALUES (:userId, :categoryId1), (:userId, :categoryId2), (:userId, :categoryId3)";

	public Users findByLoginId(String id) {
		Users userInfo;
		try{
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("loginId", id);
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
