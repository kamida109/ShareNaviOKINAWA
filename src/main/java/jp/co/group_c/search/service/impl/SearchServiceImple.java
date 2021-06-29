package jp.co.group_c.search.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.group_c.entity.Category;
import jp.co.group_c.entity.Cities;
import jp.co.group_c.entity.Store;
import jp.co.group_c.search.dao.SearchDao;
import jp.co.group_c.search.service.SearchService;
import jp.co.group_c.update.entity.Favorite;
import jp.co.group_c.update.entity.Review;

@Service
@Transactional
public class SearchServiceImple implements SearchService{

	@Autowired
	private SearchDao searchDao;

	@Override
	public List<Cities> cities() {
		return searchDao.cities();
	}

	@Override
	public List<Category> mainCategory() {
		return searchDao.mainCategory();
	}

	@Override
	public List<Category> subCategory(Integer mainId) {
		return searchDao.subCategory(mainId);
	}

	// 店舗のカテゴリを取得
	@Override
	public List<Store> storeCategory() {
		return searchDao.storeCategory();
	}

	// 店舗検索
	@Override
	public List<Store> storeSearch(String storeName, Integer subCategiry, Integer cityId, boolean hyouka) {
		return searchDao.storeSearch(storeName, subCategiry, cityId, hyouka);
	}

	// あいまい検索
	@Override
	public List<Store> partStoreSearch(String storeName, boolean hyouka) {
		return searchDao.partStoreSearch(storeName, hyouka);
	}

	// 店舗詳細用の検索メソッド
	@Override
	public List<Store> storeDitails(Integer id) {
		return searchDao.storeDitails(id);
	}

	// レビュー上書き
	@Override
	public void reviewUpdate(Integer id, String review) {
		searchDao.reviewUpdate(id, review);
	}

	// レビュー削除
	@Override
	public void reviewDelete(Integer id) {
		searchDao.reviewDelete(id);
	}

	// 店舗削除
	@Override
	public void storeDelete(Integer id) {
		searchDao.storeDelete(id);
	}

	// お気に入り店舗の取得
	@Override
	public List<Favorite> favoriteStore() {
		// TODO 自動生成されたメソッド・スタブ
		return searchDao.favoriteStore();
	}

	// レビューの追加
	@Override
	public void insertReview(Integer storeId, Integer userId, String review) {
		searchDao.insertReview(storeId, userId, review);
	}

	// レビューの取得
	@Override
	public List<Review> reviewList(Integer storeId) {
		return searchDao.reviewList(storeId);
	}

}
