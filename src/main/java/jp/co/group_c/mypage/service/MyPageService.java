package jp.co.group_c.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.group_c.controller.form.UserInfoForm;
import jp.co.group_c.dao.StoreDao;
import jp.co.group_c.dao.UsersDao;
import jp.co.group_c.entity.FavoriteCategory;
import jp.co.group_c.entity.Store;
import jp.co.group_c.entity.Users;

@Service
public class MyPageService {

	@Autowired
	StoreDao storeDao;

	@Autowired
	UsersDao usersDao;

	public List<Store> favoriteStore(Integer userId) {
		return storeDao.favoriteStore(userId);
	}

	public List<Store> reviewStore(Integer userId) {
		return storeDao.reviewStore(userId);
	}

	public Users checkLoginId(String loginId) {
		return usersDao.findByLoginId(loginId);
	}

	public List<FavoriteCategory> findFavoriteCategory(Integer userId) {
		return usersDao.findFavoriteCategory(userId);
	}

	public Users updateUserInfo(Integer userId, UserInfoForm userInfo) {
		return usersDao.updateUserInfo(userId, userInfo);
	}
}
