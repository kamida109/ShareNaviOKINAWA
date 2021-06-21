package jp.co.group_c.contact.dao;

import java.util.List;

import jp.co.group_c.contact.entity.Contact;

public interface ContactDao {
	//問い合わせ登録用（一般ユーザー）
	public void contactInsert(Contact contact);

	//問い合わせ内容一覧表示
	public List<Contact> findAll();

	//問い合わせ内容詳細の取得
	public Contact find(Integer contactId);
}
