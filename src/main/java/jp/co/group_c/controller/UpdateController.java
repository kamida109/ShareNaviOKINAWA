package jp.co.group_c.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.Store;
import jp.co.group_c.update.form.StoreUpdateForm;

@Controller
public class UpdateController {

	@Autowired
	private HttpSession session;

	@Autowired
	private HttpServletRequest request;

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

		return "update_store";
	}

	@RequestMapping(value="/updateStoreResult", method=RequestMethod.POST)
	public String updateStoreResult(@Validated @ModelAttribute("update_store") StoreUpdateForm form, BindingResult bindingResult, Model model) {

		int storeId = form.getStoreId();
		String subCategory1 = request.getParameter("category1");
		String subCategory2 = request.getParameter("category2");
		String subCategory3 = request.getParameter("category3");

		if(bindingResult.hasErrors() || !isNumber(subCategory1)) {
			System.out.println("aaa");
			return "update_store";
		}

		// 店舗基本情報の更新

		// 店舗評価の更新

		// 店舗カテゴリの更新
		// 数値に変換できるサブカテゴリのみ更新する


		return "update_store";
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
