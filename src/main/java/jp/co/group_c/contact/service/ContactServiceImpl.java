package jp.co.group_c.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.group_c.contact.dao.ContactDao;
import jp.co.group_c.contact.entity.Contact;



@Service
public class ContactServiceImpl implements ContactService{

	@Autowired//Daoのインスタンスつくる
	private ContactDao contactDao;

	//メソッド
	public void contactInsert(Contact contact) {
		contactDao.contactInsert(contact);
	}
}
