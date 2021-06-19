package jp.co.group_c.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.group_c.controller.form.SearchForm;
import jp.co.group_c.entity.Store;
import jp.co.group_c.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@Autowired
	HttpSession session;

	// 店舗検索画面
	@RequestMapping(value = "/search")
	public String search(SearchForm form, Model model) {

		//modelテスト
//		model.addAttribute("selectResult", "国立劇場のラーメン屋");

		List<Store> storeList = searchService.storeSearch(form.getStoreName(), form.getCategory(), form.getCity(), form.isChecked());

		// 店舗検索
		if(storeList.isEmpty()) {
			model.addAttribute("selectResult", "対象のデータはありませんでした");
		} else {
			model.addAttribute("selectResult", storeList);
		}

		// あいまい検索

		return "search";
	}

	// 店舗詳細画面
	@RequestMapping(value = "/details")
	public String details() {
		return "details";
	}

}
