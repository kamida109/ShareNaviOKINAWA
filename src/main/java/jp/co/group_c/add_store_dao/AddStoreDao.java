package jp.co.group_c.add_store_dao;

import java.util.List;

import jp.co.group_c.add_store_entity.Category;
import jp.co.group_c.add_store_entity.City;
import jp.co.group_c.add_store_entity.Store;

public interface AddStoreDao {

	public List<City> allCity();
	public City findCity(Integer citiesId);
	public List<Category> selectMainCategory();
	public List<Category> selectChildCategory(int mainCategory);
	public List<Category> selectAllChildCategory();
	public Category searchCategory(String categoryName);
	public Category searchCategory(Integer categoryId);
	public void insertImages(int storeId, String paths);
	public void insertStore(String storeName, String businessHours, Integer citiesId, String address, String tel);
	public Store findStore(String storeName, Integer citiesId);
	public void addStoreCategory(int nowStoreId, Integer categoryId);

}
