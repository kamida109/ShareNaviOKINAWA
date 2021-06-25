package jp.co.group_c.dao;

import java.util.List;

import jp.co.group_c.controller.form.SignInForm;
import jp.co.group_c.controller.form.SignUpForm;
import jp.co.group_c.controller.form.UserInfoForm;
import jp.co.group_c.entity.FavoriteCategory;
import jp.co.group_c.entity.Users;

public interface UsersDao {

	public Users findByLoginIdAndPass(SignInForm form);

	public Users findByLoginId(String loginId);

	public List<FavoriteCategory> findFavoriteCategory(Integer userId);

	public void insertUserInfo(SignUpForm form);

	public Users updateUserInfo(Integer userId, UserInfoForm form);

}
