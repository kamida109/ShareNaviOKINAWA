<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html lang="jp">
<!-- 共通部品_head -->
<jsp:include page="/COMMON/head.jsp"/>
<body>
	<div class="white_noise2">
	<!-- 共通部品_header -->
	<jsp:include page="/COMMON/header.jsp"/>

	<div class="frame">
	<form:form action="update_store" modelAttribute="update_store">
		<div class="input_form">
			<div class="input_form_inner">

			<button name="storeUpdate">編集</button>

			<c:if test="${signInUser.authorityId eq 1 }">
				<button id="storeDelete">削除</button>
			</c:if>

			<button  id="returnSearch">戻る</button>

			<c:forEach var="main" items="${storeDitails}">
			<input type="hidden" id="storeId" value="${main.storeId}">
			<input type="hidden" id="storeName" value="${main.storeName}">
			<input type="hidden" id="reviewId" value="${main.reviewId}">

			<p>
			店舗名：${main.storeName}

			<span id="favorite">
			<c:if test="${empty flag}">
			<img src="/CSS/image/heart_off.png">
			<input type="hidden" id="flagStatus" value=0>
			</c:if>

			<c:if test="${not empty flag}">
			<img src="/CSS/image/heart_on.png">
			<input type="hidden" id="flagStatus" value=1>
			</c:if>
			</span>

			<p>
			評価：<span class="star" id="star-${store.storeId}" data-star="${main.hyouka}"></span>
			</p>

			<p>カテゴリ:
			<c:forEach var="category" items="${mainCategoryList}">
				<c:if test="${main.storeId eq category.storeId }">
				${category.categoryName}
				</c:if>
			</c:forEach></p>

			<p>住所：${main.citiesName} ${main.address}</p>
			<p>電話番号：${main.tel}</p>
			<p>営業時間：${main.businessHours}</p>

			<p>写真 <a id="phote" href="/addPhoto?storeId=${main.storeId}">写真を追加する</a></p>
			<p><img class="storeimg" src="CSS/image/no_image1.png"><br>※スライドショーの予定</p>

			<div class="modal_window">
				<p>レビュー <a id="review" href="">レビューを追加する</a></p>
				<textarea id="review" rows="5" cols="50" readonly>${main.review}</textarea>
			</div>

			</c:forEach>

		</div>
		</div>
	</form:form>
	</div>
	</div>

	<!-- 共通部品_footer -->
	<footer id="footer"></footer>

	<script type="text/javascript" src="js/jquery.raty.js"></script>
	<script type="text/javascript" src="js/star.js"></script>
	<script type="text/javascript" src="js/modal.js"></script>
	<script type="text/javascript" src="js/favorite.js"></script>

	<div class="modal modal_output">
		<div class="modal_bg modal_close"></div>
		<div class="model_contents">
			<p>レビューの編集</p>
			<p id="errMsg"></p>
			<textarea id="newReview" rows="10" cols="90" placeholder="編集内容を入力してください"></textarea>
			<p><button type="button" id="comit">確定</button>
			<c:if test="${signInUser.authorityId eq 1 }">
				<button type="button" id="reviewDel">削除</button>
			</c:if>
			<button type="button" class="modal_close">戻る</button></p>
		</div>
	</div>
</body>
</html>