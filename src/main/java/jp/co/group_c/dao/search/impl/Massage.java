package jp.co.group_c.dao.search.impl;

public class Massage {

	public static String errMsg() {
		String index = "<div class=\"scroll\">\r\n"
						+ "<table class=\"store_table\">\r\n"
						+ "	<tbody>\r\n"
						+ "		<h3 class=\"error\">検索した内容は見つかりません。</h3>\r\n"
						+ "		<p>新しい店舗を登録する場合は<br>\r\n"
						+ "			<a href=\"input\">ここをクリック</a>\r\n"
						+ "		</p>\r\n"
						+ "	</tbody>\r\n"
						+ "</table>\r\n"
						+ "</div>";

		return index;

	}

	public static String searchMsg() {
		String index ="<div class=\"scroll\">\r\n"
						+ "<table class=\"store_table\">\r\n"
						+ "	<tbody>\r\n"
						+ "		<c:forEach items=\"${storeList}\" var=\"store\" >\r\n"
						+ "			<tr class=\"top\">\r\n"
						+ "				<td class=\"image\" rowspan=\"3\"  valign=\"middle\"><img class=\"storeimg\" src=\"CSS/image/no_image1.png\"></td>\r\n"
						+ "				<td class=\"cities\" colspan=\"3\" align=\"left\" valign=\"bottom\">${store.citiesName}</td>\r\n"
						+ "			</tr><tr>\r\n"
						+ "				<td class=\"name\" colspan=\"2\" align=\"left\"><a href=\"details\">${fn:escapeXml(store.storeName)}</a></td>\r\n"
						+ "				<td class=\"star\">${store.hyouka}</td>\r\n"
						+ "			</tr><tr class=\"bottom\">\r\n"
						+ "			<c:forEach var=\"main\" items=\"${mainCategoryList}\">\r\n"
						+ "				<c:if test=\"${store.storeId eq main.storeId}\">\r\n"
						+ "				<td valign=\"top\">${main.categoryName}</td>\r\n"
						+ "				</c:if>\r\n"
						+ "			</c:forEach></tr>\r\n"
						+ "		</c:forEach>\r\n"
						+ "	</tbody>\r\n"
						+ "</table>\r\n"
						+ "</div>";

		return index;
	}

}
