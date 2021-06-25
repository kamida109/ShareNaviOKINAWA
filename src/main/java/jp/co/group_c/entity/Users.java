package jp.co.group_c.entity;

public class Users {

	// フィールド
	private Integer userId;
	private String userName;
	private Integer citiesId;
	private String citiesName;
	private Integer authorityId;
	private String loginId;
	private String password;

	public Users() {};

	public Users(Integer userId, String userName, Integer citiesId, String citiesName, Integer authorityId, String loginId, String password) {
		this.userId = userId;
		this.userName = userName;
		this.citiesId = citiesId;
		this.citiesName = citiesName;
		this.authorityId = authorityId;
		this.loginId = loginId;
		this.password = password;
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

	public Integer getCitiesId() {
		return citiesId;
	}
	public void setCitiesId(Integer citiesId) {
		this.citiesId = citiesId;
	}

	public String getCitiesName() {
		return citiesName;
	}
	public void setCitiesName(String citiesName) {
		this.citiesName = citiesName;
	}

	public Integer getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
