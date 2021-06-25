package jp.co.group_c.contact;

import javax.validation.constraints.NotNull;

//ユーザー削除用のFormクラス
public class UserDeleteForm {

	@NotNull(message="削除するユーザーIDを入力してください。")
	private Integer userId;

	private String userName;

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

