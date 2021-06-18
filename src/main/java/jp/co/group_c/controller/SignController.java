package jp.co.group_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignController {

    @Autowired
    HttpSession session;

	// ログイン -------------------------------------------------------------------------------------------

		// ログイン画面
		@RequestMapping(value = "/sign_in")
		public String jumpSignIn() {
			return "sign_in";
		}

		// ログイン処理
		@RequestMapping(value = "/home", params = "sign_in")
		public String signIn() {

			// ここに処理を記述

			return "home";
		}

	// 新規登録 -------------------------------------------------------------------------------------------

		// 新規登録画面
		@RequestMapping(value = "/sign_up")
		public String jumpSignUp() {
			return "sign_up";
		}

		// 新規登録処理
		@RequestMapping(value = "/sign_up", params = "sign_up")
		public String signUp(Model model) {

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
