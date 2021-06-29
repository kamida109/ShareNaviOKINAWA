<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div class="input_form">
			<h3>画像を追加しました</h3>
			<c:forEach var="main" items="${storeDitails}">
				<a href="/details?storeId=${main.storeId}">店舗詳細へ戻る</a>
			</c:forEach>
		</div>
		</div>
	</div>
	<!-- 共通部品_footer -->
	<footer id="footer"></footer>
	</body>
</html>