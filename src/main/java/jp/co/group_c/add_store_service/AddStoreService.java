package jp.co.group_c.add_store_service;

import java.util.List;

import jp.co.group_c.add_store_entity.Category;
import jp.co.group_c.add_store_entity.City;
import jp.co.group_c.add_store_entity.Store;

public interface AddStoreService {

	public List<City> allCity();
	public City findCity(Integer citiesId);
	public List<Category> selectMainCategory();
	public List<Category> selectAllChildCategory();
	public List<Category> selectChildCategory(int mainCategory);
	public Category searchCategory(String cateName);
	public Category searchCategory(Integer cateId);
	public void insertStore(String storeName, String businessHours, Integer citiesId, String address, String tel);
	public void insertImages(int nowStoreId, String paths);
	public Store findStore(String storeName, Integer citiesId);
	public void addStoreCategory(int nowStoreId, Integer subCategoryId1);
}
