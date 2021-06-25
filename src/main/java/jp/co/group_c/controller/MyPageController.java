package jp.co.group_c.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.group_c.controller.form.UserInfoForm;
import jp.co.group_c.entity.FavoriteCategory;
import jp.co.group_c.entity.Store;
import jp.co.group_c.entity.Users;
import jp.co.group_c.homeService.HomeService;
import jp.co.group_c.service.StoreService;
import jp.co.group_c.service.UserInfoService;

@Controller
public class MyPageController {

    @Autowired
    private HttpSession session;

    //@Autowired
    //private HttpServletRequest request;

    @Autowired
	private UserInfoService uiService;

    @Autowired
	private StoreService storeService;

    @Autowired
	private HomeService homeService;

	// マイページ画面
	@RequestMapping(value = "/my_page")
	public String jumpMyPage(Model model) {

		//session破棄
		session.removeAttribute("storeList");
		session.removeAttribute("notList");
		session.removeAttribute("mainCategoryList");
		session.removeAttribute("planList");
		session.removeAttribute("notPlanList");
		session.removeAttribute("setImages");

		Users signInUser = (Users)session.getAttribute("signInUser");

		//お気に入り表示
		List<Store> favoriteList = storeService.favoriteStore(signInUser.getUserId());

		//DBが空の時の対処
		if(favoriteList == null) {
			model.addAttribute("notList", "undefinde");
			model.addAttribute("notRecommendList", "undefinde");
			model.addAttribute("notPlanList", "undefinde");
			return "my_page";
		}

		//レビュー履歴表示
		List<Store> reviewList = storeService.reviewStore(signInUser.getUserId());

		//DBが空の時の対処
		if(reviewList == null) {
			model.addAttribute("notList", "undefinde");
			model.addAttribute("notRecommendList", "undefinde");
			model.addAttribute("notPlanList", "undefinde");
			return "my_page";
		}

		//カテゴリ表示
		List<Store> mainCategoryList = homeService.mainCategory(favoriteList.get(0).getStoreName());

		//お店リスト送信
		model.addAttribute("storeList", favoriteList);
		model.addAttribute("recommendList", reviewList);
		model.addAttribute("mainCategoryList", mainCategoryList);

		return "my_page";
	}

	// 登録情報画面に飛ぶ
	@RequestMapping(value = "/user_info")
	public String jumpUserInfo() {

		//session破棄
		session.removeAttribute("storeList");
		session.removeAttribute("notList");
		session.removeAttribute("planList");
		session.removeAttribute("notPlanList");
		session.removeAttribute("setImages");

		return "user_info";
	}

	// 登録情報変更画面に飛ぶ
	@RequestMapping(value = "/user_info_update")
	public String UserInfo(@ModelAttribute("userInfo") UserInfoForm form, Model model) {

		Users signInUser = (Users)session.getAttribute("signInUser");
		form.setLoginId(signInUser.getLoginId());
		form.setUserName(signInUser.getUserName());
		form.setCitiesId(signInUser.getCitiesId());

		List<FavoriteCategory> list = (List<FavoriteCategory>)session.getAttribute("favoriteCategory");
		form.setMainCategoryId1(list.get(0).getMainCategory());
		form.setMainCategoryId2(list.get(1).getMainCategory());
		form.setMainCategoryId3(list.get(2).getMainCategory());

		return "user_info_update";
	}

	// 登録情報変更処理
	@RequestMapping(value = "/user_info", params = "update", method = RequestMethod.POST)
	public String updateUserInfo(@Validated @ModelAttribute("userInfo") UserInfoForm form, BindingResult bindingResult, Model model) {

		// 入力チェック
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "未入力や不正な入力があります");
			return "/user_info_update";
		}

		// セッションからログインユーザの情報を取得
		Users signInUser = (Users)session.getAttribute("signInUser");

		// loginId重複チェック
		Users userInfo = uiService.checkLoginId(form.getLoginId());
		if (!Objects.isNull(userInfo) && !(form.getLoginId().equals(signInUser.getLoginId()))) {
			model.addAttribute("msg", "既に使用されているIDです");
			return "/user_info_update";
		}

		/*
		// scriptで表示したoptionがformで取得できなかった場合
		Integer categoryId1 = Integer.parseInt(request.getParameter("categoryId1"));
		Integer categoryId2 = Integer.parseInt(request.getParameter("categoryId2"));
		Integer categoryId3 = Integer.parseInt(request.getParameter("categoryId3"));
		form.setCategoryId1(categoryId1);
		form.setCategoryId2(categoryId2);
		form.setCategoryId3(categoryId3);
		*/

		// ユーザ情報の更新とログインユーザのセッション更新
		signInUser = uiService.updateUserInfo(signInUser.getUserId(), form);
		session.setAttribute("signInUser", signInUser);
		signInUser.setPassword(null);	//念のためpasswordはnullに
		List<FavoriteCategory> favoriteList = uiService.findFavoriteCategory(signInUser.getUserId());
		session.setAttribute("favoriteCategory", favoriteList);

		model.addAttribute("msg", "登録内容を変更しました");
		return "/user_info";

	}

}