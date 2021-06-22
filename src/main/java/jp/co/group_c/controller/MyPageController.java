package jp.co.group_c.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.group_c.controller.form.UserInfoForm;
import jp.co.group_c.entity.FavoriteCategory;
import jp.co.group_c.entity.Users;

@Controller
public class MyPageController {

    @Autowired
    HttpSession session;

	// マイページ画面
	@RequestMapping(value = "/my_page")
	public String jumpMyPage() {
		return "my_page";
	}

	// 登録情報画面に飛ぶ
	@RequestMapping(value = "/user_info")
	public String jumpUserInfo() {
		// テスト用
		Users signInUser = new Users(1, "groupC", 24, "南風原町", 1, "groupC", null);
		session.setAttribute("signInUser", signInUser);
		List<FavoriteCategory> favoriteList = new ArrayList<FavoriteCategory>();
		favoriteList.add(new FavoriteCategory(1, 6, "カレー", 1));
		favoriteList.add(new FavoriteCategory(1, 7, "ラーメン", 1));
		favoriteList.add(new FavoriteCategory(1, 10, "メンズ", 5));
		session.setAttribute("favoriteCategory", favoriteList);

		return "user_info";
	}

	// 登録情報変更画面
	@RequestMapping(value = "/user_info_update")
	public String UserInfo(@ModelAttribute("userInfo") UserInfoForm form) {
		Users signInUser = (Users)session.getAttribute("signInUser");
		form.setLoginId(signInUser.getLoginId());
		form.setUserName(signInUser.getUserName());
		form.setCitiesId(signInUser.getCitiesId());

		List<FavoriteCategory> list = (List<FavoriteCategory>)session.getAttribute("favoriteCategory");
		form.setMainCategoryId1(list.get(0).getMainCategoryId());
		form.setMainCategoryId2(list.get(1).getMainCategoryId());
		form.setMainCategoryId3(list.get(2).getMainCategoryId());
		form.setCategoryId1(list.get(0).getCategoryId());
		form.setCategoryId2(list.get(1).getCategoryId());
		form.setCategoryId3(list.get(2).getCategoryId());

		//form.setMainCategoryId1(((List<FavoriteCategory>)session.getAttribute("favoriteList"))[0].getMainCategoryId());

		return "/user_info_update";
	}

	// 登録情報変更処理
	@RequestMapping(value = "/user_info", params = "update", method = RequestMethod.POST)
	public String updateUserInfo(@Validated @ModelAttribute("userInfo") UserInfoForm form, BindingResult bindingResult, Model model) {
		// 遷移先はuser_infoでリザルトはなしでいいかしら
		return "/user_info";
	}

}