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

import jp.co.group_c.controller.form.SignInForm;
import jp.co.group_c.controller.form.SignUpForm;
import jp.co.group_c.entity.Category;
import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.FavoriteCategory;
import jp.co.group_c.entity.Users;
import jp.co.group_c.service.UserInfoService;
import jp.co.group_c.service.search.SearchService;

@Controller
public class SignController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserInfoService uiService;

    @Autowired
    private SearchService searchService;

	// ログイン -------------------------------------------------------------------------------------------

		// ログイン画面に飛ぶ
		@RequestMapping(value = "/sign_in")
		public String jumpSignIn(@ModelAttribute("signIn") SignInForm form) {

			// 市町村とカテゴリの取得
			List<Cities> citiesList = searchService.cities();
			session.setAttribute("cities", citiesList);
			List<Category> mainCategoryList = searchService.mainCategory();
			session.setAttribute("mainCategory", mainCategoryList);

			return "sign_in";
		}

		// ログイン処理
		@RequestMapping(value = "/sign_in", params = "sign_in")
		public String signIn(@Validated @ModelAttribute("signIn") SignInForm form, BindingResult bindingResult, Model model) {

			// 入力チェック
			if(bindingResult.hasErrors()) {
				model.addAttribute("msg", "未入力の項目があります");
				return "sign_in";
			}

			// ログインユーザの情報取得
			Users signInUser = uiService.signInCheck(form);

			// ログインチェック
			if(signInUser == null) {
				model.addAttribute("msg","IDまたはPASSが間違っています");
				return "sign_in";
			}

			// ユーザの情報をセッションに
			session.setAttribute("signInUser", signInUser);
			signInUser.setPassword(null);	//念のためpasswordはnullに
			List<FavoriteCategory> favoriteList = uiService.findFavoriteCategory(signInUser.getUserId());
			session.setAttribute("favoriteCategory", favoriteList);

			return "redirect:home";
		}

	// 新規登録 -------------------------------------------------------------------------------------------

		// 新規登録画面に飛ぶ
		@RequestMapping(value = "/sign_up")
		public String jumpSignUp(@ModelAttribute("signUp") SignUpForm form) {
			return "sign_up";
		}

		// 新規登録確認
		@RequestMapping(value = "/sign_up", params = "check"/*, method = RequestMethod.POST*/)
		public String signUpCheck(/*@Validated @ModelAttribute("signUp") SignUpForm form, BindingResult bindingResult, Model model*/) {

			// ここに処理を記述

			return "sign_up_check";
		}

		// 新規登録処理
		@RequestMapping(value = "/sign_up", params = "update"/*, method = RequestMethod.POST)*/)
		public String signUp(/*@Validated @ModelAttribute("signUp") SignUpForm form, BindingResult bindingResult, */Model model) {

			// ここに処理を記述

			model.addAttribute("msg", "登録ありがとうございます");

			return "sign_out";
		}

	// ログアウト -----------------------------------------------------------------------------------------

		// ログアウト処理
		@RequestMapping(value = "/sign_out")
		public String signOut(Model model) {

			// セッションを破棄
			session.invalidate();

			model.addAttribute("msg", "ご利用ありがとうございました");

			return "sign_out";
		}

}
