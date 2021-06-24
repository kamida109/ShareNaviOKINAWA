package jp.co.group_c.entity;

public class FavoriteCategory {

	private Integer userId;
	private Integer categoryId;
	private String categoryName;
	private Integer mainCategory;

	public FavoriteCategory() {	}

	public FavoriteCategory(Integer userId, Integer categoryId, String categoryName, Integer mainCategory) {
		this.userId = userId;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.mainCategory = mainCategory;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
