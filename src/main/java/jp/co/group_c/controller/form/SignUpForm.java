package jp.co.group_c.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SignUpForm {

	@NotBlank
	private String userName;

	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String loginId;

	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String password;

	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String passwordRe;

	private Integer citiesId;

	private Integer categoryId1;
	private Integer categoryId2;
	private Integer categoryId3;
	private Integer mainCategoryId1;
	private Integer mainCategoryId2;
	private Integer mainCategoryId3;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRe() {
		return passwordRe;
	}
	public void setPasswordRe(String passwordRe) {
		this.passwordRe = passwordRe;
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

	public Integer getMainCategoryId1() {
		return mainCategoryId1;
	}
	public void setMainCategoryId1(Integer mainCategoryId1) {
		this.mainCategoryId1 = mainCategoryId1;
	}

	public Integer getMainCategoryId2() {
		return mainCategoryId2;
	}
	public void setMainCategoryId2(Integer mainCategoryId2) {
		this.mainCategoryId2 = mainCategoryId2;
	}

	public Integer getMainCategoryId3() {
		return mainCategoryId3;
	}
	public void setMainCategoryId3(Integer mainCategoryId3) {
		this.mainCategoryId3 = mainCategoryId3;
	}

}
