package jp.co.group_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.group_c.controller.form.SignInForm;
import jp.co.group_c.controller.form.SignUpForm;
import jp.co.group_c.controller.form.UserInfoForm;
import jp.co.group_c.dao.UsersDao;
import jp.co.group_c.entity.FavoriteCategory;
import jp.co.group_c.entity.Users;

@Service
public class UserInfoService {

	@Autowired
	UsersDao usersDao;

	// ログインチェック
	public Users signInCheck(SignInForm form) {
		return usersDao.findByLoginIdAndPass(form);
	}

	// 好きなカテゴリの取得
	public List<FavoriteCategory> findFavoriteCategory(Integer userId) {
		return usersDao.findFavoriteCategory(userId);
	}

	// loginIdの重複チェックなど
	public Users checkLoginId(String loginId) {
		return usersDao.findByLoginId(loginId);
	}

	// ユーザ情報の登録
	public void insertUserInfo(SignUpForm form) {
		usersDao.insertUserInfo(form);
	}

	// ユーザ情報の変更
	public Users updateUserInfo(Integer userId, UserInfoForm form) {
		return usersDao.updateUserInfo(userId, form);
	}

}
