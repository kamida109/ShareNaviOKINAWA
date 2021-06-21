package jp.co.group_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {

	// マイページ画面
	@RequestMapping(value = "/my_page")
	public String jumpMyPage() {
		return "my_page";
	}

	// 登録情報画面に飛ぶ
	@RequestMapping(value = "/user_info")
	public String jumpUserInfo() {
		return "user_info";
	}

	// 登録情報変更画面
	@RequestMapping(value = "/user_info_update")
	public String UserInfo(/*@ModelAttribute("userInfo") SignUpForm form*/) {
		return "/user_info_update";
	}

	// 登録情報変更確認
	@RequestMapping(value = "/user_info_check", params = "check"/*, method = RequestMethod.POST*/)
	public String updateUserInfoCheck(/*@Validated @ModelAttribute("userInfo") SignUpForm form, BindingResult bindingResult, Model model*/) {
		return "/user_info_check";
	}

	// 登録情報変更処理
	@RequestMapping(value = "/user_info", params = "update"/*, method = RequestMethod.POST*/)
	public String updateUserInfo(/*@Validated @ModelAttribute("userInfo") SignUpForm form, BindingResult bindingResult, Model model*/) {
		// 遷移先はuser_infoでリザルトはアラートのみでいいかしら
		return "/user_info";
	}

}