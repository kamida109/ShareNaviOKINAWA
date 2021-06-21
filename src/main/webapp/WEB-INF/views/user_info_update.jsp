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

				<h2>登録情報</h2>

				<form:form action="user_info_check" method="post" modelAttribute="userInfo">
					<div class="out_frame">
						<div class="inner_frame_hidden">
							<div class="inner_frame_left">
								ID：<br>
								名前：<br>
								お住まいの地域：<br>
							</div>
							<div class="inner_frame_right">
								<form:input type="text" path="loginId"/><br>
								<form:input type="text" path="userName"/><br>
								<form:input type="text" path=""/><br>
							</div>
						</div>
						<div style="margin-top: 130px; clear: both;">
							<span style="font-weight: bold;">好きなカテゴリ<br></span>
							<form:select path="categoryId1">
								<form:options items="${category}" itemLabel="categoryName" itemValue="categoryId" />
							</form:select>
							<form:select path="categoryId2">
								<form:options items="${category}" itemLabel="categoryName" itemValue="categoryId" />
							</form:select>
							<form:select path="categoryId3">
								<form:options items="${category}" itemLabel="categoryName" itemValue="categoryId" />
							</form:select>
						</div>
					</div>
					<p><form:button class="btn" type="submit" name="check">確認</form:button></p>
				</form:form>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>