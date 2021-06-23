package jp.co.group_c.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.group_c.contact.dao.ContactDao;
import jp.co.group_c.contact.entity.Contact;
import jp.co.group_c.contact.entity.UserManagement;



@Service
public class ContactServiceImpl implements ContactService{

	@Autowired//Daoのインスタンスつくる
	private ContactDao contactDao;

	//メソッド
	//問い合わせ内容登録用（一般ユーザー）
	public void contactInsert(Contact contact) {
		contactDao.contactInsert(contact);
	}
	//問い合わせ内容一覧表示。全検索
	public List<Contact> findAll(){
		return contactDao.findAll();
	}
	//問い合わせ内容詳細の取得
	public Contact find (Integer contactId) {
		return contactDao.find(contactId);
	}

	//ユーザー管理画面での検索用
	public List<UserManagement> managementFind(UserManagement userManagement){
		return contactDao.managementFind(userManagement);
	}
	//ユーザー管理画面での削除用
	public String managementDelete(Integer userId) {
		return contactDao.managementDelete(userId);
	}
}
