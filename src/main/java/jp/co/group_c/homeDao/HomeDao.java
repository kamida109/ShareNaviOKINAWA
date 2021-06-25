package jp.co.group_c.homeDao;

import java.util.List;

import jp.co.group_c.entity.Store;
import jp.co.group_c.entity.Users;

public interface HomeDao {

	//新着情報の取得メソッド
	List<Store> newArrival();

	//カテゴリ表示メソッド
	List<Store> mainCategory();

	//ユーザー情報の取得
	List<Users> users();

	//おすすめの取得メソッド
	List<Store> recommend(int userId);

	//新しい提案メソッド
	List<Store> plan(int userId);

	//画像表示メソッド
	List<Store> image();
}
