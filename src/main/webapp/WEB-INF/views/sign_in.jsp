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

				<form:form action="sign_in" method="post" modelAttribute="signIn">
					<div class="sign_form_hidden">
					<p><label>ID : <form:input type="text" path="loginId"/></label></p>
					<p><label>PASS : <form:input type="password" path="password"/></label></p>
					</div>
					<p><form:button class="btn" type="submit" name="sign_in">LOGIN<small> >></small></form:button></p>
				</form:form>

				<p><button class="btn" type="button" onclick="location.href='sign_up'">新規登録</button></p>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>
