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
import jp.co.group_c.entity.Users;
import jp.co.group_c.homeService.HomeService;
import jp.co.group_c.search.service.SearchService;
import jp.co.group_c.update.entity.Favorite;
import jp.co.group_c.update.entity.Review;


@Controller
public class SearchController {

	// 店舗検索画面に飛ぶ
	@Autowired
	private SearchService searchService;

	@Autowired
	private HomeService homeService;

	@Autowired
	HttpSession session;

	// 店舗検索画面に飛ぶ
	@Autowired
	HttpServletRequest request;

	// 店舗検索画面
	@RequestMapping(value = "/search")
	public String jampSearch(@ModelAttribute("userInfo") SearchForm form, Model model) {

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

		keyWord = (keyWord.equals("empty")) ? "" : keyWord;

		// あいまい検索用にキーワードの前後に「%」をつける
		String index = "%" + keyWord + "%";

		Integer intSubCategory = (subCategory.equals("------------")) ? null : Integer.parseInt(subCategory);
		Integer intPrace = (prace.equals("0")) ? null : Integer.parseInt(prace);
		boolean boolCheck = Boolean.valueOf(check);

		List<Store> storeList = searchService.storeSearch(index, intSubCategory, intPrace, boolCheck);

		// 店舗検索
		if(!storeList.isEmpty()) {
			session.setAttribute("storeList", storeList);
		} else {
			session.setAttribute("notList", "undefinde");
		}


		// あいまい検索
		List<Store> partStoreList = searchService.partStoreSearch(index, boolCheck);
		if(!partStoreList.isEmpty()) {
			session.setAttribute("planList", partStoreList);
		} else {
			session.setAttribute("notPlanList", "undefinde");
		}

		List<Store> storeCategoryList = searchService.storeCategory();
		session.setAttribute("mainCategoryList", storeCategoryList);

		List<Store> imageList = homeService.image();
		session.setAttribute("imageList", imageList);

		return null;
	}

	// 店舗詳細画面
	@RequestMapping(value = "/details")
	public String details(@RequestParam("storeId") Integer id, Model model) {

		List<Store> storeDitails = searchService.storeDitails(id);
		session.setAttribute("storeDitails", storeDitails);

		List<Store> storeCategoryList = searchService.storeCategory();
		session.setAttribute("mainCategoryList", storeCategoryList);

		// レビューテーブルの取得
		List<Review> reviewList = searchService.reviewList(id);
		session.setAttribute("reviewList", reviewList);

		// ユーザー情報の取得
		Users userInfo = (Users)session.getAttribute("signInUser");

		// お気に入りに登録されているお店情報を取得
		List<Favorite> favoriteStore = searchService.favoriteStore();

		List<Store> imageList = homeService.image();
		session.setAttribute("imageList", imageList);

		for(Favorite f : favoriteStore) {

			if(f.getUserId()==userInfo.getUserId() && f.getStoreId()==id) {
				model.addAttribute("flag", "userFavorite");
			}
		}

		for(Review r : reviewList) {
			if(r.getUserId()==userInfo.getUserId()) {
				model.addAttribute("review", "review");
			}
		}

		return "details";
	}

	// レビューを非同期で変更
	@RequestMapping(value="/inputReview/{reviewId}/{newReview}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void newReview(@PathVariable("reviewId") Integer id, @PathVariable("newReview") String review) {

		searchService.reviewUpdate(id, review);

	}

	// レビュー削除
	@RequestMapping(value="/reviewDel/{reviewId}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void reviewDel(@PathVariable("reviewId") List<Integer> id) {

		for(Integer i : id) {
			searchService.reviewDelete(i.intValue());
		}

	}

	// レビューの追加
	@RequestMapping(value="/insertReview/{storeId}/{userId}/{newReview}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void reviewDel(@PathVariable("storeId") Integer sId, @PathVariable("userId") Integer uId, @PathVariable("newReview") String review) {

		searchService.insertReview(sId, uId, review);
	}


	// 店舗削除を非同期で実装
	@RequestMapping(value="/storeDelete/{storeId}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void storeDelete(@PathVariable("storeId") Integer id) {

		searchService.storeDelete(id);
		session.removeAttribute("storeDitails");
		session.removeAttribute("mainCategoryList");
	}

	// 店舗詳細で戻るボタンが押された
	@RequestMapping(value="/returnSearch/", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void returnSearch() {

		session.removeAttribute("storeList");
		session.removeAttribute("planList");
		session.removeAttribute("notList");
		session.removeAttribute("notPlanList");
		session.removeAttribute("mainCategoryList");

	}

}