package jp.co.group_c.homeDao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.entity.Store;
import jp.co.group_c.entity.Users;
import jp.co.group_c.homeDao.HomeDao;

@Repository
public class HomeDaoImpl implements HomeDao{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	// 入力値の埋め込み
    MapSqlParameterSource param = new MapSqlParameterSource();

    //SQL
    private static final String NEW_ARRIVAL = "SELECT s.store_id, store_name, cities_name, hyouka, paths FROM store AS s\n"
    											 + "JOIN store_category AS sc ON s.store_id = sc.store_id\n"
    											 + "JOIN category AS c ON sc.category_id = c.category_id\n"
    											 + "JOIN cities AS city ON s.cities_id = city.cities_id\n"
    											 + "JOIN review AS r ON s.store_id = r.store_id\n"
    											 + "JOIN images AS i ON s.store_id = i.store_id\n"
    											 + "GROUP BY s.store_id, store_name, cities_name, hyouka, paths\n"
    											 + "ORDER BY s.store_id DESC LIMIT 5";

    private static final String MAIN_CATEGORY = "SELECT s.store_id, category_name\n"
    											   + "FROM store_category as s\n"
    											   + "JOIN category as c on s.category_id = c.category_id\n";

    private static final String USERS = "SELECT * FROM users";

    private static final String RECOMMEND = "SELECT DISTINCT s.store_id, store_name, cities_name, hyouka, paths\n"
    									       + "FROM store AS s\n"
    									       + "JOIN store_category AS sc ON s.store_id = sc.store_id\n"
    									       + "JOIN category AS c ON sc.category_id = c.category_id\n"
    									       + "JOIN cities AS city ON s.cities_id = city.cities_id\n"
    									       + "JOIN review AS r ON s.store_id = r.store_id\n"
    									       + "JOIN images AS i ON s.store_id = i.store_id\n"
    									       + "WHERE sc.category_id IN (SELECT fc.category_id\n"
    									       + "FROM users AS u\n"
    									       + "JOIN favorite_category AS fc ON :userId = fc.user_id)\n"
    									       + "LIMIT 3";

    private static final String PLAN = "SELECT s.store_id, store_name, category_name, cities_name, hyouka, paths\n"
    									 + "FROM store AS s\n"
    									 + "JOIN store_category AS sc ON s.store_id = sc.store_id\n"
    									 + "JOIN category AS c ON sc.category_id = c.category_id\n"
    									 + "JOIN cities AS city ON s.cities_id = city.cities_id\n"
    									 + "JOIN review AS r ON s.store_id = r.store_id\n"
    									 + "JOIN images AS i ON s.store_id = i.store_id\n"
    									 + "WHERE sc.category_id NOT IN (SELECT fc.category_id\n"
    									 + "FROM users AS u\n"
    									 + "JOIN favorite_category AS fc ON :userId = fc.user_id)\n"
    									 + "LIMIT 1";

    //新着機能メソッド
	@Override
	public List<Store> newArrival() {
		String newArrival = NEW_ARRIVAL;
		List<Store> newArrivalList = jdbcTemplate.query(newArrival, new BeanPropertyRowMapper<Store>(Store.class));
		return newArrivalList;
	}

	//カテゴリ表示メソッド
	@Override
	public List<Store> mainCategory(String storeName) {
		String mainCategory = MAIN_CATEGORY;
		List<Store> mainCategoryList = jdbcTemplate.query(mainCategory, new BeanPropertyRowMapper<Store>(Store.class));
		return mainCategoryList;
	}

	//ユーザー情報の取得メソッド
	@Override
	public List<Users> users(){
		String users = USERS;
		List<Users> usersList = jdbcTemplate.query(users, new BeanPropertyRowMapper<Users>(Users.class));
		return usersList;
	}

	//おすすめ表示メソッド
	@Override
	public List<Store> recommend(int userId) {
		String recommend = RECOMMEND;
		param.addValue("userId", userId);
		List<Store> recommendList = jdbcTemplate.query(recommend, param, new BeanPropertyRowMapper<Store>(Store.class));
		return recommendList;
	}

	//新しい提案メソッド
	@Override
	public List<Store> plan(int userId) {
		String plan = PLAN;
		param.addValue("userId", userId);
		List<Store> planList = jdbcTemplate.query(plan, param, new BeanPropertyRowMapper<Store>(Store.class));
		return planList;
	}

}
