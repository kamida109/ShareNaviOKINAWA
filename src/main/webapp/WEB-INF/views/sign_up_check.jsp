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

				<h2>新規登録確認</h2>

				<p>こちらの内容でよろしいでしょうか？</p>

				<p class="error">${msg}</p>

				<form action="sign_up" >
				<div><div class="sign_form_hidden">
					<p><label>NAME : <input type="text" name="name"/></label></p>
					<p><label>  ID : <input type="text" name="userId"/></label></p>
					<p><label>PASS : <input type="password" name="pass"/></label></p>
					<p><label>PASS(再) : <input type="password" name="passRe"/></label></p>
				</div></div>
				<div class="sign_form">
					この中であなたが好きなものを3つまで選んでください<br>
					<select name="category">
						<option></option>
						<option>飲食店</option>
						<option>アウトドア</option>
						<option>インドア</option>
					</select>
					<select name="category">
						<option></option>
						<option>飲食店</option>
						<option>アウトドア</option>
						<option>インドア</option>
					</select>
					<select name="category">
						<option></option>
						<option>飲食店</option>
						<option>アウトドア</option>
						<option>インドア</option>
					</select>
				</div>
					<p><button class="btn" type="submit" name="update">登録</button></p>
				</form>

				<p><button class="btn" type="button" onclick="location.href='sign_up'">戻る</button></p>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>
