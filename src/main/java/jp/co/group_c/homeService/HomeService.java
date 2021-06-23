package jp.co.group_c.homeService;

import java.util.List;

import jp.co.group_c.entity.Store;
import jp.co.group_c.entity.Users;

public interface HomeService {

	//新着情報の取得用メソッド
	List<Store> newArrival();

	//カテゴリ表示用メソッド
	List<Store> mainCategory(String storeName);

	//ユーザー情報の取得
	List<Users> users();

	//おすすめの取得用メソッド
	List<Store> recommend(int userId);

	//新しい提案用メソッド
	List<Store> plan(int userId);
}
