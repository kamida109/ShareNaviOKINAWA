package jp.co.group_c.homeService;

import java.util.List;

import jp.co.group_c.entity.Store;

public interface HomeService {

	//新着情報の取得用メソッド
		List<Store> newArrival();

	//カテゴリ表示用メソッド
		List<Store> mainCategory(String storeName);
}
