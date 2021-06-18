package jp.co.group_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	// ホーム画面
	@RequestMapping(value = "/home")
	public String home() {
		return "home";
	}

}
