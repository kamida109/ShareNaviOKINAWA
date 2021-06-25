package jp.co.group_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.group_c.category_process.service.CategoryProcessService;
import jp.co.group_c.controller.form.CategoryProcessForm;
import jp.co.group_c.entity.Category;
import jp.co.group_c.service.search.SearchService;

@Controller
public class CategoryProcessController {

	@Autowired
	private CategoryProcessService categoryProcessService;
	@Autowired
	private SearchService searchService;

	/*category_process.jsp遷移時の共通処理*/
	private void defaultCategorySet(Model model) {
		List<Category> mainCateList = categoryProcessService.selectMainCategory();
		List<Category> subCateList = categoryProcessService.selectAllChildCategory();
		model.addAttribute("mainCategory", mainCateList);
		model.addAttribute("subCategory", subCateList);
	}


	//カテゴリ操作画面遷移
	@RequestMapping(value = "/category_process", method = RequestMethod.GET)
	public String categoryProcess(@ModelAttribute("category_process") CategoryProcessForm form, Model model) {

		defaultCategorySet(model);
		return "category_process";
	}




	//カテゴリ操作確認画面
	@RequestMapping(value = "/category_process_check", method = RequestMethod.POST)
	public String categoryProcessCheck(@Validated @ModelAttribute("category_process") CategoryProcessForm form,
										BindingResult bindingResult,
										@RequestParam("subCate") String subCateName, Model model) {

		System.out.println("処理：" + form.getProcess());
		System.out.println("親カテゴリ：" + form.getMainCategory());
		if(subCateName.isEmpty()) {
			subCateName = null;
		}
		if(bindingResult.hasErrors()) {
			defaultCategorySet(model);
			return "category_process";
		}
		String pCateName = form.getpCategoryName();

		//既にあるカテゴリ名を入力した場合は、元の画面に戻る

		if(categoryProcessService.findExistCategory(pCateName)) {
			defaultCategorySet(model);
			model.addAttribute("existErr", "そのカテゴリ名は既にあります。");
			return "category_process";
		}
		Category mainCate = categoryProcessService.findCategory(form.getMainCategory());
		Category subCate = categoryProcessService.findCategory(subCateName);




		//追加処理		メイン：両方（一般は必要）    サブ：不要    入力：必要
		if(form.getProcess().equals("追加") || form.getProcess() == null) {
			//親カテゴリが選択されている場合
			if(mainCate != null) {
				if(form.getpCategoryName().isEmpty()) {
					model.addAttribute("lackErr", "カテゴリ名が入力されていません");
					defaultCategorySet(model);
					return "category_process";
				}
				form.setMainCategoryName(mainCate.getCategoryName());
			} else {

//				if(権限IDがNULL) {
//					model.addAttribute("lackErr", "親カテゴリ名が選択されていません");
//					defaultCategorySet(model);
//					return "category_process";
//				}
				System.out.println("ニューカテゴリ："+form.getpCategoryName());

				//新規カテゴリ名未入力
				if(form.getpCategoryName().isEmpty()) {
					model.addAttribute("lackErr", "カテゴリ名が入力されていません");
					defaultCategorySet(model);
					return "category_process";
				}
			}

		//更新処理		メイン：必要    サブ：必要    入力：必要
		} else if(form.getProcess().equals("更新")) {
			if(mainCate != null) {
				if(subCate.getCategoryId() != null) {
					if(form.getpCategoryName().isEmpty()) {
						model.addAttribute("lackErr", "カテゴリ名が入力されていません");

						defaultCategorySet(model);
						return "category_process";
					}
					form.setCategoryId(subCate.getCategoryId());
					form.setCategoryName(subCate.getCategoryName());

					System.out.println("更新前カテゴリ："+form.getpCategoryName());
					System.out.println("更新後カテゴリ："+form.getpCategoryName());

				} else {
					System.out.println("更新：サブカテゴリが選択されていません。");
					model.addAttribute("lackErr", "サブカテゴリが選択されていません。");

					defaultCategorySet(model);
					return "category_process";
				}

				form.setMainCategoryName(mainCate.getCategoryName());
			} else {
				System.out.println("更新：メインカテゴリが選択されていません。");
					defaultCategorySet(model);
					return "category_process";
			}

		//削除処理		メイン：両方    サブ：必要    入力：不要
		} else {
			System.out.println("削除するカテゴリ："+form.getCategoryName());
			if(mainCate != null) {
				if(subCate.getCategoryId() != null) {
					form.setCategoryId(subCate.getCategoryId());
					form.setCategoryName(subCate.getCategoryName());
				} else {
					System.out.println("削除：サブカテゴリが選択されていません。");
					model.addAttribute("lackErr", "サブカテゴリが選択されていません。");

					defaultCategorySet(model);
					return "category_process";
				}
				form.setMainCategoryName(mainCate.getCategoryName());
			} else {
				if(form.getpCategoryName().isEmpty()) {
					model.addAttribute("lackErr", "メインカテゴリが選択されていません");
					defaultCategorySet(model);
					return "category_process";
				}
			}
		}


		if(mainCate != null) {
			if(subCate.getCategoryId() != null) {
				form.setCategoryId(subCate.getCategoryId());
				form.setCategoryName(subCate.getCategoryName());
			}
			form.setMainCategoryName(mainCate.getCategoryName());
		} else {
			System.out.println("ニューカテゴリ："+form.getpCategoryName());

			if(form.getpCategoryName().isEmpty()) {
				model.addAttribute("lackErr", "カテゴリ名が入力されていません");
				defaultCategorySet(model);
				return "category_process";
			}
		}
		return "category_process_check";
	}






