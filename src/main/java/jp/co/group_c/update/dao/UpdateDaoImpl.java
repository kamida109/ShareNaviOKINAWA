package jp.co.group_c.update.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.entity.Store;
import jp.co.group_c.update.entity.Review;
import jp.co.group_c.update.entity.StoreCategory;

@Repository
public class UpdateDaoImpl implements UpdateDao{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	// 入力値の埋め込み
    MapSqlParameterSource param = new MapSqlParameterSource();

	// 店舗の基本情報の更新
	private static final String STORE_INFO = "UPDATE store\n"
												+ "SET store_name = :storeName, business_hours = :businessHours, cities_id = :cityId,"
												+ " address = :address, tel = :tel, insert_day = CURRENT_DATE\n"
												+ "WHERE store_id = :storeId";

	// 店舗の評価の更新
	private static final String STORE_RANK = "UPDATE review\n"
												+ "SET hyouka = :hyouka, review_date = CURRENT_DATE\n"
												+ "WHERE review_id = :reviewId";

	private static final String STORE_CATEGORY_DELETE = "DELETE FROM store_category\n"
															+ "WHERE store_id = :storeId";

	private static final String STORE_CATEGORY_UPDATE = "INSERT INTO store_category\n"
																+ "VALUES (:storeId, :category1), \n"
																+ "(:storeId, :category2), \n"
																+ "(:storeId, :category3)";

	// 店舗の基本情報の更新
	@Override
	public void storeUpdate(Store store) {

		param.addValue("storeId", store.getStoreId());
		param.addValue("storeName", store.getStoreName());
		param.addValue("businessHours", store.getBusinessHours());
		param.addValue("cityId", store.getCitiesId());
		param.addValue("address", store.getAddress());
		param.addValue("tel", store.getTel());

		jdbcTemplate.update(STORE_INFO, param);
	}

	// 店舗の評価更新
	@Override
	public void storeRankUpdate(Review review) {
		param.addValue("reviewId", review.getReviewId());
		param.addValue("hyouka", review.getHyouka());

		jdbcTemplate.update(STORE_RANK, param);
	}

	// 店舗カテゴリの更新
	@Override
	public void storeCategoryUpdate(StoreCategory sc) {
		param.addValue("storeId", sc.getStoreId());

		jdbcTemplate.update(STORE_CATEGORY_DELETE, param);

		param.addValue("category1", sc.getCategoryId1());
		param.addValue("category2", sc.getCategoryId2());
		param.addValue("category3", sc.getCategoryId3());

		jdbcTemplate.update(STORE_CATEGORY_UPDATE, param);
	}

}
