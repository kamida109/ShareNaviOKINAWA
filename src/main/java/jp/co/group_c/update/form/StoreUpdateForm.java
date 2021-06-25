package jp.co.group_c.update.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class StoreUpdateForm {
	private Integer storeId;

	private String businessHours;

	private Integer citiesId;

	private String address;

	private String tel;

	private Date insertDay;

	private Integer reviewId;

	private Integer hyouka;

	private Integer categoryId;

	private Integer subCategoryId;

	@NotBlank(message="店舗名は必須です")
	private String storeName;

	// アクセサ
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBusinessHours() {
		return businessHours;
	}
	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public Integer getCitiesId() {
		return citiesId;
	}
	public void setCitiesId(Integer citiesId) {
		this.citiesId = citiesId;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getInsertDay() {
		return insertDay;
	}
	public void setInsertDay(Date insertDay) {
		this.insertDay = insertDay;
	}

	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public Integer getHyouka() {
		return hyouka;
	}
	public void setHyouka(Integer hyouka) {
		this.hyouka = hyouka;
	}

	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

}
