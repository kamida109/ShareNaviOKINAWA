package jp.co.group_c.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.dao.SearchDao;
import jp.co.group_c.entity.Store;

@Repository
public class SearchDaoImpl implements SearchDao{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	HttpSession session;

	// 入力値の埋め込み
    MapSqlParameterSource param = new MapSqlParameterSource();

	private static final String searchStore = "";

	@Override
	public List<Store> storeSearch(String storeName, String category, String city, Integer hyouka) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
