package jp.co.group_c.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.dao.StoreDao;
import jp.co.group_c.entity.Store;

@Repository
public class PgStoreDao implements StoreDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String STORE_SELECT_FAVORITRE = "SELECT s.store_id, store_name, cities_name, avg(hyouka) AS hyouka, paths FROM store AS s\r\n"
															+ "JOIN store_category AS sc ON s.store_id = sc.store_id\r\n"
															+ "JOIN category AS c ON sc.category_id = c.category_id\r\n"
															+ "JOIN cities AS city ON s.cities_id = city.cities_id\r\n"
															+ "JOIN review AS r ON s.store_id = r.store_id\r\n"
															+ "JOIN images AS i ON s.store_id = i.store_id\r\n"
															+ "WHERE s.store_id IN(SELECT store_id FROM favorite WHERE user_id = :userId)\r\n"
															+ "GROUP BY s.store_id, store_name, cities_name, paths";

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
		return storeList.isEmpty() ? null : storeList;
	}

	//レビュー履歴表示メソッド
	public List<Store> reviewStore(int userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		List<Store> storeList = jdbcTemplate.query(STORE_SELECT_REVIEW, param, new BeanPropertyRowMapper<Store>(Store.class));
		return storeList.isEmpty() ? null : storeList;
	}

}
