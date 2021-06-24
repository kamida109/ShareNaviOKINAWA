package jp.co.group_c.add_store_service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.group_c.add_store_dao.AddStoreDao;
import jp.co.group_c.add_store_entity.Category;
import jp.co.group_c.add_store_entity.City;
import jp.co.group_c.add_store_entity.Store;
import jp.co.group_c.add_store_service.AddStoreService;

@Service
public class AddStoreServiceImpl implements AddStoreService{

	@Autowired
	AddStoreDao addStoreDao;

	public List<City> allCity() {
		return addStoreDao.allCity();
	}

	@Override
	public City findCity(Integer citiesId) {
		return addStoreDao.findCity(citiesId);
	}

	@Override
	public List<Category> selectMainCategory() {
		return addStoreDao.selectMainCategory();
	}

	@Override
	public List<Category> selectChildCategory(int mainCategory) {
		return addStoreDao.selectChildCategory(mainCategory);
	}

	@Override
	public List<Category> selectAllChildCategory() {
		return addStoreDao.selectAllChildCategory();
	}

	@Override
	public Category searchCategory(String categoryName) {
		return addStoreDao.searchCategory(categoryName);
	}

	@Override
	public Category searchCategory(Integer cateId) {
		return addStoreDao.searchCategory(cateId);
	}

	@Override
	public void insertStore(String storeName, String businessHours, Integer citiesId, String address, String tel) {
		addStoreDao.insertStore(storeName, businessHours, citiesId, address, tel);
	}

	@Override
	public void insertImages(int storeId, String paths) {
		addStoreDao.insertImages(storeId, paths);
	}

	@Override
	public Store findStore(String storeName, Integer citiesId) {
		return addStoreDao.findStore(storeName, citiesId);
	}

	@Override
	public void addStoreCategory(int nowStoreId, Integer categoryId) {
		addStoreDao.addStoreCategory(nowStoreId, categoryId);

	}




}
