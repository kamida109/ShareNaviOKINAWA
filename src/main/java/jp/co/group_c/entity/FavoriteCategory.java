package jp.co.group_c.entity;

public class FavoriteCategory {

	private Integer userId;
	private Integer categoryId;

	public FavoriteCategory() {	}

	public FavoriteCategory(Integer userId, Integer categoryId) {
		this.userId = userId;
		this.categoryId = categoryId;
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

}
