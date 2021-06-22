package jp.co.group_c.service.search;

import java.util.List;

import jp.co.group_c.entity.Category;
import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.Store;

public interface SearchService {

	// 市町村テーブルの取得
	public List<Cities> cities();

	// カテゴリテーブルのメインカテゴリを取得
	public List<Category> mainCategory();

	// カテゴリテーブルのサブカテゴリを取得
	public List<Category> subCategory(Integer mainId);

	// 店舗検索
	public List<Store> storeSearch(String storeName, String subCategory, Integer cityid, boolean hyouka);

	// 店舗のカテゴリを取得
	public List<Store> storeCategory();

	// あいまい検索
	public List<Store> partStoreSearch(String storeName, boolean hyouka);

}
