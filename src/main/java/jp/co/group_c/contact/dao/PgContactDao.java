package jp.co.group_c.contact.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.contact.entity.Contact;

@Repository
public class PgContactDao implements ContactDao{
	//問い合わせ内容登録用のSQL
	private static final String CONTACT_INSERT =
			"INSERT INTO contact(user_id, contact_category_id, contents, flag)"
			+ "VALUES(:userId, :contactCategoryId, :contents, :flag)";

	//プレースホルダーを使うときはこの型のクラス使う
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	//問い合わせ内容登録
	public void contactInsert(Contact contact) {
		String sql = CONTACT_INSERT;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", contact.getUserId());
		param.addValue("contactCategoryId", contact.getContactCategoryId());
		param.addValue("contents", contact.getContents());
		param.addValue("flag", contact.isFlag());

		jdbcTemplate.update(sql, param);
	}

}
