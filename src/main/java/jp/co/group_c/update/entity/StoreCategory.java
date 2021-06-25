package jp.co.group_c.update.entity;

public class StoreCategory {

	private Integer storeId;
	private Integer categoryId1;
	private Integer categoryId2;
	private Integer categoryId3;

	public StoreCategory() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public StoreCategory(Integer id, Integer category1, Integer category2, Integer category3) {
		this.storeId = id;
		this.categoryId1 = category1;
		this.categoryId2 = category2;
		this.categoryId3 = category3;
	}

	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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
