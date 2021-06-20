package jp.co.group_c.dao;

import java.util.List;

import jp.co.group_c.entity.Store;

public interface SearchDao {

	// 店舗検索
	public List<Store> storeSearch(String storeName, String category, String city, boolean hyouka);

	// あいまい検索
	public List<Store> partStoreSearch(String storeName, boolean hyouka);

}
