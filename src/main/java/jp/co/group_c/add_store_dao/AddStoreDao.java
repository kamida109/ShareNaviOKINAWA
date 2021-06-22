package jp.co.group_c.add_store_dao;

import java.util.List;

import jp.co.group_c.add_store_entity.Category;
import jp.co.group_c.add_store_entity.City;

public interface AddStoreDao {

	public List<City> allCity();
	public List<Category> selectMainCategory();
	public List<Category> selectChildCategory(int mainCategory);
	public List<Category> selectAllChildCategory();
}
