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

	// 問い合わせ画面
	@RequestMapping(value = "/contact")
	public String contact(@ModelAttribute("contactInfo") ContactForm form) {
		return "contact";
	}

	// 問い合わせ送信処理
	@RequestMapping(value = "/contact_result",params = "insert", method = RequestMethod.POST)
	public String insert(@Validated @ModelAttribute("contactInfo") ContactForm form,  BindingResult bindingResult, Model model) {

		//入力内容をもとにContactインスタンス生成→サービスのメソッドに引き継いでデータ登録
		//引数の中はContactFormのフィールドにつながる
		Contact contact = new Contact(form.getUserId(), form.getContactCategoryId(), form.getContents(), form.isFlag());
		contactService.contactInsert(contact);

		return "contact_result";
	}

}

