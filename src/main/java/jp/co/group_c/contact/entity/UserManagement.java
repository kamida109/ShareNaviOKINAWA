package jp.co.group_c.contact.entity;

//ユーザー管理用エンティティ
//usersテーブルと、storeテーブルから
public class UserManagement {

	private Integer userId;
	private String userName;
	private String loginId;

	//引数なしコンストラクター
	public UserManagement() {

	}
	//ユーザー管理検索用コンストラクター
	public UserManagement(Integer userId, String userName) {
		this.setUserId(userId);
		this.setUserName(userName);
	}
	//ゲッターセッター
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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

}
