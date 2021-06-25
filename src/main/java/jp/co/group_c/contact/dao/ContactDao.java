package jp.co.group_c.contact.dao;

import java.util.List;

import jp.co.group_c.contact.InqueryForm;
import jp.co.group_c.contact.entity.Contact;
import jp.co.group_c.contact.entity.UserManagement;

public interface ContactDao {
	//問い合わせ登録用（一般ユーザー）
	public void contactInsert(InqueryForm contact);

	//問い合わせ内容一覧表示
	public List<Contact> findAll();

	//未解決のみ取得
	public List<Contact> findUnsolved();

	//解決のみ取得
	public List<Contact> findSolved();

	//問い合わせ内容詳細の取得
	public Contact find(Integer contactId);

	//解決ボタン→flagをtrueに
	public void flagUpdate (Integer contactId);


	//IDか名前検索時（ユーザー管理画面で）
	public List<UserManagement> managementFind(UserManagement userManagement);

	//ユーザー管理画面での削除用
	public String managementDelete(Integer userId);
}
