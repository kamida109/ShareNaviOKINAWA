package jp.co.group_c.contact;

public class UserManagementForm {
	//Formクラスは、jspの入力フォームからの値を入れる。入力フォーム文のフィールド定義する

	//フィールド
		private String loginId;
		private String userName;

		//ゲッターセッター
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

}
