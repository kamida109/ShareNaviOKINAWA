package jp.co.group_c.contact;

public class UserManagementForm {
	//Formクラスは、jspの入力フォームからの値を入れる。入力フォーム文のフィールド定義する

	//フィールド
		private Integer userId;
		private String userName;



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

}