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
<!-- セッションにあるログインユーザーの権限情報から管理者か一般ユーザーか判断する -->

 <%-- <c:if test= "${fn:escapeXml(signInUser.authorityId)==1}"> --%>

<%-- <form:form action="contact" method="post" modelAttribute="contact"> --%>
	<table border="1">
		<caption>問い合わせ</caption>
		<thead>
		<tr>
			<th>ID</th>
			<th>ユーザー名</th>
			<th>種類</th>
			<th>状況</th>
		</tr>
		</thead>

	<c:forEach var="result" items= "${selectResult}">
		<tr>
			<td>${fn:escapeXml(result.contactId)}</td>
			<td>${fn:escapeXml(result.userName)}</td>
			<td>${fn:escapeXml(result.contactCategoryId)}</td>
			<td>${fn:escapeXml(result.flag)}</td>
		</tr>
	</c:forEach>
</table>





	<!--ユーザー名をクリックしたときに内容が表示される -->

<%-- 	  <h3>問い合わせ内容</h3>
	<form:form action="contact_result" method="post" modelAttribute="contact">
	<form:input type="hidden" path="contactId" readonly="true"/>
	<label>ユーザー名<br>
	<form:select path="userName" readonly="true"/>
	</label>

	<label>目的<br>
	<form:select path="contactCategoryId" readonly="true"/>
	</label>

	<label>本文<br>
	<form:textarea path="contents" readonly="true"/>
	</label>
	<form:button type="submit" name="update">解決</form:button>


	</form:form>
 --%>
	<!-- hrefはとぶjspを指定する。コントローラーではリクエストマッピングの値 -->
	<a href="/user_management">ユーザー管理</a>

 <%-- </c:if> --%>



<%-- <c:if test= "${fn:escapeXml(signInUser.authorityId)==2}"> --%>

  <h3>問い合わせ</h3>
<form:form action="contact_result" method="post" modelAttribute="contactInfo">

 <label>目的<br>
 <form:select path="contactCategoryId">
	<form:option value="1">通報</form:option>
	<form:option value="2">問い合わせ</form:option>
	<form:option value="3">要望</form:option>
 </form:select>
 </label>

 <br><label>本文<br>
 <form:textarea path="contents"/> </label>
 <br><form:errors path="contents" cssStyle="color: red"/><br>

 <form:button type= "submit" name= "insert">送信</form:button>
 </form:form>
 <%--  </c:if> --%>


				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>