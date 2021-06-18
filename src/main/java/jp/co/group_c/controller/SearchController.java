package jp.co.group_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {

	// 店舗検索画面に飛ぶ
	@RequestMapping(value = "/search")
	public String jampSearch(/*@ModelAttribute("userInfo") SearchForm form, */Model model) {

		//modelテスト
		model.addAttribute("selectResult", "国立劇場のラーメン屋");

		return "search";
	}

	// 店舗詳細画面
	@RequestMapping(value = "/details"/*"/details/{id}"*/)
	public String details() {
		return "details";
	}

}
