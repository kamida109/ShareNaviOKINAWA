package jp.co.group_c.update.entity;

import java.util.Date;

public class Review {

	private Integer storeId;
	private Integer reviewId;
	private Integer userId;
	private String review;
	private Integer hyouka;
	private Date review_date;
	private String userName;

	public Review() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Review(int reviewId, Integer hyouka) {
		this.reviewId = reviewId;
		this.hyouka = hyouka;
	}

	// アクセサ
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	public Integer getHyouka() {
		return hyouka;
	}
	public void setHyouka(Integer hyouka) {
		this.hyouka = hyouka;
	}

	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



}
