package jp.co.group_c.update.dao;

import jp.co.group_c.entity.Store;
import jp.co.group_c.update.entity.Favorite;
import jp.co.group_c.update.entity.StoreCategory;

public interface UpdateDao {

	// 店舗の基本情報の更新
	public void storeUpdate(Store store);

	// 店舗の評価更新
	public void storeRankUpdate(Integer sId, Integer hyouka);

	// 店舗のカテゴリ更新
	public void storeCategoryUpdate(StoreCategory sc);

	// 店舗のお気に入り
	public void storeFavorite(Favorite favorite, Integer flag);

}
