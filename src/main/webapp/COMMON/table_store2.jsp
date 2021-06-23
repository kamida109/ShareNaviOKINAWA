<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="scroll"><table class="store_table">
	<tbody>
		<c:forEach begin="0" end="2" step="1" varStatus="status" >
			<c:if test="${not empty recommendList[status.index].storeId}">
			<tr class="top">
				<td class="image" rowspan="3"  valign="middle"><img class="storeimg" src="CSS/image/no_image1.png">
				</td>
				<td class="cities" colspan="3" align="left" valign="bottom">${recommendList[status.index].citiesName}</td>
			</tr><tr>
				<td class="name" colspan="2" align="left"><a href="details">${fn:escapeXml(recommendList[status.index].storeName)}</a></td>
				<td class="star2" id="star2-${recommendList[status.index].storeId}" data-star2="${recommendList[status.index].hyouka}"></td>
			</tr>
			<tr class="bottom">
			<c:forEach var="main" items="${mainCategoryList}">
				<c:if test="${recommendList[status.index].storeId eq main.storeId }">
				<td valign="top">${main.categoryName}</td>
				</c:if>
			</c:forEach></tr>
		</c:if>
		</c:forEach>
		<c:if test="${not empty notList}">
			<p>検索した内容が見つかりません</p>
			<p>店舗を登録したい場合は<br>
				<a href="input">こちらをクリック</a>
			</p>
		</c:if>
	</tbody>
</table></div>