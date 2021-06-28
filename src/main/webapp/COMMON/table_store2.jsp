<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="scroll">
	<table class="store_table">
		<tbody>
			<c:forEach items="${recommendList}" var="recommend" begin="0" end="2" step="1" varStatus="i">
				<tr class="top">
					<td class="image2" rowspan="3" valign="middle">
					<c:set var="counter" value="0" />

						<!-- 写真の出力処理 -->
							<img class="storeimg" id="img2-${recommend.storeId}" data-count="1"
								<c:forEach items="${imageList}" var="img">
									<c:if test="${recommend.storeId eq img.storeId }">
										src="${img.paths}"
									</c:if>
								</c:forEach>
								<c:forEach begin="1" end="${fn:length(imageList)}" step="1" varStatus="j">
								    <c:if test="${recommend.storeId eq imageList[j.index].storeId}">
									    <c:set var="counter" value="${counter + 1}"/>
								  		data-img${counter}="${imageList[j.index].paths}"
							 		</c:if>
								</c:forEach>
						>
					</td>

					<!-- 市町村名の出力処理 -->
					<td class="cities" colspan="3" align="left" valign="bottom">${recommend.citiesName}</td>
				</tr>

				<tr>
					<!-- 店舗名の出力処理 -->
					<td class="name" colspan="2" align="left"><a href="/details?storeId=${recommend.storeId}">${fn:escapeXml(recommend.storeName)}</a></td>

					<!-- 評価の出力処理 -->
					<td class="star2" id="star2-${recommend.storeId}"
						data-star2="${recommend.hyouka}"></td>
				</tr>

				<!-- カテゴリの出力処理 -->
				<tr class="bottom">
					<c:forEach var="main" items="${mainCategoryList}">
						<c:if test="${recommend.storeId eq main.storeId }">
							<td valign="top">${main.categoryName}</td>
						</c:if>
					</c:forEach>
				</tr>

			</c:forEach>
			<c:if test="${not empty notRecommendList}">
				<p>検索した内容が見つかりません</p>
				<p>
					店舗を登録したい場合は<br> <a href="input">こちらをクリック</a>
				</p>
			</c:if>
		</tbody>
	</table>
</div>
