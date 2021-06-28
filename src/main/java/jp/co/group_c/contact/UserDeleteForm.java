package jp.co.group_c.contact;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

//ユーザー削除用のFormクラス
public class UserDeleteForm {

	//@Pattern(regexp = "[0-9]*")
	@NotNull
	@Positive
	private Integer userId;

	//private String userName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer deleteUserId) {
		this.userId = deleteUserId;
	}

	/*
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	*/

}
