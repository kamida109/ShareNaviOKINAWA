package jp.co.group_c.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.group_c.entity.Users;
import jp.co.group_c.mypage.dao.MyPageDao;

@Service
public class MyPageService {

	@Autowired
	MyPageDao myPageDao;

	public Users checkLoginId(String loginId) {
		return myPageDao.findByLoginId(loginId);
	}

	/*
	public void update(Users user) {
		usersDao.updateUser(user);
	}
	*/

}
