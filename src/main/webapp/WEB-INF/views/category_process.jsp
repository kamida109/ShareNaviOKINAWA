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

				<h2>カテゴリ</h2>

				<div class="input_form">
					<c:if test="${fn:escapeXml(signInUser.authorityId)==1}"><h3>カテゴリ操作</h3></c:if>
					<c:if test="${fn:escapeXml(signInUser.authorityId)==2}"><h3>カテゴリ追加</h3></c:if>
					<p class="error">${existErr}</p>
					<p class="error">${lackErr}</p>
					<form:form action="category_process_check" modelAttribute="category_process" method="Post">
					<c:choose>
						<c:when test="${fn:escapeXml(signInUser.authorityId)==1}">

								<p>
									<form:select path="process">
										<form:option value="追加"/>
										<form:option value="更新"/>
										<form:option value="削除"/>
									</form:select><br><br>
									<form:select path="mainCategory" id="mainCate">
										<form:option value="0" label=""></form:option>
										<form:options items="${mainCategory}" itemValue="categoryId" itemLabel="categoryName"/>
									</form:select>
									<select id="subCate" name="subCate">
										<option selected value="">-------------</option>
									</select>

								</p>
								<p>
									<form:input path="pCategoryName"/>
								</p>

						</c:when>
						<c:otherwise>

								<form:select path="mainCategory">
									<form:option value=""></form:option>
									<form:options items="${mainCategory}" itemValue="categoryId" itemLabel="categoryName"/>
								</form:select>
								<form:input path="pCategoryName"/>
								<form:hidden path="process" value="追加"/>

						</c:otherwise>
					</c:choose>
						<p><button class="btn" type="submit" name="check">確認</button></p>
					</form:form>
				</div>

				<script type="text/javascript">
					$(function(){
						// 親カテゴリを変更したときの処理
						$("#mainCate").change(function(){
							// 親カテゴリが選択されたときに、valueに選択された内容を入れる
							var value = $("#mainCate").val();
							// 子カテゴリ表示
							$("#subCate").show();
							// コントローラに送信
							$.get("pulldown2/"+value, function(data){
								console.log(data);
								var obj = data;
								$("#subCate").html(data);
							})
						})
					})
				</script>
				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>