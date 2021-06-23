package jp.co.group_c.add_store_entity;

public class Category {

	private int categoryId;
	private String categoryName;
	private Integer mainCategory;


	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getMainCategory() {
		return mainCategory;
	}
	public void setMainCategory(Integer mainCategory) {
		this.mainCategory = mainCategory;
	}

}
