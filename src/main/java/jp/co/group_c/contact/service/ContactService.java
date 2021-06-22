package jp.co.group_c.contact.service;

import java.util.List;

import jp.co.group_c.contact.entity.Contact;
import jp.co.group_c.contact.entity.UserManagement;

public interface ContactService {

	//メソッド
	//問い合わせ内容登録用（一般ユーザー）
	public void contactInsert(Contact contact);

	//問い合わせ内容一覧表示用（管理者）
	public List<Contact> findAll();

	//問い合わせ内容詳細の取得.引数は検索する条件を入れる。WHERE句
	public Contact find (Integer contactId);


	//IDか名前検索時（ユーザー管理画面で）
	public List<UserManagement> managementFind(UserManagement userManagement);

	//ユーザーIDで削除（ユーザー管理画面で）
	//public void managementDelete(Integer userId);
}
