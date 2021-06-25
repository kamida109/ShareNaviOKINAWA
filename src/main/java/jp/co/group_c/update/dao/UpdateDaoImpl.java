package jp.co.group_c.update.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.group_c.entity.Store;

@Repository
public class UpdateDaoImpl implements UpdateDao{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	// 入力値の埋め込み
    MapSqlParameterSource param = new MapSqlParameterSource();

	// 店舗の基本情報の更新
	private static final String STORE_INFO = "UPDATE store\n"
												+ "SET store_name = :storeName, business_hours = businessHours, cities_id = :cityId, address = :address,"
												+ "tel = :tel, insert_day = :insertDay\n"
												+ "WHERE store_id = storeId";

	// 店舗の評価の更新
	private static final String STORE_RANK = "UPDATE review\n"
												+ "SET hyouka = :hyouka\n"
												+ "WHERE review_id = :review_id";

	// 店舗の基本情報の更新
	@Override
	public void storeUpdate(Store store) {

		param.addValue("storeName", store.getStoreName());
		param.addValue("businessHours", store.getBusinessHours());
		param.addValue("cityId", store.getCategoryId());
		param.addValue("address", store.getAddress());
		param.addValue("tel", store.getTel());
		param.addValue("insertDay", store.getInsertDay());

		jdbcTemplate.update(STORE_INFO, param);
	}

	// 店舗の評価更新
	@Override
	public void storeRankUpdate(Integer reviewId, Integer hyouka) {
		param.addValue("reviewId", reviewId);
		param.addValue("hyouka", hyouka);

		jdbcTemplate.update(STORE_RANK, param);
	}

}
