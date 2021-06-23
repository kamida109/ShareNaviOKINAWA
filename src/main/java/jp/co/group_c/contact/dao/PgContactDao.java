package jp.co.group_c.contact.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.contact.entity.Contact;
import jp.co.group_c.contact.entity.UserManagement;
import jp.co.group_c.contact.util.ParamUtil;

@Repository
public class PgContactDao implements ContactDao{
	//問い合わせ内容登録用のSQL
	private static final String CONTACT_INSERT =
			"INSERT INTO contact(user_id, contact_category_id, contents, flag)"
			+ "VALUES(:userId, :contactCategoryId, :contents, :flag)";

	//問い合わせ内容一覧表示
	private static final String FIND_ALL = "SELECT contact_id, user_name, contact_category_id, flag FROM contact"
			+ " INNER JOIN users ON users.user_id = contact.user_id ORDER BY flag ASC";

	//問い合わせ内容詳細の取得
	private static final String SELECT_FIND ="SELECT contact_id, user_name,"
			+ " contact_category_id, contents, flag FROM contact"
			+ " INNER JOIN users ON users.user_id = contact.user_id"
			+ " WHERE contact_id = :contactId";

	//解決ボタンおしたとき、問い合わせ内容削除＆flagがtrueになるようにアップデート
	private static final String FLAG_UPDATE = "UPDATE contact SET flag = true"
			+ " WHERE contact_id = :contactId";


	//IDと名前検索時（全件取得）
	private static final String SELECT_FIND_ALL = "SELECT user_id, user_name, login_id"
			+ " FROM users ORDER BY user_id ASC";

	//IDと名前検索時
	private static final String FINDBY_ID_OR_NAME = "SELECT user_id, user_name, login_id"
			+ " FROM users WHERE ";

	//ユーザー管理画面での削除用
	private static final String GET_NAME = "SELECT user_name FROM users WHERE user_id = :userId";
	private static final String DELETE = "DELETE FROM users WHERE user_id = :userId";


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
	//@Override
	public List<Contact> findAll(){
		String sql = FIND_ALL;

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	//問い合わせ内容詳細取得
	public Contact find(Integer contactId) {
		String sql = SELECT_FIND;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("contactId", contactId);

		Contact contactDetails = (Contact) jdbcTemplate.queryForObject(sql, param, new BeanPropertyRowMapper<Contact>(Contact.class));

		return contactDetails;
	}

	//解決ボタン→flagをtrueに
	public void flagUpdate(Integer contactId) {
		String sql = FLAG_UPDATE;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("contactId", contactId);

		jdbcTemplate.update(sql, param);
	}



	//IDと名前検索未入力時
	public List<UserManagement> managementFindAll(){
		String sql = SELECT_FIND_ALL;

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserManagement>(UserManagement.class));
	}


    //IDと名前検索時
	public List<UserManagement> managementFind(UserManagement userManagement){
		//未入力時、全検索（Entityのメソッド呼ぶ）
		if (userManagement == null || userManagement.isEmptyCondition()) {
			return managementFindAll();
		}

		List<String> condition = new ArrayList<String>();
		MapSqlParameterSource param = new MapSqlParameterSource();

		//値チェックのためDBからゲットする
		Integer userId = userManagement.getUserId();
		String userName = userManagement.getUserName();

		//conditionとparamに追加
		if(userId != null) {
			condition.add("user_id= :userId");
			param.addValue("userId", userId);
		}
			System.out.println(userId);

		if(!ParamUtil.isNullOrEmpty(userName)) {
			condition.add("user_name= :userName");
			param.addValue("userName", userName);
		}
			System.out.println(userName);

		String whereString = String.join(" AND ", condition.toArray(new String[] {}));

		String sql = FINDBY_ID_OR_NAME + whereString;

			System.out.println(sql);

		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<UserManagement>(UserManagement.class));
	}

	//ユーザー管理画面での削除用
	public String managementDelete(Integer userId) {
		String selectSql = GET_NAME;
		String deleteSql = DELETE;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userName", userId);
		param.addValue("userId", userId);

		String getName = jdbcTemplate.queryForObject(selectSql, param, String.class);
		jdbcTemplate.update(deleteSql, param);

		return getName;
	}


}
