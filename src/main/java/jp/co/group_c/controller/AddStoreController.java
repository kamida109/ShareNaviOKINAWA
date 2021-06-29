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

		session.removeAttribute("setImages");
		session.removeAttribute("storeList");
		session.removeAttribute("notList");
		session.removeAttribute("mainCategoryList");
		session.removeAttribute("planList");
		session.removeAttribute("notPlanList");

		List<City> cityList = addStoreService.allCity();
		List<Category> mainCateList = addStoreService.selectMainCategory();
		List<Category> childCateList = addStoreService.selectAllChildCategory();

		model.addAttribute("cities", cityList);
		model.addAttribute("mainCategory", mainCateList);
		model.addAttribute("childCategory", childCateList);

		return "add_store";
	}


	/*----------------------------------------------------------------------------------------------------*/



	// 店舗登録確認
	@RequestMapping(value = "/add_store_check", method = RequestMethod.POST)
	@Transactional
	public String addStoreCheck(@Validated @ModelAttribute("add_store") AddStoreForm form, BindingResult bindingResult,
								 @RequestParam("subCate1") String cateName1,
								 @RequestParam("subCate2") String cateName2,
								 @RequestParam("subCate3") String cateName3, Model model) /*throws IllegalStateException, IOException*/ {

		/*画像保存*/
		if(form.getStoreImages().get(0) != null) {

			List<MultipartFile> formImg = form.getStoreImages();
			String aaa = "";

			try {
				for (MultipartFile file : formImg) {

					String formFileName = file.getOriginalFilename();
					String imgUploadPath = context.getRealPath("/") + "/IMAGES/store/";
					File uploadFile = new File(imgUploadPath,formFileName);

					/*アクセスが拒否されました*/
					file.transferTo(uploadFile);

					aaa += "<img src=\"\\IMAGES\\store\\"+uploadFile.getName()+"\" style=\"width:400px;\">";
				}
				session.setAttribute("setImages", formImg);
				model.addAttribute("checkImage", aaa);
				model.addAttribute("imagesNum", formImg.size());

			} catch(IllegalStateException e) {
				System.out.println("☆--------IllegalStateException--------☆");

			} catch(IOException e) {
				System.out.println("☆--------IOException---------☆");

			}
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

		try {
			form.setMainCategoryName1(mainCate1.getCategoryName());
			form.setSubCategoryId1(subCate1.getCategoryId());
			form.setSubCategoryName1(subCate1.getCategoryName());
			model.addAttribute("cateName1", form.getSubCategoryName1());
		}catch (NullPointerException e) {
			System.out.println("★１");
		}
		try {
			form.setMainCategoryName2(mainCate2.getCategoryName());
			form.setSubCategoryId2(subCate2.getCategoryId());
			form.setSubCategoryName2(subCate2.getCategoryName());
			model.addAttribute("cateName2", form.getSubCategoryName2());
		}catch (NullPointerException e) {
			System.out.println("★２");
		}
		try {
			form.setMainCategoryName3(mainCate3.getCategoryName());
			form.setSubCategoryId3(subCate3.getCategoryId());
			form.setSubCategoryName3(subCate3.getCategoryName());
			model.addAttribute("cateName3", form.getSubCategoryName3());
		}catch (NullPointerException e) {
			System.out.println("★３");
		}

		return "add_store_check";
	}


/*----------------------------------------------------------------------------------------------------*/




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
		session.setAttribute("newStoreId", nowStoreId);

		//↓店舗カテゴリ
		if(form.getSubCategoryId1() != null) {
			addStoreService.addStoreCategory(nowStoreId, form.getSubCategoryId1());
		}
		if(form.getSubCategoryId2() != null) {
			addStoreService.addStoreCategory(nowStoreId, form.getSubCategoryId2());
		}
		if(form.getSubCategoryId3() != null) {
			addStoreService.addStoreCategory(nowStoreId, form.getSubCategoryId3());
		}


		//↑店舗カテゴリ

		//↓画像をテーブルに保存
		if(session.getAttribute("setImages") != null) {

			List<MultipartFile> formImg = (List<MultipartFile>)session.getAttribute("setImages");

			//画像の数だけループ
//			try {
				for (MultipartFile file : formImg) {
					String formFileName = file.getOriginalFilename();
					String imgUploadPath = "IMAGES/store/";
//					String imgDeletePath = context.getRealPath("/") + "/IMAGES/set/";

					File uploadFile = new File(imgUploadPath,formFileName);
//					File deleteFile = new File(imgDeletePath,formFileName);

//					file.transferTo(uploadFile);
					addStoreService.insertImages(nowStoreId, uploadFile.getPath());
//					deleteFile.delete();
				}
//			} catch(IllegalStateException e) {
//				System.out.println("☆--------IllegalStateException--------☆");
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}

		//↑画像をテーブルに保存

		return "add_store_result";
	}

	// 写真の追加
	@RequestMapping(value = "/addPhoto", method = RequestMethod.GET)
	public String addPhoto(@ModelAttribute("add_photo") AddStoreForm form,
							@RequestParam("storeId") Integer storeId, Model model) {

		model.addAttribute("nowStoreId",storeId);

		return "add_photo";
	}
	// 写真の追加確認＆ディレクトリに保存
	@RequestMapping(value = "/add_photo_check", method = RequestMethod.POST)
	public String addPhotoCheck(@ModelAttribute("add_photo") AddStoreForm form, Model model) {

		if(form.getStoreImages().get(0) != null) {

			List<MultipartFile> formImg = form.getStoreImages();
			String aaa = "";

			try {
				for (MultipartFile file : formImg) {

					String formFileName = file.getOriginalFilename();
					String imgUploadPath = context.getRealPath("/") + "/IMAGES/store/";
					File uploadFile = new File(imgUploadPath,formFileName);

					/*アクセスが拒否されました*/
					file.transferTo(uploadFile);

					aaa += "<img src=\"\\IMAGES\\store\\"+uploadFile.getName()+"\" style=\"width:400px;\">";
				}
				session.setAttribute("addImages", formImg);
				model.addAttribute("checkImage", aaa);
				model.addAttribute("imagesNum", formImg.size());

			} catch(IllegalStateException e) {
				System.out.println("☆--------IllegalStateException--------☆");

			} catch(IOException e) {
				System.out.println("☆--------IOException---------☆");

			}
		} else {
			model.addAttribute("errMes", "写真が選択されていません");
			return "add_photo";
		}

		return "add_photo_check";
	}




	// 写真パスをテーブルに追加
	@RequestMapping(value = "/add_photo_result", method = RequestMethod.POST)
	public String addPhoto(@Validated @ModelAttribute("add_photo") AddStoreForm form,
							BindingResult bindingResult, Model model) {

		int nowStoreId = form.getStoreId();

		//↓画像をテーブルに保存

		if(session.getAttribute("addImages") != null) {

			@SuppressWarnings("unchecked")
			List<MultipartFile> formImg = (List<MultipartFile>)session.getAttribute("addImages");

				for (MultipartFile file : formImg) {
					String formFileName = file.getOriginalFilename();
					String imgUploadPath = "IMAGES/store/";

					File uploadFile = new File(imgUploadPath,formFileName);

					addStoreService.insertImages(nowStoreId, uploadFile.getPath());



				}
		}

		return "add_photo_result";
	}

}