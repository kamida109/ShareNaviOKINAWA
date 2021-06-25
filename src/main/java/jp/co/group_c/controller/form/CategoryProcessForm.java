package jp.co.group_c.controller.form;

public class CategoryProcessForm {

	private Integer categoryId;
	private String categoryName;
	private Integer mainCategory;
	private String mainCategoryName;
	private String pCategoryName;
	private String process;

	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getMainCategory() {
		return mainCategory;
	}
	public void setMainCategory(Integer mainCategory) {
		this.mainCategory = mainCategory;
	}
	public String getpCategoryName() {
		return pCategoryName;
	}
	public void setpCategoryName(String pCategoryName) {
		this.pCategoryName = pCategoryName;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getMainCategoryName() {
		return mainCategoryName;
	}
	public void setMainCategoryName(String mainCategoryName) {
		this.mainCategoryName = mainCategoryName;
	}

}
