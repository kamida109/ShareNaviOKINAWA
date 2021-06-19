package jp.co.group_c.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.group_c.dao.SearchDao;
import jp.co.group_c.entity.Store;
import jp.co.group_c.service.SearchService;

@Service
@Transactional
public class SearchServiceImple implements SearchService{

	@Autowired
	private SearchDao searchDao;

	// 店舗検索
	@Override
	public List<Store> storeSearch(String storeName, String category, String city, boolean hyouka) {
		return searchDao.storeSearch(storeName, category, city, hyouka);
	}

}
