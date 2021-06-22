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

				<!-- ---------- ここから本体 ---------- -->


		<h3>ユーザー管理</h3>
<form:form action="user_management" method="post" modelAttribute="userManagement">
			<label>ID:
			<form:input path="userId" /></label>

			<label>名前:
			<form:input path="userName" /></label>

		<form:button type= "submit" name= "select">検索</form:button>

<table border="1">
  <thead>
	<tr>
		<th>ユーザーID</th>
		<th>名前</th>
		<th>登録日</th>
		<th>ログインID</th>

	</tr>
  </thead>
  <tbody>
  	<c:forEach var="contact" items= "${selectResult}">
  		<tr>
			<td>${fn:escapeXml(contact.user_id)}</td>
			<td>${fn:escapeXml(contact.user_name)}</td>
			<td>${fn:escapeXml(contact.insert_day)}</td>
			<td>${fn:escapeXml(contact.login_id)}</td>
		</tr>

  	</c:forEach>
  </tbody>
</table>

		<h3>ユーザーの削除</h3>
		<label>削除するユーザーID:
		<form:input path="userId" /></label>

		<form:button type= "submit" name= "delete">確定</form:button>

</form:form>


				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>