package jp.co.group_c.entity;

public class Category {

	private Integer categoryId;
	private String categoryName;
	private Integer mainCategory;

	public Category() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

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

}
