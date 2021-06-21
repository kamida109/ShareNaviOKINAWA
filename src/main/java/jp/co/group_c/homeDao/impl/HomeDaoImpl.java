package jp.co.group_c.homeDao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.entity.Store;
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

    private static final String MAIN_CATEGORY = "select category_name\n"
    											   + "from store as s\r\n"
    											   + "join store_category as sc on s.store_id = sc.store_id\n"
    											   + "join category as c on sc.category_id = c.category_id\n"
    											   + "where store_name = :storeName";

    //新着機能メソッド
	@Override
	public List<Store> newArrival() {
		String newArrival = NEW_ARRIVAL;
		List<Store> newArrivalList = jdbcTemplate.query(newArrival, new BeanPropertyRowMapper<Store>(Store.class));
		return newArrivalList;
	}

	//カテゴリ表示用メソッド
	@Override
	public List<Store> mainCategory(String storeName) {
		String mainCategory = MAIN_CATEGORY;
		param.addValue("storeName", storeName);

		List<Store> mainCategoryList = jdbcTemplate.query(mainCategory, param, new BeanPropertyRowMapper<Store>(Store.class));
		return mainCategoryList;
	}

}
