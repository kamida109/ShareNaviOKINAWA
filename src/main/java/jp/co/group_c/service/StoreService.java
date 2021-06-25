package jp.co.group_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.group_c.dao.StoreDao;
import jp.co.group_c.entity.Store;

@Service
public class StoreService {

	@Autowired
	StoreDao storeDao;

	public List<Store> favoriteStore(Integer userId) {
		return storeDao.favoriteStore(userId);
	}

	public List<Store> reviewStore(Integer userId) {
		return storeDao.reviewStore(userId);
	}

}
