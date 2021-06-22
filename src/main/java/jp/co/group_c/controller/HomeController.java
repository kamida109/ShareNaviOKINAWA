package jp.co.group_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.group_c.entity.Store;
import jp.co.group_c.homeService.HomeService;

@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;

	// ホーム画面
	@RequestMapping(value = "/home")
	public String jumpHome(Model model) {

		//新着表示
		List<Store> newArrivalList = homeService.newArrival();

		//カテゴリ表示
		List<Store> mainCategoryList = homeService.mainCategory(newArrivalList.get(0).getStoreName());

		model.addAttribute("mainCategoryList", mainCategoryList);
		model.addAttribute("storeList", newArrivalList);

		return "home";
	}

}
