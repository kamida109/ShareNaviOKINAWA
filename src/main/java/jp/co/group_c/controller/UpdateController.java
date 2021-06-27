package jp.co.group_c.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.Store;
import jp.co.group_c.search.service.SearchService;
import jp.co.group_c.update.entity.Favorite;
import jp.co.group_c.update.entity.Review;
import jp.co.group_c.update.entity.StoreCategory;
import jp.co.group_c.update.entity.Utility;
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

		Integer intCategory1 = (Utility.isNumber(subCategory1)) ? Integer.parseInt(subCategory1):null;
		Integer intCategory2 = (Utility.isNumber(subCategory2)) ? Integer.parseInt(subCategory2):null;
		Integer intCategory3 = (Utility.isNumber(subCategory3)) ? Integer.parseInt(subCategory3):null;

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

	// お気に入り機能の実装(非同期)
	@RequestMapping(value="favorite/{storeId}/{flag}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void storeFavorite(@PathVariable("storeId") String storeId, @PathVariable("flag") String flag, Model model) {

		Integer intStoreId = (Utility.isNumber(storeId)) ? Integer.parseInt(storeId):null;
		Integer intFlag = (Utility.isNumber(flag)) ? Integer.parseInt(flag):null;

		Favorite favarite = new Favorite(1, intStoreId);

		updateService.storeFavorite(favarite, intFlag);

	}

}
