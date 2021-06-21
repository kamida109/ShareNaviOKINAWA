package jp.co.group_c.controller.form;

public class UserInfoForm {

	private String loginId;
	private String userName;
	private Integer citiesId;
	private Integer categoryId1;
	private Integer categoryId2;
	private Integer categoryId3;

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCitiesId() {
		return citiesId;
	}
	public void setCitiesId(Integer citiesId) {
		this.citiesId = citiesId;
	}

	public Integer getCategoryId1() {
		return categoryId1;
	}
	public void setCategoryId1(Integer categoryId1) {
		this.categoryId1 = categoryId1;
	}

	public Integer getCategoryId2() {
		return categoryId2;
	}
	public void setCategoryId2(Integer categoryId2) {
		this.categoryId2 = categoryId2;
	}

	public Integer getCategoryId3() {
		return categoryId3;
	}
	public void setCategoryId3(Integer categoryId3) {
		this.categoryId3 = categoryId3;
	}

}
