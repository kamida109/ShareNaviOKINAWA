<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="jp">
	<!-- 共通部品_head -->
	<jsp:include page="/COMMON/head.jsp"/>
	<body>
		<div class="white_noise1">
			<div class="sign_frame">

				<!-- ---------- ここから本体 ---------- -->

				<h2>${msg}</h2>

				<p><button class="btn" type="button" onclick="location.href='sign_in'">ログイン画面へ戻る</button></p>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>
