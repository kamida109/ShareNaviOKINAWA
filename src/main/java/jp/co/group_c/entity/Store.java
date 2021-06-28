package jp.co.group_c.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Store {
	private Integer storeId;
	private String storeName;
	private String businessHours;
	private Integer citiesId;
	private String citiesName;
	private String address;
	private String tel;
	private Date insertDay;
//	private String insertDay;
	private Integer categoryId;
	private String categoryName;
	private Integer mainCategory;
	private Integer imgId;
	private String paths;
	private Integer reviewId;
	private String review;
	private Integer hyouka;
	private Timestamp reviewDate;
//	private String reviewDate;
	private Integer userId;
	private String userName;

	public Store() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Store(Integer storeId, String storeName, String businessHours, Integer cityId, String address, String tel) {
		this.storeId = storeId;
		this.storeName = storeName;
		this.businessHours = businessHours;
		this.citiesId = cityId;
		this.address = address;
		this.tel = tel;
	}

	/* アクセサ */
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

	public String getCitiesName() {
		return citiesName;
	}
	public void setCitiesName(String citiesName) {
		this.citiesName = citiesName;
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

	public Integer getImgId() {
		return imgId;
	}
	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public String getPaths() {
		return paths;
	}
	public void setPaths(String paths) {
		this.paths = paths;
	}

	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	public Integer getHyouka() {
		return hyouka;
	}
	public void setHyouka(Integer hyouka) {
		this.hyouka = hyouka;
	}

	public Timestamp getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Timestamp reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
