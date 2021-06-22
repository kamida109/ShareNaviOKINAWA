package jp.co.group_c.add_store_service;

import java.util.List;

import jp.co.group_c.add_store_entity.Category;
import jp.co.group_c.add_store_entity.City;

public interface AddStoreService {

	public List<City> allCity();
	public List<Category> selectMainCategory();
	public List<Category> selectAllChildCategory();
	public List<Category> selectChildCategory(int mainCategory);


}
