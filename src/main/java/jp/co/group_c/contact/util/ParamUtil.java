package jp.co.group_c.contact.util;

public class ParamUtil {
	//引数に指定した文字列がnull、または空文字かを判定

		public static boolean isNullOrEmpty(String str) {
			if (str == null || str.isEmpty()) {
				return true;
			} else {
				return false;
			}
		}
}
