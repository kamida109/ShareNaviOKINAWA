<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="scroll">
	<table class="store_table">
		<tbody>
			<c:forEach items="${planList}" var="plan">
				<tr class="top">
					<td class="image" rowspan="3" valign="middle">

					<!-- 写真の出力処理 -->
						<c:forEach items="${imageList}" var="img">
							<c:if test="${plan.storeId eq img.storeId }">
								<img class="storeimg" src="${img.paths}">
							</c:if>
						</c:forEach>
					</td>

					<!-- 市町村名の出力処理 -->
					<td class="cities" colspan="3" align="left" valign="bottom">${plan.citiesName}</td>
				</tr>

				<tr>
					<!-- 店舗名の出力処理 -->
					<td class="name" colspan="2" align="left"><a href="/details?storeId=${plan.storeId}">${fn:escapeXml(plan.storeName)}</a></td>

					<!-- 評価の出力処理 -->
					<td class="star3" id="star3-${plan.storeId}"
						data-star3="${plan.hyouka}"></td>
				</tr>

				<!-- カテゴリの出力処理 -->
				<tr class="bottom">
					<c:forEach var="main" items="${mainCategoryList}">
						<c:if test="${plan.storeId eq main.storeId }">
							<td valign="top">${main.categoryName}</td>
						</c:if>
					</c:forEach>
				</tr>

			</c:forEach>
			<c:if test="${not empty notPlanList}">
				<p>検索した内容が見つかりません</p>
				<p>
					店舗を登録したい場合は<br> <a href="add_store">こちらをクリック</a>
				</p>
			</c:if>
		</tbody>
	</table>
</div>
