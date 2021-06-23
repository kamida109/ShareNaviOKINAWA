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

				<h2>登録情報変更</h2>

				<form:form action="user_info" method="post" modelAttribute="userInfo">
					<div class="out_frame">
						<div class="inner_frame_hidden">
							<div class="inner_frame_left">
								ID：<br>
								名前：<br>
								お住まいの地域：<br>
							</div>
							<div class="inner_frame_right">
								<form:input type="text" path="loginId" readonly="true"/><br>
								<form:input type="text" path="userName" readonly="true"/><br>
								<form:select path="citiesId" itemValue="categoryId">
									<form:options items="${cities}" itemLabel="citiesName"/>
								</form:select><br>

							</div>
						</div>
						<div style="margin-top: 130px; clear: both;">
						<span style="font-weight: bold;">好きなカテゴリ<br></span>
						<div class="inner_frame_hidden">
							<div class="inner_frame_left">
								<form:hidden id="mainCategory1" path="mainCategoryId1" readonly="true"/><br>
								<form:input id="subCategory1" path="categoryId1" readonly="true"/>
							</div>
							<div class="inner_frame_left">
								<form:input id="mainCategory2" path="mainCategoryId2" readonly="true"/><br>
								<form:input id="subCategory2" path="categoryId2" readonly="true"/>
							</div>
							<div class="inner_frame_left">
								<form:input id="mainCategory3" path="mainCategoryId3" readonly="true"/><br>
								<form:input id="subCategory3" path="categoryId3" readonly="true"/>
							</div>
						</div>
						</div>
					</div>
					<p><form:button class="btn" type="submit" name="back">戻る</form:button></p>
					<p><form:button class="btn" type="submit" name="update">登録</form:button></p>
				</form:form>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>