package jp.co.group_c.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
		Users signInUser = new Users(1, "groupC", 24, "groupC");
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

		//form.set(session.getAttribute("favoriteCategory"));

		return "/user_info_update";
	}

	// 登録情報変更確認
	@RequestMapping(value = "/user_info_check", params = "check"/*, method = RequestMethod.POST*/)
	public String updateUserInfoCheck(/*@Validated @ModelAttribute("userInfo") UserInfoForm form, BindingResult bindingResult, Model model*/) {
		return "/user_info_check";
	}

	// 登録情報変更処理
	@RequestMapping(value = "/user_info", params = "update"/*, method = RequestMethod.POST*/)
	public String updateUserInfo(/*@Validated @ModelAttribute("userInfo") UserInfoForm form, BindingResult bindingResult, Model model*/) {
		// 遷移先はuser_infoでリザルトはアラートのみでいいかしら
		return "/user_info";
	}

}