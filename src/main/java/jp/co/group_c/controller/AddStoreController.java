package jp.co.group_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.group_c.add_store_entity.Category;
import jp.co.group_c.add_store_entity.City;
import jp.co.group_c.add_store_service.AddStoreService;
import jp.co.group_c.controller.add_store_form.AddStoreForm;

@Controller
public class AddStoreController {

	@Autowired
	AddStoreService addStoreService;



	// 店舗登録画面
	@RequestMapping(value = "/add_store", method = RequestMethod.GET)
	public String addStore(@ModelAttribute("add_store") AddStoreForm form, Model model) {

		List<City> cityList = addStoreService.allCity();
		List<Category> mainCateList = addStoreService.selectMainCategory();
		List<Category> childCateList = addStoreService.selectAllChildCategory();

		model.addAttribute("cities", cityList);
		model.addAttribute("mainCategory", mainCateList);
		model.addAttribute("childCategory", childCateList);

		System.out.println(cityList.size());
		System.out.println(mainCateList.size());
		System.out.println(childCateList.size());
		return "add_store";
	}
	@RequestMapping(value = "/add_store_check", method = RequestMethod.POST)
	public String addStoreCheck(@ModelAttribute("add_store") AddStoreForm form, Model model) {
		return "add_store_check";
	}

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public String category(@ModelAttribute("add_store") AddStoreForm form, Model model) {
		System.out.println("おいしい");
		return null;

	}
}
