package jp.co.group_c.add_store_service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.group_c.add_store_dao.AddStoreDao;
import jp.co.group_c.add_store_entity.Category;
import jp.co.group_c.add_store_entity.City;
import jp.co.group_c.add_store_service.AddStoreService;

@Service
public class AddStoreServiceImpl implements AddStoreService{

	@Autowired
	AddStoreDao addStoreDao;

	public List<City> allCity() {
		return addStoreDao.allCity();
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

}
