package jp.co.group_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddStoreController {

	// 店舗登録画面に飛ぶ
	@RequestMapping(value = "/add_store")
	public String jumpAddStore(/*@ModelAttribute("storeInfo") storeInfoForm form, Model model*/) {
		return "add_store";
	}

	// 店舗登録確認
	@RequestMapping(value = "/add_store_check", params = "check"/*, method = RequestMethod.POST*/)
	public String addStoreCheck(/*@Validated @ModelAttribute("storeInfo") storeInfoForm form, BindingResult bindingResult, Model model*/) {
		return "add_store_check";
	}

	// 店舗登録処理
	@RequestMapping(value = "/add_store_result", params = "update"/*, method = RequestMethod.POST*/)
	public String addStore(/*@Validated @ModelAttribute("storeInfo") storeInfoForm form, BindingResult bindingResult, Model model*/) {
		return "add_store_result";
	}


}
