package jp.co.group_c.dao;

import java.util.List;

import jp.co.group_c.controller.form.UserInfoForm;
import jp.co.group_c.entity.FavoriteCategory;
import jp.co.group_c.entity.Users;

public interface UserDao {

	public Users findByLoginId(String loginId);

	public List<FavoriteCategory> findFavoriteCategory(Integer userId);

	public Users updateUserInfo(Integer userId, UserInfoForm userInfo);

}