	//カテゴリ操作決定
	@RequestMapping(value = "/category_process_result", method = RequestMethod.POST)
	@Transactional
	public String categoryProcessResult(@ModelAttribute("category_process") CategoryProcessForm form, Model model) {

		Category mainCate = categoryProcessService.findCategory(form.getMainCategoryName());
		Category subCate = categoryProcessService.findCategory(form.getCategoryName());

		System.out.println("★");
		if(form.getProcess().equals("追加")) {
			System.out.println(form.getMainCategoryName());
			if(mainCate != null) {
				System.out.println("メインカテゴリにインサート");
//				categoryProcessService.insertCategory(form.getpCategoryName());
			} else {
				System.out.println("メインカテゴリをインサート");
//				categoryProcessService.insertCategory(form.getpCategoryName(), form.getMainCategory());
			}
			model.addAttribute("resultMessage", "カテゴリ"+ form.getpCategoryName() +"を追加しました。");

		} else if(form.getProcess().equals("更新")) {
			if(mainCate != null) {
				System.out.println("サブカテゴリのアップデート");
			} else {
				System.out.println("メインカテゴリが入力されていません");
				defaultCategorySet(model);
				return "category_process";
			}
			model.addAttribute("resultMessage", "カテゴリ"+ form.getCategoryName() +"を"+ form.getpCategoryName() +"に更新しました。");

		} else if(form.getProcess().equals("削除")) {
			if(mainCate != null) {
				System.out.println("サブカテゴリの削除");
			} else {
				System.out.println("メインカテゴリが入力されていません");
				defaultCategorySet(model);
				return "category_process";
			}
			model.addAttribute("resultMessage", "カテゴリ"+ form.getCategoryName() +"を"+ form.getpCategoryName() +"に更新しました。");
		} else {

		}
		return "category_process_result";
	}



	@RequestMapping(value="/pulldown2/{value}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String changeCategory(@PathVariable("value")String value) {
		Integer val = Integer.parseInt(value);
		String str = "";
		if(val == 0) {
			str += "<option value=\"\">-------------</option>";
		} else {
			List<Category> subCategoryList = searchService.subCategory(val);

			for(Category c : subCategoryList) {
				str += "<option name=\""+ c.getCategoryName() +"\">" +  c.getCategoryName() + "</option>";
			}
		}

		return str;

	}

}
