package jp.co.group_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {

	// マイページ画面
	@RequestMapping(value = "/my_page")
	public String myPage() {
		return "my_page";
	}

}
