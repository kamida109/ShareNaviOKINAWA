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
			<div style="margin-top:20px;">

			<button name="storeUpdate">編集</button>

			<c:if test="${signInUser.authorityId eq 1 }">
				<button id="storeDelete">削除</button>
			</c:if>

			<button id="backSearch">戻る</button>

			<c:forEach var="main" items="${storeDitails}">
			<input type="hidden" id="storeId" value="${main.storeId}">
			<input type="hidden" id="storeName" value="${main.storeName}">
			<input type="hidden" id="userId" value="${signInUser.userId}">
			<input type="hidden" id="userId" value="${main.businessHours}">
			<input type="hidden" id="userId" value="${signInUser.userId}">

			<c:forEach var="review" items="${reviewList}">
				<input type="hidden" id="reviewId" value="${review.reviewId}">
			</c:forEach>

			<div class="inner_frame_hidden">
				<div class="inner_frame_left">
					<p>店舗名：</p>
					<p>評価：</p>
					<p>カテゴリ:</p>
					<p>住所：</p>
					<p>電話番号：</p>
					<p>営業時間：</p>
					<p>写真：</p>
				</div>
				<div class="inner_frame_right">
					<p>
					${main.storeName}

					<span id="favorite">
					<c:if test="${empty flag}">
					&emsp;<img src="/CSS/image/heart_off.png">
					<input type="hidden" id="flagStatus" value=0>
					</c:if>

					<c:if test="${not empty flag}">
					&emsp;<img src="/CSS/image/heart_on.png">
					<input type="hidden" id="flagStatus" value=1>
					</c:if>
					</span>

					<p><span class="star" id="star-${store.storeId}" data-star="${main.hyouka}"></span></p>

					<p><c:forEach var="category" items="${mainCategoryList}">
						<c:if test="${main.storeId eq category.storeId }">
						<span class="button">${category.categoryName}</span>
						</c:if>
					</c:forEach></p>

					<p>${main.citiesName} ${main.address}&emsp;</p>
					<p>${main.tel}&emsp;</p>
					<p>${main.businessHours}&emsp;</p>
					<div class="image" style="margin-top:0; margin-bottom:10px; height:300px; width:300px;">
						<c:forEach items="${imageList}" var="img">
						<c:if test="${main.storeId eq img.storeId }">
							<img class="storeimg" src="${img.paths}">
						</c:if>
						</c:forEach>
					</div>
					<a id="phote" href="/addPhoto?storeId=${main.storeId}" style="margin-left:200px; font-size: 20px">写真を追加する</a>
				</div>
			</div>

			<div style="clear:both;"></div>


			<p>レビュー <br><a id="review" href="" style="margin-left:200px;font-size: 20px">レビューを追加する</a></p>

			<div class="modal_window">
			<c:forEach var="review" items="${reviewList}">
			<p>
<%-- 				<c:if test="${not empty review.review }"> --%>
					投稿者：${review.userName}<br>
					<c:if test="${signInUser.authorityId eq 1 }">
						<input type="checkBox" class="delReview" value="${review.reviewId}">
					</c:if>
					<textarea id="review" rows="5" cols="50" readonly>${review.review}</textarea>
<%-- 				</c:if> --%>
			</p>
			<c:if test="${signInUser.userId eq review.userId}">
				<input type="hidden" id="reviewId" value="${review.reviewId}">
				<input type="hidden" id="reviewId" value="${review.userId}">
			</c:if>
			</c:forEach>

			<c:if test="${signInUser.authorityId eq 1 }">
				<button type="button" id="reviewDel">レビューの削除</button>
			</c:if>

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
	<script type="text/javascript" src="js/slick.min.js"></script>
	<script type="text/javascript" src="js/slick.js"></script>
	<script type="text/javascript" src="js/img.js"></script>

	<div class="modal modal_output">
		<div class="modal_bg modal_close"></div>
		<div class="model_contents">
			<p>レビューの編集</p>
			<p id="errMsg"></p>
			<textarea id="newReview" rows="10" cols="90" placeholder="編集内容を入力してください"></textarea>
			<p>
				<button type="button" id="reviewInsert">追加</button>

				<button type="button" class="modal_close">戻る</button></p>
		</div>
	</div>
</body>
</html>