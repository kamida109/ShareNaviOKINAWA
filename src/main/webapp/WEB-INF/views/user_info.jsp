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

				<div class="out_frame">
					<div class="inner_frame_hidden">
					<div class="inner_frame_left">
						ID：<br>
						名前：<br>
						お住まいの地域：<br>
					</div>
					<div class="inner_frame_right">
						aaa<br>
						aaa<br>
						aaa<br>
					</div>
					</div>
					<div style="clear:both;">
						好きなカテゴリ<br>
						カテゴリ1
						カテゴリ2
						カテゴリ3
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