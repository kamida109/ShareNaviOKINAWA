package jp.co.group_c.update.entity;

public class Favorite {

	private Integer userId;
	private Integer storeId;

	public Favorite() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Favorite(Integer userId, Integer storeId) {
		this.userId = userId;
		this.storeId = storeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

}
