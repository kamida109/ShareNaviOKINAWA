package jp.co.group_c.update.service;

import jp.co.group_c.entity.Store;

public interface UpdateService {

	// 店舗の基本情報の更新
	public void storeUpdate(Store store);

	// 店舗の評価更新
	public void storeRankUpdate(Integer reviewId, Integer hyouka);

}
