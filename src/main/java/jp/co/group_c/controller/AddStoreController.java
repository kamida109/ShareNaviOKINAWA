package jp.co.group_c.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jp.co.group_c.add_store_entity.Category;
import jp.co.group_c.add_store_entity.City;
import jp.co.group_c.add_store_service.AddStoreService;
import jp.co.group_c.controller.add_store_form.AddStoreForm;

@Controller
public class AddStoreController {


	@Autowired
	AddStoreService addStoreService;
    @Autowired
    ServletContext context;
    @Autowired
    HttpSession session;


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


	// 店舗登録確認
	@RequestMapping(value = "/add_store_check", method = RequestMethod.POST)
	public String addStoreCheck(@Validated @ModelAttribute("add_store") AddStoreForm form, BindingResult bindingResult,
								 @RequestParam("subCate1") String cateName1,
								 @RequestParam("subCate2") String cateName2,
								 @RequestParam("subCate3") String cateName3, Model model) throws IllegalStateException, IOException {

		System.out.println("form.getStoreImages()：" + form.getStoreImages());
		//System.out.println(form.getStoreImages().get(0).getOriginalFilename());



		/*画像保存*/
		if(form.getStoreImages() != null) {
			System.out.println("写真格納処理開始");

			List<MultipartFile> formImg = form.getStoreImages();

			for (MultipartFile file : formImg) {

				String formFileName = file.getOriginalFilename();
				String imgUploadPath = context.getRealPath("/") + "/IMAGES/store/";
				File uploadFile = new File(imgUploadPath,formFileName);
				System.out.println("getPath："+uploadFile.getPath());

				file.transferTo(uploadFile);
			}

			session.setAttribute("setImages", formImg);

			System.out.println("formImg："+ formImg);

			model.addAttribute("imagesNum", formImg.size());

			System.out.println("写真格納処理終了");
		}
		/*画像保存*/


		model.addAttribute("images", form.getStoreImages());
		if(bindingResult.hasErrors()) {
			List<City> cityList = addStoreService.allCity();
			List<Category> mainCateList = addStoreService.selectMainCategory();
			List<Category> childCateList = addStoreService.selectAllChildCategory();

			model.addAttribute("cities", cityList);
			model.addAttribute("mainCategory", mainCateList);
			model.addAttribute("childCategory", childCateList);
			return "add_store";
		}



		Category mainCate1 = addStoreService.searchCategory(form.getMainCategoryId1());
		Category mainCate2 = addStoreService.searchCategory(form.getMainCategoryId2());
		Category mainCate3 = addStoreService.searchCategory(form.getMainCategoryId3());
		Category subCate1 = addStoreService.searchCategory(cateName1);
		Category subCate2 = addStoreService.searchCategory(cateName2);
		Category subCate3 = addStoreService.searchCategory(cateName3);

		form.setAddress1Name(addStoreService.findCity(form.getAddress1()).getCitiesName());

		form.setMainCategoryName1(mainCate1.getCategoryName());
		form.setMainCategoryName2(mainCate2.getCategoryName());
		form.setMainCategoryName3(mainCate3.getCategoryName());

		try {
			form.setSubCategoryId1(subCate1.getCategoryId());
			form.setSubCategoryId2(subCate2.getCategoryId());
			form.setSubCategoryId3(subCate3.getCategoryId());

			form.setSubCategoryName1(subCate1.getCategoryName());
			form.setSubCategoryName2(subCate2.getCategoryName());
			form.setSubCategoryName3(subCate3.getCategoryName());
		} catch (NullPointerException e) {
			System.out.println("ぬるぽ");
		}

		return "add_store_check";
	}







	// 店舗登録処理
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/add_store_result", params = "update", method = RequestMethod.POST)
	@Transactional
	public String addStore(@Validated @ModelAttribute("add_store") AddStoreForm form,
							BindingResult bindingResult, Model model) {

		//↓店舗情報追加

		addStoreService.insertStore(form.getStoreName(), form.getWorkTime(), form.getAddress1(), form.getAddress2(), form.getTel());

		//↑店舗情報追加

		int nowStoreId = addStoreService.findStore(form.getStoreName(), form.getAddress1()).getStoreId();
		//↓店舗カテゴリ


//		addStoreService.addStoreCategory(nowStoreId, form.getSubCategoryId1());

		//↑店舗カテゴリ
		//↓画像をテーブルに保存

		System.out.println("form.getStoreImages()：" + form.getStoreImages());
		if(form.getStoreImages() != null) {



			List<MultipartFile> formImg = (List<MultipartFile>)session.getAttribute("setImages");

			System.out.println(formImg);

			for (MultipartFile file : formImg) {
				String formFileName = file.getOriginalFilename();
				//System.out.println("formFileName："+formFileName);
				String imgUploadPath = context.getRealPath("/") + "/IMAGES/store/";
				//System.out.println("imgUploadPath："+imgUploadPath);
				File uploadImg = new File(imgUploadPath,formFileName);
				//System.out.println("uplpadImg："+uploadImg.getName());
				//System.out.println("uplpadImg："+uploadImg.getPath());

				addStoreService.insertImages(nowStoreId, uploadImg.getPath()); //AbsolutePath = 絶対パス
			}
		}

		//↑画像をテーブルに保存

		return "add_store_result";
	}

}