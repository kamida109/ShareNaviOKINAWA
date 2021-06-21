package jp.co.group_c.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.group_c.dao.SearchDao;
import jp.co.group_c.entity.Category;
import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.Store;
import jp.co.group_c.service.SearchService;

@Service
@Transactional
public class SearchServiceImple implements SearchService{

	@Autowired
	private SearchDao searchDao;

	@Override
	public List<Cities> cities() {
		return searchDao.cities();
	}

	@Override
	public List<Category> mainCategory() {
		return searchDao.mainCategory();
	}

	@Override
	public List<Category> subCategory() {
		return searchDao.subCategory();
	}

	// 店舗検索
	@Override
	public List<Store> storeSearch(String storeName, Integer categoryId, Integer cityId, boolean hyouka) {
		return searchDao.storeSearch(storeName, categoryId, cityId, hyouka);
	}

	// あいまい検索
	@Override
	public List<Store> partStoreSearch(String storeName, boolean hyouka) {
		return searchDao.partStoreSearch(storeName, hyouka);
	}

}
