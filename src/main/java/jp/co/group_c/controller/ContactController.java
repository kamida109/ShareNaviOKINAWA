package jp.co.group_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

	// 問い合わせ画面
	@RequestMapping(value = "/contact")
	public String contact() {
		return "contact";
	}

}
