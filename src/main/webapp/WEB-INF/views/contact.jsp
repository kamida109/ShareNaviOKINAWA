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
			<td><a href="/contact/${fn:escapeXml(result.contactId)}">${fn:escapeXml(result.contactId)}</a></td>
			<td>${fn:escapeXml(result.userName)}</td>

			<td>
				<c:choose>
				<c:when test="${fn:escapeXml(result.contactCategoryId ==1)}">
					<span>通報</span>
				</c:when>
				<c:when test="${fn:escapeXml(result.contactCategoryId ==2)}">
					<span>問い合わせ</span>
				</c:when>
				<c:when test="${fn:escapeXml(result.contactCategoryId ==3)}">
					<span>要望</span>
				</c:when>
				</c:choose>
			</td>

			<td>
			<c:choose>
				<c:when test="${fn:escapeXml(result.flag eq 'true')}">
					<span>解決</span>
				</c:when>
				<c:otherwise>
					<span>未解決</span>
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
	</c:forEach>
</table>





	<!--ユーザー名をクリックしたときに内容が表示される -->

 	  <h3>問い合わせ内容</h3>
 	  <c:if test="${not empty updateMsg}">
			<p class="error">${updateMsg}</p>
	  </c:if>

<form:form action="/contact" method="post" modelAttribute="contact_management">
	<form:input type="hidden" path="contactId" readonly="true"/>
	<label>ユーザー名<br>
	<form:input path="userName" readonly="true"/>
	</label>

	<br><label>種類<br>
	<form:hidden path="contactCategoryId" readonly="true"/>
	<input type="text" value="${contactCategoryId}" readonly/>
	</label>

	<br><label>本文<br>

	<%--  form:textarea path="contents" readonly="true"/>--%>

	<form:textarea path="contents"/>
	</label>


	<form:button  name="update">解決</form:button>

 </form:form>

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