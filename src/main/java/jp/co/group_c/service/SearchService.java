package jp.co.group_c.service;

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
	public List<Store> storeSearch(String storeName, Integer categoryId, Integer cityid, boolean hyouka);

	// あいまい検索
	public List<Store> partStoreSearch(String storeName, boolean hyouka);

}
