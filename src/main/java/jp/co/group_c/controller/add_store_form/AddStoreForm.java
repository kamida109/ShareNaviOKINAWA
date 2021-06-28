package jp.co.group_c.controller.add_store_form;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class AddStoreForm {

	private Integer storeId;
	@NotBlank(message="店舗名は必須です。")
	private String storeName;
	private Integer mainCategoryId1;
	private Integer mainCategoryId2;
	private Integer mainCategoryId3;
	private String mainCategoryName1;
	private String mainCategoryName2;
	private String mainCategoryName3;
	private Integer subCategoryId1;
	private Integer subCategoryId2;
	private Integer subCategoryId3;
	private String subCategoryName1;
	private String subCategoryName2;
	private String subCategoryName3;
	private Integer address1;
	private String address1Name;
	private String address2;
	private String workTime;
	private String tel;

	private MultipartFile storeImage;
	private List<MultipartFile> storeImages;



	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getMainCategoryId1() {
		return mainCategoryId1;
	}
	public void setMainCategoryId1(Integer mainCategoryId) {
		this.mainCategoryId1 = mainCategoryId;
	}
	public Integer getMainCategoryId2() {
		return mainCategoryId2;
	}
	public void setMainCategoryId2(Integer mainCategoryId) {
		this.mainCategoryId2 = mainCategoryId;
	}
	public Integer getMainCategoryId3() {
		return mainCategoryId3;
	}
	public void setMainCategoryId3(Integer mainCategoryId) {
		this.mainCategoryId3 = mainCategoryId;
	}
	public Integer getSubCategoryId1() {
		return subCategoryId1;
	}
	public void setSubCategoryId1(Integer subCategoryId) {
		this.subCategoryId1 = subCategoryId;
	}
	public Integer getSubCategoryId2() {
		return subCategoryId2;
	}
	public void setSubCategoryId2(Integer subCategoryId) {
		this.subCategoryId2 = subCategoryId;
	}
	public Integer getSubCategoryId3() {
		return subCategoryId3;
	}
	public void setSubCategoryId3(Integer subCategoryId) {
		this.subCategoryId3 = subCategoryId;
	}
	public String getMainCategoryName1() {
		return mainCategoryName1;
	}
	public void setMainCategoryName1(String mainCategoryName1) {
		this.mainCategoryName1 = mainCategoryName1;
	}
	public String getMainCategoryName2() {
		return mainCategoryName2;
	}
	public void setMainCategoryName2(String mainCategoryName2) {
		this.mainCategoryName2 = mainCategoryName2;
	}
	public String getMainCategoryName3() {
		return mainCategoryName3;
	}
	public void setMainCategoryName3(String mainCategoryName3) {
		this.mainCategoryName3 = mainCategoryName3;
	}
	public String getSubCategoryName1() {
		return subCategoryName1;
	}
	public void setSubCategoryName1(String subCategoryName1) {
		this.subCategoryName1 = subCategoryName1;
	}
	public String getSubCategoryName2() {
		return subCategoryName2;
	}
	public void setSubCategoryName2(String subCategoryName2) {
		this.subCategoryName2 = subCategoryName2;
	}
	public String getSubCategoryName3() {
		return subCategoryName3;
	}
	public void setSubCategoryName3(String subCategoryName3) {
		this.subCategoryName3 = subCategoryName3;
	}


	public Integer getAddress1() {
		return address1;
	}
	public void setAddress1(Integer address1) {
		this.address1 = address1;
	}
	public String getAddress1Name() {
		return address1Name;
	}
	public void setAddress1Name(String address1Name) {
		this.address1Name = address1Name;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public MultipartFile getStoreImage() {
		return storeImage;
	}
	public void setStoreImage(MultipartFile storeImage) {
		this.storeImage = storeImage;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public List<MultipartFile> getStoreImages() {
		return storeImages;
	}
	public void setStoreImages(List<MultipartFile> storeImages) {
		this.storeImages = storeImages;
	}

}
