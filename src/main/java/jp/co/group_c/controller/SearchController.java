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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.group_c.controller.form.SearchForm;
import jp.co.group_c.entity.Category;
import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.FavoriteCategory;
import jp.co.group_c.entity.Store;
import jp.co.group_c.service.search.SearchService;

@Controller
public class SearchController {

	// 店舗検索画面に飛ぶ
	@Autowired
	private SearchService searchService;

	@Autowired
	HttpSession session;

	@Autowired
	HttpServletRequest request;

	// 店舗検索画面
	@RequestMapping(value = "/search")
	public String jumpSearch(@ModelAttribute("userInfo") SearchForm form, Model model) {

		session.removeAttribute("setImages");

		List<Cities> citiesList = searchService.cities();
		session.setAttribute("cities", citiesList);

		List<Category> mainCategoryList = searchService.mainCategory();
		session.setAttribute("mainCategory", mainCategoryList);

		return "search";
	}

	// セレクトタグを非同期で切替
	@RequestMapping(value="/pulldown/{value}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String changeCategory(@PathVariable("value")String value) {
		Integer val = Integer.parseInt(value);
		String str = "";
		List<Category> subCategoryList = searchService.subCategory(val);

		for(Category c : subCategoryList) {
			str += "<option value=\"" + c.getCategoryId() +"\"" + "label=\""+ c.getCategoryName() + "\">" +  c.getCategoryName() + "</option>";
		}

		return str;

	}

	// セレクトタグを非同期で切替
	@RequestMapping(value="/selected/{value}/{number}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String selectCategory(@PathVariable("value")String value, @PathVariable("number")String number) {

		Integer val = Integer.parseInt(value);
		String str = "";
		List<Category> subCategoryList = searchService.subCategory(val);

		Integer num = Integer.parseInt(number);
		List<FavoriteCategory> list = (List<FavoriteCategory>)session.getAttribute("favoriteCategory");

		for(Category c : subCategoryList) {
			if(c.getCategoryId() == list.get(num).getCategoryId()){
				str += "<option value=\"" + c.getCategoryId() +"\"" + "label=\""+ c.getCategoryName() + "\" selected>" +  c.getCategoryName() + "</option>";
			}else{
				str += "<option value=\"" + c.getCategoryId() +"\"" + "label=\""+ c.getCategoryName() + "\">" +  c.getCategoryName() + "</option>";
			}
		}

		return str;

	}


	// 検索を非同期
	@RequestMapping(value="/result/{keyWord}/{subCategory}/{prace}/{check}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String searchResult(@PathVariable("keyWord")String keyWord, @PathVariable("subCategory")String subCategory,
			@PathVariable("prace")String prace, @PathVariable("check")String check, Model model) {

		session.removeAttribute("storeList");
		session.removeAttribute("planList");
		session.removeAttribute("notList");
		session.removeAttribute("notPlanList");
		session.removeAttribute("mainCategoryList");

		// サブカテゴリが未選択の時
		if(subCategory.equals("------------")) {
			session.setAttribute("notList", "undefinde");
			return "redirect:search";
		}

		if(keyWord.isEmpty()) {
			return "redirect:search";
		}

		Integer intSubCategory = Integer.parseInt(subCategory);
		Integer intPrace = Integer.parseInt(prace);
		boolean boolCheck = Boolean.valueOf(check);

		List<Store> storeList = searchService.storeSearch(keyWord, intSubCategory, intPrace, boolCheck);

		// 店舗検索
		if(!storeList.isEmpty()) {
			session.setAttribute("storeList", storeList);
		} else {
			session.setAttribute("notList", "undefinde");
		}

		// あいまい検索用にキーワードの前後に「%」をつける
		String index = "%" + keyWord + "%";
		// あいまい検索
		List<Store> partStoreList = searchService.partStoreSearch(index, boolCheck);
		if(!partStoreList.isEmpty()) {
			session.setAttribute("planList", partStoreList);
		} else {
			session.setAttribute("notPlanList", "undefinde");
		}

		List<Store> storeCategoryList = searchService.storeCategory();
		session.setAttribute("mainCategoryList", storeCategoryList);

		return null;
	}

	// 店舗詳細画面
	@RequestMapping(value = "/details")
	public String details(@RequestParam("storeId") Integer id, Model model) {

		List<Store> storeDitails = searchService.storeDitails(id);
		model.addAttribute("storeDitails", storeDitails);

		return "details";
	}

	// レビューを非同期で変更
	@RequestMapping(value="/inputReview/{storeId}/{newReview}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void newReview(@PathVariable("storeId") Integer id, @PathVariable("newReview") String review) {

		searchService.reviewUpdate(id, review);

	}

	// レビュー削除
	@RequestMapping(value="/reviewDel/{storeId}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void reviewDel(@PathVariable("storeId") Integer id) {

		searchService.reviewDelete(id);

	}


	// 店舗削除を非同期で実装
	@RequestMapping(value="/storeDelete/{storeId}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void storeDelete(@PathVariable("storeId") Integer id) {

		searchService.storeDelete(id);
	}

}