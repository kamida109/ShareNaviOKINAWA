package jp.co.group_c.search.dao;

import java.util.List;

import jp.co.group_c.entity.Category;
import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.Store;
import jp.co.group_c.update.entity.Favorite;

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

	// 店舗詳細用の検索メソッド
	public List<Store> storeDitails(Integer id);

	// レビューの変更
	public void reviewUpdate(Integer id, String review);

	// レビュー削除
	public void reviewDelete(Integer id);

	// 店舗削除
	public void storeDelete(Integer id);

	// お気に入り店舗情報の取得
	public List<Favorite> favoriteStore();

}
