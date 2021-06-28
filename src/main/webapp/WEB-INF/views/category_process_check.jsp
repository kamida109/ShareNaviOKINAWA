<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

				<h2>カテゴリの追加</h2>
				<h2>この内容でよろしいですか？</h2>

				<form:form action="category_process_result" modelAttribute="category_process" method="Post">
					<c:choose>
						<c:when test="${authorityId == 1}">
							<p>
								<form:input path="mainCategoryName" readonly="true"/>
								<form:input path="categoryName" readonly="true"/>
								<form:input path="process" readonly="true"/>
							</p>
							<p>
								<form:input path="pCategoryName" readonly="true"/>
							</p>
						</c:when>

						<c:otherwise>
							<form:input path="mainCategoryName" readonly="true"/>
							<form:input path="pCategoryName" readonly="true"/>
							<form:hidden path="process"/>
						</c:otherwise>
					</c:choose>
					<p><button class="btn" type="submit" name="check">確認</button></p>
					<form:hidden path="mainCategory"/>
					<form:hidden path="categoryId"/>
				</form:form>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>