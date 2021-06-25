<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="scroll">
	<table class="store_table">
		<tbody>
			<c:forEach items="${storeList}" var="store" begin="0" end="4"
				step="1" varStatus="i">
				<tr class="top">
					<td class="image" rowspan="3" valign="middle">
						<!-- 写真の出力処理 --> <c:if
							test="${store.storeId eq imageList[i.index].storeId}">
							<c:set var="counter" value="0" />
							<img class="storeimg" id="img-${store.storeId}"
								src="${imageList[i.index].paths}" data-count="1"
								<c:forEach begin="1" end="${fn:length(imageList)}" step="1" varStatus="j">
							<c:if test="${store.storeId eq imageList[j.index].storeId}">
								<c:set var="counter" value="${counter + 1}"/>
								  data-img${counter}="${imageList[j.index].paths}"
							 </c:if>
						</c:forEach>>
						</c:if>
					</td>

					<!-- 市町村名の出力処理 -->
					<td class="cities" colspan="3" align="left" valign="bottom">${store.citiesName}</td>
				</tr>
				<tr>
					<!-- 店舗名の出力処理 -->
					<td class="name" colspan="2" align="left"><a href="details">${fn:escapeXml(store.storeName)}</a></td>

					<!-- 評価の出力処理 -->
					<td class="star" id="star-${store.storeId}"
						data-star="${store.hyouka}"></td>
				</tr>

				<!-- カテゴリの出力処理 -->
				<tr class="bottom">
					<c:forEach var="main" items="${mainCategoryList}">
						<c:if test="${store.storeId eq main.storeId }">
							<td valign="top">${main.categoryName}</td>
						</c:if>
					</c:forEach>
				</tr>

			</c:forEach>
			<c:if test="${not empty notList}">
				<p>検索した内容が見つかりません</p>
				<p>
					店舗を登録したい場合は<br> <a href="input">こちらをクリック</a>
				</p>
			</c:if>
		</tbody>
	</table>
</div>
