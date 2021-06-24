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

				<div class="input_form">
					<div class="input_form_inner">
					<c:forEach var="main" items="${storeDitails}">
						<input type="hidden" id="storeId" value="${main.storeId}">
						<p>
							店舗名：${main.storeName}
							評価<span class="star" id="star-${store.storeId}" data-star="${main.hyouka}"></span>
						</p>
						<p>住所：${main.citiesName} ${main.address}</p>
						<p>営業時間：${main.businessHours}</p>

						<p>写真 <a id="phote" href="">写真を追加する</a></p>
						<p><img class="storeimg" src="CSS/image/no_image1.png"><br>※スライドショーの予定</p>

						<div class="modal_window">
							<p>レビュー <a id="review" href="">レビューを追加する</a></p>
							<textarea rows="5" cols="50">${main.review}</textarea>
						</div>
					</c:forEach>

					</div>
				</div>

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>

			<script type="text/javascript" src="js/jquery.raty.js"></script>
			<script type="text/javascript" src="js/star.js"></script>
			<script type="text/javascript" src="js/modal.js"></script>

		</div>

		<div class="modal modal_output">
			<div class="modal_bg modal_close"></div>
			<div class="model_contents">
				<p>レビューの追加・編集</p>
				<textarea id="newReview" rows="10" cols="90" placeholder="追加・変更内容を入力してください"></textarea>
				<p><button type="button" id="comit">適応</button>
				<button type="button" class="modal_close" id="return">戻る</button></p>
			</div>
		</div>

</body>
</html>