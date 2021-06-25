package jp.co.group_c.contact;

import javax.validation.constraints.NotEmpty;

//一般ユーザー用の問い合わせ本文入力するクラス
//contactテーブルに登録する
public class InqueryForm {

	private Integer contactId;
	private Integer userId;
	private String userName;
	private Integer contactCategoryId;

	@NotEmpty(message="本文を入力してください。")
	private String contents;
	private boolean flag;

	//ゲッターセッター

	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
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
	public Integer getContactCategoryId() {
		return contactCategoryId;
	}
	public void setContactCategoryId(Integer contactCategoryId) {
		this.contactCategoryId = contactCategoryId;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}



}
