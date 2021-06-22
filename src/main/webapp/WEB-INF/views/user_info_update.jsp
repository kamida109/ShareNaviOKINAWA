<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

				<!-- ---------- ここから本体 ---------- -->

				<form action="user_info_check">

					<div class="input_form">
					<div class="inner_frame_hidden">
					<div class="inner_frame_left">
						ID：<br>
						名前：<br>
						お住まいの地域：<br>
					</div>
					<div class="inner_frame_right">
						${fn:escapeXml(signInUser)}<br>
						${fn:escapeXml(signInUser)}<br>
						${fn:escapeXml(signInUser)}<br>
					</div>
					</div>
					<div style="clear:both;">
					好きなカテゴリ<br>
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
				</div>

					<p><button class="btn" type="submit" name="check">確認</button></p>
				</form>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>