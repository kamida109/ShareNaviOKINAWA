package jp.co.group_c.mypage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.entity.Users;

@Repository
public class MyPageDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SQL_SELECT_LOGINID = "SELECT * FROM users WHERE login_id = :loginId";

	public Users findByLoginId(String id) {
		Users userInfo;
		try{
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("loginId", id);
			userInfo = jdbcTemplate.queryForObject(SQL_SELECT_LOGINID, param, new BeanPropertyRowMapper<Users>(Users.class));
		} catch (Exception e) {
			userInfo = null;
		}
		return userInfo;
	}

}
