<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="jp">
	<!-- 共通部品_head -->
	<jsp:include page="/COMMON/head.jsp"/>
	<body>
		<div class="white_noise2">
			<!-- 共通部品_header -->
			<jsp:include page="/COMMON/header.jsp"/>
			<div class="frame">

				<!-- ---------- ここから本体 ---------- -->

				<details open>
				<summary>お気に入り</summary>
					<jsp:include page="/COMMON/table_store.jsp"/>
				</details>

				<details open>
				<summary>レビュー履歴</summary>
					<jsp:include page="/COMMON/table_store2.jsp"/>
				</details>

				<jsp:include page="/COMMON/table_recommend.jsp"/>


				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>

		<script type="text/javascript" src="js/jquery.raty.js"></script>
		<script type="text/javascript" src="js/star.js"></script>
		<script type="text/javascript" src="js/img.js"></script>

	</body>
</html>