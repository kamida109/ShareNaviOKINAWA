package jp.co.group_c.controller.form;

public class SearchForm {

	private String storeName;
	private String category;
	private String city;
	private boolean hyouka;
	private Integer categoryId;
	private Integer citiesId;

	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isHyouka() {
		return hyouka;
	}
	public void setHyouka(boolean hyouka) {
		this.hyouka = hyouka;
	}

	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCitiesId() {
		return citiesId;
	}
	public void setCitiesId(Integer citiesId) {
		this.citiesId = citiesId;
	}



}
