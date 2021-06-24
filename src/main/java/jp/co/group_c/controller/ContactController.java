package jp.co.group_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.group_c.contact.ContactForm;
import jp.co.group_c.contact.UserDeleteForm;
import jp.co.group_c.contact.UserManagementForm;
import jp.co.group_c.contact.entity.Contact;
import jp.co.group_c.contact.entity.UserManagement;
import jp.co.group_c.contact.service.ContactService;

@Controller
public class ContactController {

	//ContactServiceのインスタンス生成
	@Autowired
	ContactService contactService;


	// 問い合わせ画面に飛ぶ
	@RequestMapping(value = "/contact")
	public String jampContact(@ModelAttribute("contactInfo") ContactForm form, Model model,
								@ModelAttribute("contact_management") ContactForm contactForm) {

		// sessionからログインユーザの情報を取得して
		// ログインユーザが管理者なら
		// 問い合わせ情報を全件取得(あとあと全件じゃなくなるかも)
		// ORDER BY でcontactcategoryの昇順に取得してください

		 List<Contact> list = contactService.findAll();
		 model.addAttribute("selectResult", list);


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

	// 問い合わせ内容詳細表示
	@RequestMapping(value = "/contact/{id}")
	public String detaileContact(@ModelAttribute("id") Integer id,
									@ModelAttribute("contact_management") ContactForm form, Model model,
									@ModelAttribute("contactInfo") ContactForm contactForm) {

		Contact detailInfo = contactService.find(id);
		model.addAttribute("detailInfo", detailInfo);


		 List<Contact> list = contactService.findAll();
		 model.addAttribute("selectResult", list);

		//内容をjspのフォームに入れるとき、セットして引数にゲットする
		form.setContactId(detailInfo.getContactId());
		form.setUserName(detailInfo.getUserName());
		form.setContactCategoryId(detailInfo.getContactCategoryId());
		form.setContents(detailInfo.getContents());

		//contactCategoryIdを判定
		Integer contactCategoryId = detailInfo.getContactCategoryId();

		if(contactCategoryId == 1) {
			model.addAttribute("contactCategoryId", "通報");
		}else if(contactCategoryId == 2) {
			model.addAttribute("contactCategoryId", "問い合わせ");
		}else if(contactCategoryId == 3) {
			model.addAttribute("contactCategoryId", "要望");
		}

		return "contact";
	}

	//★ 問い合わせ内容詳細表示→解決ボタン押したとき
	@RequestMapping(value = "/contacts" , /*params = "update",*/ method = RequestMethod.POST)
	public String updateContact(@ModelAttribute("contact_management") ContactForm form,
									@ModelAttribute("contactInfo") ContactForm contactForm) {

		//Contact contactId = new Contact(form.getContactId());
		System.out.println(form.getContactId());

		System.out.println("action");

		contactService.flagUpdate(form.getContactId());

		return "contact";
	}

	// ユーザ管理画面に飛ぶ
	@RequestMapping(value = "/user_management")
	public String jampUserManagement(@ModelAttribute("userManagement") UserManagementForm form) {

		return "user_management";
	}

	//ユーザー管理画面で
	//検索ボタン押されたとき、この画面に戻る
	@RequestMapping(value = "/user_management", params = "select", method = RequestMethod.POST)
	public String managementSelect (@Validated @ModelAttribute("userManagement") UserManagementForm form, BindingResult bindingResult, Model model) {

		UserManagement userManagement = new UserManagement(form.getUserId(), form.getUserName());
		List<UserManagement> list = contactService.managementFind(userManagement);
		model.addAttribute("userManagementList", list);

		return "user_management";
	}

	//確定ボタン押されたとき、この画面に戻る（削除）
	@RequestMapping(value = "/user_management", params = "delete", method = RequestMethod.POST)
	public String managementDelete (@Validated @ModelAttribute("userManagement") UserDeleteForm userDeleteForm, BindingResult bindingResult, Model model) {


		//削除するIDが存在しない場合
		String getUserName = contactService.managementDelete(userDeleteForm.getUserId());

				if(getUserName == null) {
					model.addAttribute("errMsg", "入力されたIDのユーザーは存在しません。");
					return "user_management";
				}

		//String getUserName = contactService.managementDelete(userDeleteForm.getUserId());
		model.addAttribute("msg", "ユーザー" + (getUserName) + "さんを削除しました。");

		return "user_management";
	}
}


