package jp.co.group_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

	// 問い合わせ画面に飛ぶ
	@RequestMapping(value = "/contact")
	public String jampContact(/*@ModelAttribute("contactInfo") ContactForm form, Model model*/) {

		// sessionからログインユーザの情報を取得して
		// ログインユーザが管理者なら
		// 問い合わせ情報を全件取得(あとあと全件じゃなくなるかも)
		// ORDER BY でcontactcategoryの昇順に取得してください

		return "contact";
	}

	// 問い合わせ処理
	@RequestMapping(value = "/contact" , params = "insert"/*, method = RequestMethod.POST*/)
	public String contact(/*@Validated @ModelAttribute("contactInfo") ContactForm form,  BindingResult bindingResult, Model model*/) {
		return "contact_result";
	}

	// 問い合わせ解決処理
	@RequestMapping(value = "/contact" , params = "update"/*, method = RequestMethod.POST*/)
	public String updateContact(/*@ModelAttribute("contactInfo") ContactForm form, Model model*/) {
		return "contact";
	}

	// ユーザ管理画面に飛ぶ
	@RequestMapping(value = "/user_management")
	public String jampUserManegement(/*@ModelAttribute("contactInfo") ContactForm form, Model model*/) {
		return "user_manegement";
	}
}
