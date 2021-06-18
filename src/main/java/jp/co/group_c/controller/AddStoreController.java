package jp.co.group_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddStoreController {

	// 店舗登録画面
	@RequestMapping(value = "/add_store")
	public String addStore() {
		return "add_store";
	}

}
