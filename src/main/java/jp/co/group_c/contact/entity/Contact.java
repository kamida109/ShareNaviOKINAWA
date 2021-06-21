package jp.co.group_c.contact.entity;

//問い合わせ用エンティティ
//contactテーブルの全カラム＋usersテーブルからuser_nameも
public class Contact {

	private Integer contactId;
	private Integer userId;
	private Integer contactCategoryId;
	private String contents;
	private boolean flag;
	private String userName;

	//コンストラクター引数なし
	public Contact() {

	}
	//コンストラクター問い合わせ内容登録用
	public Contact(Integer userId, Integer contactCategoryId, String contents, boolean flag) {
		this.userId = userId;
		this.contactCategoryId =contactCategoryId;
		this.contents = contents;
		this.flag = flag;
	}



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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


}
