package jp.co.group_c.dao;

import java.util.List;

import jp.co.group_c.entity.Store;

public interface StoreDao {

	//お気に入り表示メソッド
	public List<Store> favoriteStore(int userId);

	//レビュー履歴表示メソッド
	public List<Store> reviewStore(int userId);

}
