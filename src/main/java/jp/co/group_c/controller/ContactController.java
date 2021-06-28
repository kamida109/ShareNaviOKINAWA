package jp.co.group_c.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.group_c.contact.ContactForm;
import jp.co.group_c.contact.InqueryForm;
import jp.co.group_c.contact.UserDeleteForm;
import jp.co.group_c.contact.UserManagementForm;
import jp.co.group_c.contact.entity.Contact;
import jp.co.group_c.contact.entity.UserManagement;
import jp.co.group_c.contact.service.ContactService;
import jp.co.group_c.entity.Users;

@Controller
public class ContactController {

	//ContactServiceのインスタンス生成
	@Autowired
	ContactService contactService;

	@Autowired
	HttpSession session;

	int flag = 0;

	 // 問い合わせ送信処理（一般ユーザー用）
	@RequestMapping(value = "/contact_result",params = "insert", method = RequestMethod.POST)
	public String contact(@Validated @ModelAttribute("contactInfo") InqueryForm inqueryForm,  BindingResult bindingResult, Model model,
										@ModelAttribute("contact_management") ContactForm contactForm) {

		//バリデーションの結果で処理分岐
		if(bindingResult.hasErrors()) {
		return "contact";
	}
		//引数の中はInqueryFormのフィールドにつながる
		//Contact contact = new Contact(inqueryForm.getUserId(), inqueryForm.getContactCategoryId(), inqueryForm.getContents(), inqueryForm.isFlag());
		inqueryForm.setUserId(((Users)(session.getAttribute("signInUser"))).getUserId());
		contactService.contactInsert(inqueryForm);

		return "contact_result";
	}


	// 問い合わせ画面に飛ぶ（管理者でログインしたとき）
	@RequestMapping(value = "/contact")
	public String jampContact(@ModelAttribute("contactInfo") ContactForm form, Model model,
								@ModelAttribute("contact_management") ContactForm contactForm) {

		// sessionからログインユーザの情報を取得してログインユーザが管理者なら
		// 問い合わせ情報を全件取得(あとあと全件じゃなくなるかも)
		// ORDER BY でcontactcategoryの昇順に取得してください

		if(flag == 0) {
			List<Contact> list = contactService.findAll();
			model.addAttribute("selectResult", list);
			flag++;
		}

		return "contact";
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

		//解決済みのとき解決ボタンを非表示にする
		 boolean solvedFlag = detailInfo.isFlag();
		 if(solvedFlag == false) {
			 model.addAttribute("flag", "false");

		 }

		 return "contact";
	}

	//★ 問い合わせ内容詳細表示→解決ボタン押したとき
	@RequestMapping(value = "/contact" , params = "update", method = RequestMethod.POST)
	public String updateContact(@Validated @ModelAttribute("contact_management") ContactForm form, BindingResult bindingResult,Model model,
									@ModelAttribute("contactInfo") ContactForm contactForm) {

		 //List<Contact> list = contactService.findAll();
		 //model.addAttribute("selectResult", list);

		//contactCategoryIdを判定
		Integer contactCategoryId = form.getContactCategoryId();

		if(contactCategoryId == 1) {
			model.addAttribute("contactCategoryId", "通報");
		}else if(contactCategoryId == 2) {
			model.addAttribute("contactCategoryId", "問い合わせ");
		}else if(contactCategoryId == 3) {
			model.addAttribute("contactCategoryId", "要望");
		}

		contactService.flagUpdate(form.getContactId());
		model.addAttribute("updateMsg", "問い合わせ内容を解決しました。");

		return "contact";
	}

	// ユーザ管理画面に飛ぶ
	@RequestMapping(value = "/user_management")
	public String jampUserManagement(@ModelAttribute("userManagement") UserManagementForm form,
									@ModelAttribute("userDelete") UserDeleteForm userDeleteForm) {

		return "user_management";
	}

	//ユーザー管理画面で検索ボタン押されたとき、この画面に戻る
	@RequestMapping(value = "/user_management", params = "select", method = RequestMethod.POST)
	public String managementSelect (@Validated @ModelAttribute("userManagement") UserManagementForm userManagementForm, BindingResult bindingResult,
									@ModelAttribute("userDelete") UserDeleteForm userDeleteForm, Model model) {

		//UserManagement userManagement = new UserManagement(userManagementForm.getUserId(), userManagementForm.getUserName());
		List<UserManagement> list = contactService.managementFind(userManagementForm);

		//IDか名前での検索結果がないとき
		if(list.size() == 0) {
			model.addAttribute("checkMsg", "対象のユーザーは存在しません");
			return "user_management";
		}

		model.addAttribute("userManagementList", list);

		return "user_management";
	}

	//確定ボタン押されたとき、この画面に戻る（削除）
	@RequestMapping(value = "/user_management", params = "delete", method = RequestMethod.POST)
	public String managementDelete (@Validated @ModelAttribute("userDelete") UserDeleteForm userDeleteForm, BindingResult bindingResult, @ModelAttribute("userManagement") UserManagementForm userManagementForm, Model model) {

		//ユーザーID未入力のとき
		if(bindingResult.hasErrors()) {
			model.addAttribute("errMsg", "削除するユーザのユーザIDを入力してください");
			return "user_management";
		}

		//ログインユーザーは削除できないようにする
		//入力フォームの値
		Integer inputUserId = userDeleteForm.getUserId();

		//sessionからユーザーIdをとる
		Integer sessionUserId = ((Users)(session.getAttribute("signInUser"))).getUserId();

		if(inputUserId.equals(sessionUserId)) {
			model.addAttribute("errMsg", "ログインユーザーは削除できません。");
			return "user_management";
		}


		//削除するIDが存在しない場合
		String getUserName = contactService.managementDelete(userDeleteForm.getUserId());

				if(getUserName == null) {
					model.addAttribute("errMsg", "対象のユーザーは存在しません。");
					return "user_management";
				}

		model.addAttribute("msg", "ユーザー" + (getUserName) + "さんを削除しました。");
		return "user_management";
	}


	// 未解決のみ表示
	@RequestMapping(value="/checkSolved/", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String checkSolved(Model model) {

		List<Contact> list = contactService.findUnsolved();

//		model.addAttribute("selectResult", list);
		session.setAttribute("selectResult", list);
		return null;
	}

	@RequestMapping(value="/checkUnSolved/", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String checkUnSolved(Model model) {

		List<Contact> list = contactService.findSolved();

//		model.addAttribute("selectResult", list);
		session.setAttribute("selectResult", list);
		return null;
	}

	@RequestMapping(value="/checkAll/", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String checkAll(Model model) {

		List<Contact> list = contactService.findAll();

//		model.addAttribute("selectResult", list);
		session.setAttribute("selectResult", list);
		return null;
	}



}


