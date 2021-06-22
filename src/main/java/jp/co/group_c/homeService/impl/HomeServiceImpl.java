package jp.co.group_c.homeService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.group_c.entity.Store;
import jp.co.group_c.homeDao.HomeDao;
import jp.co.group_c.homeService.HomeService;

@Service
@Transactional
public class HomeServiceImpl implements HomeService{

	@Autowired
	private HomeDao homeDao;

	//新着情報の取得用メソッド
	@Override
	public List<Store> newArrival() {
		return homeDao.newArrival();
	}

	//カテゴリ表示用メソッド
	@Override
	public List<Store> mainCategory(String storeName) {
		return homeDao.mainCategory(storeName);
	}

}
