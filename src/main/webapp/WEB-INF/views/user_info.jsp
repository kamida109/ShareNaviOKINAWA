<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

				<h2>登録情報</h2>
				<p class="error">${msg}</p>

				<div class="out_frame">
					<div class="inner_frame_hidden">
						<div class="inner_frame_left">
							ID：<br>
							名前：<br>
							お住まいの地域：<br>
						</div>
						<div class="inner_frame_right">
							${fn:escapeXml(signInUser.loginId)}<br>
							${fn:escapeXml(signInUser.userName)}<br>
							${fn:escapeXml(signInUser.citiesName)}<br>
						</div>
					</div>
					<div style="margin-top: 130px; clear: both;">
						<span style="font-weight: bold;">好きなカテゴリ<br></span>
						<span>${fn:escapeXml(favoriteCategory[0].categoryName)}</span>
						<span style="padding-left:10px;">${fn:escapeXml(favoriteCategory[1].categoryName)}</span>
						<span style="padding-left:10px;">${fn:escapeXml(favoriteCategory[2].categoryName)}</span>
					</div>
				</div>

				<p><button class="btn" type="button" onclick="location.href='user_info_update'">登録情報変更</button></p>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>