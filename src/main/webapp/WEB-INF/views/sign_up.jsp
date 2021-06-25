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

				<h2>新規登録</h2>

				<p>IDとPASSは半角英数字で入力してください</p>

				<p class="error">${msg}</p>

				<form:form action="sign_up" method="post" modelAttribute="signUp" >
				<div><div class="sign_form_hidden">
					<p><label>NAME : <form:input type="text" path="userName"/></label></p>
					<p><label>  ID : <form:input type="text" path="loginId"/></label></p>
					<p><label>PASS : <form:input type="password" path="password"/></label></p>
				</div></div>
				<div class="sign_form">
					この中であなたが好きなものを3つまで選んでください<br>
					<div class="inner_frame_left">
						<form:select id="mainCategory1" path="mainCategoryId1">
							<form:options items="${mainCategory}" itemLabel="categoryName" itemValue="categoryId" />
						</form:select><br>
						<form:select id="subCategory1" path="categoryId1">
							<form:options items="${category}" itemLabel="categoryName" itemValue="categoryId" />
						</form:select>
					</div>
					<div class="inner_frame_left">
						<form:select id="mainCategory2" path="mainCategoryId2">
							<form:options items="${mainCategory}" itemLabel="categoryName" itemValue="categoryId" />
						</form:select><br>
						<form:select id="subCategory2" path="categoryId2">
							<form:options items="${category}" itemLabel="categoryName" itemValue="categoryId" />
						</form:select>
					</div>
					<div class="inner_frame_left">
						<form:select id="mainCategory3" path="mainCategoryId3">
							<form:options items="${mainCategory}" itemLabel="categoryName" itemValue="categoryId" />
						</form:select><br>
						<form:select id="subCategory3" path="categoryId3">
							<form:options items="${category}" itemLabel="categoryName" itemValue="categoryId" />
						</form:select>
					</div>
				</div>
					<p><form:button class="btn" type="submit" name="check">確認</form:button></p>
				</form:form>

				<p><button class="btn" type="button" onclick="location.href='sign_in'">戻る</button></p>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>
