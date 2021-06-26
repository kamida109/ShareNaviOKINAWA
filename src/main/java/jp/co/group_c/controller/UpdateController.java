package jp.co.group_c.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.Store;
import jp.co.group_c.search.service.SearchService;
import jp.co.group_c.update.entity.Review;
import jp.co.group_c.update.entity.StoreCategory;
import jp.co.group_c.update.form.StoreUpdateForm;
import jp.co.group_c.update.service.UpdateService;

@Controller
public class UpdateController {

	@Autowired
	private HttpSession session;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UpdateService updateService;

	@Autowired
	private SearchService searchService;

	@RequestMapping(value="/update_store", params="storeUpdate",method=RequestMethod.POST)
	public String jumpUpdateStore(@ModelAttribute("update_store") StoreUpdateForm form, Model model) {

		List<Store> list = (List<Store>)session.getAttribute("storeDitails");
		List<Cities> clist = (List<Cities>)session.getAttribute("cities");

		form.setStoreId(list.get(0).getStoreId());
		form.setStoreName(list.get(0).getStoreName());
		form.setBusinessHours(list.get(0).getBusinessHours());
		form.setCitiesId(list.get(0).getCitiesId());
		form.setAddress(list.get(0).getAddress());
		form.setTel(list.get(0).getTel());
		form.setHyouka(list.get(0).getHyouka());
		form.setReviewId(list.get(0).getReviewId());

		return "update_store";
	}

	@RequestMapping(value="/updateStoreResult", params="updateDetails", method=RequestMethod.POST)
	public String updateStoreResult(@ModelAttribute("update_store") StoreUpdateForm form, Model model) {

		String subCategory1 = request.getParameter("category1");
		String subCategory2 = request.getParameter("category2");
		String subCategory3 = request.getParameter("category3");

		Integer intCategory1 = (isNumber(subCategory1)) ? Integer.parseInt(subCategory1):null;
		Integer intCategory2 = (isNumber(subCategory2)) ? Integer.parseInt(subCategory2):null;
		Integer intCategory3 = (isNumber(subCategory3)) ? Integer.parseInt(subCategory3):null;

		if(form.getStoreName().equals("")) {
			model.addAttribute("errMsg", "店舗名は必須です");
			return "update_store";
		}

		if(intCategory1==null && intCategory2==null && intCategory3==null) {
			model.addAttribute("errMsg", "カテゴリを一つ以上選択してください");
			return "update_store";
		}

		// 店舗基本情報の更新
		Store store = new Store(form.getStoreId(), form.getStoreName(), form.getBusinessHours(), form.getCitiesId(), form.getAddress(), form.getTel());

		// 店舗評価の更新
		Review review = new Review(form.getReviewId(), form.getHyouka());

		// 店舗カテゴリの更新
		StoreCategory sc = new StoreCategory(form.getStoreId(), intCategory1, intCategory2, intCategory3);

		updateService.storeRankUpdate(review);
		updateService.storeUpdate(store);
		updateService.storeCategoryUpdate(sc);

		List<Store> storeDitails = searchService.storeDitails(form.getStoreId());
		session.setAttribute("storeDitails", storeDitails);

		List<Store> storeCategoryList = searchService.storeCategory();
		session.setAttribute("mainCategoryList", storeCategoryList);

		return "details";
	}

	// 戻るボタンが押されたときの処理
	@RequestMapping(value="/updateStoreResult", params="returnDetails", method=RequestMethod.POST)
	public String updateStoreResult(@ModelAttribute("update_store") StoreUpdateForm form) {
		return "details";
	}

	// 数字に変換できるか
	public boolean isNumber(String val) {
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
	}

}
