package jp.co.group_c.update.entity;

public class Utility {

	// 数字に変換できるか
	public static boolean isNumber(String val) {
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
	}

}
