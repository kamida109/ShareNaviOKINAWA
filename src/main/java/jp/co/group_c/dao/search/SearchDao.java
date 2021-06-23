package jp.co.group_c.dao.search;

import java.util.List;

import jp.co.group_c.entity.Category;
import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.Store;

public interface SearchDao {

	// 市町村テーブルの取得
	public List<Cities> cities();

	// カテゴリテーブルのメインカテゴリを取得
	public List<Category> mainCategory();

	// カテゴリテーブルのサブカテゴリを取得
	public List<Category> subCategory(Integer mianId);

	// 店舗検索
	public List<Store> storeSearch(String storeName, Integer subCategory, Integer cityId, boolean hyouka);

	// 店舗のカテゴリを取得
	public List<Store> storeCategory();

	// あいまい検索
	public List<Store> partStoreSearch(String storeName, boolean hyouka);

}
