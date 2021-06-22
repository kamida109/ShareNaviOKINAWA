package jp.co.group_c.contact.dao;

import java.util.List;

import jp.co.group_c.contact.entity.Contact;
import jp.co.group_c.contact.entity.UserManagement;

public interface ContactDao {
	//問い合わせ登録用（一般ユーザー）
	public void contactInsert(Contact contact);

	//問い合わせ内容一覧表示
	public List<Contact> findAll();

	//問い合わせ内容詳細の取得
	public Contact find(Integer contactId);

	//IDか名前検索時（ユーザー管理画面で）
	public List<UserManagement> managementFind(UserManagement userManagement);

	//ユーザー管理画面での削除用
	//public void managementDelete(Integer userId);
}
