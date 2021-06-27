package jp.co.group_c.update.entity;

public class Favorite {

	private Integer userid;
	private Integer storeId;

	public Favorite() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Favorite(Integer userId, Integer storeId) {
		this.userid = userId;
		this.storeId = storeId;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

}
