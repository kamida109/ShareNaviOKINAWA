package jp.co.group_c.update.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.group_c.entity.Store;
import jp.co.group_c.update.dao.UpdateDao;
import jp.co.group_c.update.entity.Review;
import jp.co.group_c.update.entity.StoreCategory;

@Service
@Transactional
public class UpdateServiceImpl implements UpdateService{

	@Autowired
	private UpdateDao updateDao;

	// 店舗の基本情報の更新
	@Override
	public void storeUpdate(Store store) {
		updateDao.storeUpdate(store);
	}

	// 店舗の評価更新
	@Override
	public void storeRankUpdate(Review review) {
		updateDao.storeRankUpdate(review);
	}

	// 店舗カテゴリの更新
	@Override
	public void storeCategoryUpdate(StoreCategory sc) {
		updateDao.storeCategoryUpdate(sc);
	}

}
