<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="jp">
	<!-- 共通部品_head -->
	<jsp:include page="/COMMON/head.jsp"/>
	<body>
		<div class="white_noise1">
			<div class="sign_frame">

				<!-- ---------- ここから本体 ---------- -->

				<h1>ShareNaviOKINAWA</h1>

				<p class="error">${msg}</p>

				<form action="home">
					<div class="sign_form_hidden">
					<p><label>ID : <input type="text" name="userId"/></label></p>
					<p><label>PASS : <input type="password" name="pass"/></label></p>
					</div>
					<p><button class="btn" type="submit" name="sign_in">LOGIN<small> >></small></button></p>
				</form>

				<p><button class="btn" type="button" onclick="location.href='sign_up'">新規登録</button></p>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>
