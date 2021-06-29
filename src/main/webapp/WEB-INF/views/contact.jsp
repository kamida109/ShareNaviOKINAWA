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
	<!-- 管理者 -->
	<c:if test= "${fn:escapeXml(signInUser.authorityId)==1}">

		<h2>問い合わせ管理</h2>

		<!-- hrefはとぶjspを指定する。コントローラーではリクエストマッピングの値 -->
		<a href="/user_management">ユーザー管理</a><br><br>

		<div class="scroll" style="margin-top:20px; max-height:350px;">
			<table class="other_table" id="checked_list">
			<caption style="height:90px;"><h3 style="margin:0;">問い合わせ</h3>
			<form:form action="/contact" method="post" modelAttribute="contact_management">
				<label class="button"><input type="radio" name="sample" id="solved" style="display:none;">未解決のみ表示</label>
				<label class="button"><input type="radio" name="sample" id="unsolved" style="display:none;">解決のみ表示</label>
				<label class="button"><input type="radio" name="sample" id="all" style="display:none;">全件表示</label>
		 	</form:form></caption>
			<thead>
				<tr>
					<th>ID</th>
					<th>ユーザー名</th>
					<th>目的</th>
					<th>状況</th>
				</tr>
			</thead>

			<c:forEach var="result" items= "${selectResult}">
				<tr data-href="/contact/${fn:escapeXml(result.contactId)}">
					<td>${fn:escapeXml(result.contactId)}</td>
					<td>${fn:escapeXml(result.userName)}</td>

					<c:choose>
						<c:when test="${fn:escapeXml(result.contactCategoryId ==1)}">
							<td style="background-color:#ffaaaa77;"><span>通報</span></td>
						</c:when>
						<c:when test="${fn:escapeXml(result.contactCategoryId ==2)}">
							<td style="background-color:#efe8af77;"><span>問い合わせ</span></td>
						</c:when>
						<c:when test="${fn:escapeXml(result.contactCategoryId ==3)}">
							<td style="background-color:#aaffea77;"><span>要望</span></td>
						</c:when>
					</c:choose>

					<c:choose>
						<c:when test="${fn:escapeXml(result.flag eq 'true')}">
							<td><span>解決</span></td>
						</c:when>
						<c:otherwise>
							<td style="background-color:#ffaaaa77;"><span>未解決</span></td>
						</c:otherwise>
					</c:choose>

				</tr>
			</c:forEach>

		</table>
	</div><br>


	<!--ユーザー名をクリックしたときに内容が表示される -->

	<div class="input_form">
	<h3>問い合わせ内容</h3>
	<c:if test="${not empty updateMsg}">
			<p class="error">${updateMsg}</p>
	  </c:if>

	<form:form action="/contact" method="post" modelAttribute="contact_management">
		<form:input type="hidden" path="contactId" readonly="true"/>
		ユーザー名<br>
		<form:input path="userName" style="text-align:center;" readonly="true"/><br><br>

		目的<br>
		<form:hidden path="contactCategoryId" readonly="true"/>
		<input type="text" style="text-align:center;" value="${contactCategoryId}" readonly/><br><br>

		本文<br>
		<form:textarea path="contents" readonly="true"/>
		<br>

		<!-- flagの値でボタンを表示させる  -->
			<c:if test="${fn:escapeXml(flag == false)}">
				<form:button value="${solvedFlag}" name="update">解決</form:button>
			</c:if><br>

		</form:form>
		</div>

	</c:if>

	<!-- セッションにあるログインユーザーの権限情報から管理者か一般ユーザーか判断する -->
	<!-- 一般ユーザ -->
	<c:if test= "${fn:escapeXml(signInUser.authorityId)==2}">

		<h2>問い合わせ</h2>
		<div class="input_form">
		<form:form action="contact_result" method="post" modelAttribute="contactInfo">
		<form:errors path="contents" cssStyle="color: red"/>

		 <br><label>目的<br>
		 <form:select path="contactCategoryId">
			<form:option value="1">通報</form:option>
			<form:option value="2">問い合わせ</form:option>
			<form:option value="3">要望</form:option>
		 </form:select>
		 </label>

		 <br><br><label>本文
		 <br><form:textarea path="contents"/> </label>

		 <br><br><form:button type= "submit" name= "insert">送信</form:button>
		 </form:form>
		 <br></div>

	</c:if>

	<script>
jQuery( function($) {
    $('tbody tr[data-href]').addClass('clickable').click( function() {
        window.location = $(this).attr('data-href');
    }).find('a').hover( function() {
        $(this).parents('tr').unbind('click');
    }, function() {
        $(this).parents('tr').click( function() {
            window.location = $(this).attr('data-href');
        });
    });
});
</script>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>

		<script type="text/javascript" src="js/checked.js"></script>

	</body>
</html>