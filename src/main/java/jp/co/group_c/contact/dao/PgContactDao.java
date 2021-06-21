package jp.co.group_c.contact.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

	//問い合わせ内容一覧表示
	private static final String SELECT_FIND_ALL = "SELECT contact_id, user_name,"
			+ "contact_category_id, flag FROM contact"
			+ "INNER JOIN users ON users.user_id = contact.user_id"
			+ "ORDER BY flag ASC";

	//問い合わせ内容詳細の取得
	private static final String SELECT_FIND ="SELECT contact_id, user_name,"
			+ "contact_category_id, contents, flag FROM contact"
			+ "INNER JOIN users ON users.user_id = contact.user_id"
			+ "WHERE contact_id = :contactId";


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
	//問い合わせ内容一覧表示
	@Override
	public List<Contact> findAll(){
		String sql = SELECT_FIND_ALL;

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	//問い合わせ内容詳細取得
	public Contact find(Integer contactId) {
		String sql = SELECT_FIND;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("contactId", contactId);

		Contact contactDetails = (Contact) jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Contact>());

		return contactDetails;

	}


}
