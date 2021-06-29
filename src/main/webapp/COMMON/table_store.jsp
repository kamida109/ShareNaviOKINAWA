<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="scroll">

	<c:forEach items="${storeList}" var="store" begin="0" end="4" step="1" varStatus="i">
		<div style="height:330px;">

			<div class="image" style="display:inline-block; margin:15px 60px; width:300px; height:300px;">

				<!-- 写真の出力処理 -->
				<c:forEach items="${imageList}" var="img">
					<c:if test="${store.storeId eq img.storeId }">
						<img style="width:300px; height:300px" class="storeimg" src="${img.paths}">
					</c:if>
				</c:forEach>

			</div>

			<div style="display:inline-block; width:650px; height:300px;">
				<table class="store_table">
					<tbody>

						<tr class="top">
							<!-- 市町村名の出力処理 -->
							<td class="cities" rowspan="1" colspan="3" align="left" valign="bottom">${store.citiesName}</td>
							<!-- 評価の出力処理 -->
							<td class="star" rowspan="1" colspan="3" id="star-${store.storeId}"
								data-star="${store.hyouka}"></td>
						</tr>

						<tr>
							<!-- 店舗名の出力処理 -->
							<td class="name" rowspan="1" colspan="6" align="left"><a href="/details?storeId=${store.storeId}">${fn:escapeXml(store.storeName)}</a></td>
						</tr>

						<!-- カテゴリの出力処理 -->
						<tr class="bottom">
							<c:forEach var="main" items="${mainCategoryList}">
								<c:if test="${store.storeId eq main.storeId }">
									<td rowspan="1" colspan="2" valign="top" ><span class="button">${main.categoryName}</span></td>
								</c:if>
							</c:forEach>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</c:forEach>

	<c:if test="${not empty notList}">
		<p>検索した内容が見つかりません</p>
		<p>
			店舗を登録したい場合は<br> <a href="add_store">こちらをクリック</a>
		</p>
	</c:if>

</div>
