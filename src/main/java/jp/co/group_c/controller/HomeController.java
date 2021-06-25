package jp.co.group_c.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.group_c.entity.Store;
import jp.co.group_c.entity.Users;
import jp.co.group_c.homeService.HomeService;

@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;

	@Autowired
	private HttpSession session;

	// ホーム画面
	@RequestMapping(value = "/home")
	public String jumpHome(Model model) {

		//session破棄
		session.removeAttribute("storeList");
		session.removeAttribute("notList");
		session.removeAttribute("mainCategoryList");
		session.removeAttribute("planList");
		session.removeAttribute("notPlanList");
		session.removeAttribute("setImages");

		//新着表示
		List<Store> newArrivalList = homeService.newArrival();

		//DBが空の時の対処
		if(newArrivalList == null) {
			model.addAttribute("notList", "undefinde");
			model.addAttribute("notRecommendList", "undefinde");
			model.addAttribute("notPlanList", "undefinde");
			return "home";
		}

		//カテゴリ表示
		List<Store> mainCategoryList = homeService.mainCategory();

		//ユーザー情報の取得
		List<Users> userList = homeService.users();

		//DBが空の時の対処
		if(userList == null) {
			model.addAttribute("notRecommendList", "undefinde");
			model.addAttribute("notPlanList", "undefinde");
			return "home";
		}

		//おすすめ表示
		List<Store> recommendList = homeService.recommend(userList.get(0).getUserId());

		//新しい提案
		List<Store> planList = homeService.plan(userList.get(0).getUserId());
		Collections.shuffle(planList);

		//画像表示
		List<Store> imageList = homeService.image();

		//宣言したList達をModelに保存
		model.addAttribute("storeList", newArrivalList);
		model.addAttribute("mainCategoryList", mainCategoryList);
		model.addAttribute("recommendList", recommendList);
		model.addAttribute("planList", planList);
		model.addAttribute("imageList", imageList);

		return "home";
	}

}
