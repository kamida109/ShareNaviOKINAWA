package jp.co.group_c.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.group_c.controller.form.SearchForm;
import jp.co.group_c.entity.Category;
import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.Store;
import jp.co.group_c.service.SearchService;

@Controller
public class SearchController {

	// 店舗検索画面に飛ぶ
	@Autowired
	private SearchService searchService;

	@Autowired
	HttpSession session;

	// 店舗検索画面
	@RequestMapping(value = "/search")
	public String jumpSearch(@ModelAttribute("userInfo") SearchForm form, Model model) {
		List<Cities> citiesList = searchService.cities();
		session.setAttribute("cities", citiesList);

		List<Category> mainCategoryList = searchService.mainCategory();
		session.setAttribute("mainCategory", mainCategoryList);

		List<Category> subCategoryList = searchService.subCategory();
		session.setAttribute("subCategory", subCategoryList);

		return "search";
	}


	// 検索結果
	@RequestMapping(value = "/searchResult", method=RequestMethod.GET)
	public String searchResult(@ModelAttribute("userInfo") SearchForm form, Model model) {

		System.out.println(form.getMainCategoryId());
		System.out.println(form.getSubCategoryId());

		List<Store> storeList = searchService.storeSearch(form.getStoreName(), form.getSubCategoryId(), form.getCitiesId(), form.isHyouka());

		// 店舗検索
		if(storeList.isEmpty()) {
			model.addAttribute("selectResult", "対象のデータはありませんでした");
		} else {
			model.addAttribute("selectResult", storeList);
		}

		// あいまい検索用にキーワードの前後に「%」をつける
		String index = "%" + form.getStoreName() + "%";
		// あいまい検索
		List<Store> partStoreList = searchService.partStoreSearch(index, form.isHyouka());

		if(partStoreList.isEmpty()) {
			model.addAttribute("selectResult", "対象のデータはありませんでした");
		} else {
			model.addAttribute("selectPartResult", partStoreList);
		}

		return "search";
	}

	// 店舗詳細画面
	@RequestMapping(value = "/details"/*"/details/{id}"*/)
	public String details() {
		return "details";
	}

}