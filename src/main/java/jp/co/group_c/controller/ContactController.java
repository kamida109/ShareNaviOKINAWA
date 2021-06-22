package jp.co.group_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.group_c.contact.ContactForm;
import jp.co.group_c.contact.entity.Contact;
import jp.co.group_c.contact.service.ContactService;

@Controller
public class ContactController {

	//ContactServiceのインスタンス生成
	@Autowired
	ContactService contactService;


	// 問い合わせ画面に飛ぶ
	@RequestMapping(value = "/contact")
	public String jampContact(@ModelAttribute("contactInfo") ContactForm form) {

		// sessionからログインユーザの情報を取得して
		// ログインユーザが管理者なら
		// 問い合わせ情報を全件取得(あとあと全件じゃなくなるかも)
		// ORDER BY でcontactcategoryの昇順に取得してください

		return "contact";
	}

	// 問い合わせ送信処理
	@RequestMapping(value = "/contact_result",params = "insert", method = RequestMethod.POST)
	public String contact(@Validated @ModelAttribute("contactInfo") ContactForm form,  BindingResult bindingResult, Model model) {

		//バリデーションの結果で処理分岐
		if(bindingResult.hasErrors()) {
			return "contact";
		}

		//入力内容をもとにContactインスタンス生成→サービスのメソッドに引き継いでデータ登録
		//引数の中はContactFormのフィールドにつながる
		Contact contact = new Contact(form.getUserId(), form.getContactCategoryId(), form.getContents(), form.isFlag());
		contactService.contactInsert(contact);

		return "contact_result";
	}


	// 問い合わせ解決処理.管理者用
	@RequestMapping(value = "/contact" , params = "update", method = RequestMethod.POST)
	public String updateContact(@Validated @ModelAttribute("contactInfo") ContactForm form, BindingResult bindingResult, Model model) {

		//？？
		Integer contactId = (form.getContactId());

		contactService.findAll();
		contactService.find(contactId);

		return "contact";
	}

	// ユーザ管理画面に飛ぶ
	@RequestMapping(value = "/user_management")
	public String jampUserManegement(/*@ModelAttribute("contactInfo") ContactForm form, Model model*/) {
		return "user_manegement";
	}

}

