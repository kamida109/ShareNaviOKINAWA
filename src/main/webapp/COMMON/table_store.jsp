<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="scroll"><table class="store_table">
	<tbody>
		<c:forEach items="${storeList}" var="store" >
			<tr class="top">
				<td class="image" rowspan="3"  valign="middle"><img class="storeimg" src="CSS/image/no_image1.png"></td>
				<td class="cities" colspan="3" align="left" valign="bottom">${store.citiesName}</td>
			</tr><tr>
				<td class="name" colspan="2" align="left"><a href="details">${fn:escapeXml(store.storeName)}</a></td>
				<td class="star">${store.hyouka}</td>
			</tr><tr class="bottom">
			<c:forEach var="main" items="${mainCategoryList}">
				<td valign="top">${main.categoryName}</td>
			</c:forEach></tr>
		</c:forEach>
	</tbody>
</table></div>