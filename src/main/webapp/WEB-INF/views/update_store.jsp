<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="jp">
	<!-- 共通部品_head -->
	<jsp:include page="/COMMON/head.jsp"/>
	<body>
		<div class="white_noise2">
			<!-- 共通部品_header -->
			<jsp:include page="/COMMON/header.jsp"/>
			<div class="frame">

				<form:form class="input_form" action="updateStoreResult" modelAttribute="update_store">
				<div class="input_form_inner">
					<c:forEach items="${storeDitails}" var="update">

					<div>
						<label>店舗名：</label><form:input path="storeName"/>
						<form:errors path="storeName" />
					</div>

					<div>
						<label>カテゴリ1</label>
						<select id="mainCategory1">
						<c:forEach var="main" items="${mainCategory}">
							<option value="${main.categoryId}">${main.categoryName}</option>
						</c:forEach>
						</select>

						<select id="subCategory1" name="category1">
							<option selected>------------</option>
						</select>
					</div>

					<div>
						<label>カテゴリ2</label>
						<select id="mainCategory2" >
						<c:forEach var="main" items="${mainCategory}">
							<option value="${main.categoryId}">${main.categoryName}</option>
						</c:forEach>
						</select>

						<select id="subCategory2" name="category2">
							<option selected>------------</option>
						</select>
					</div>

					<div>
						<label>カテゴリ3</label>

						<select id="mainCategory3">
						<c:forEach var="main" items="${mainCategory}">
							<option value="${main.categoryId}">${main.categoryName}</option>
						</c:forEach>
						</select>

						<select id="subCategory3" name="category3">
							<option selected>------------</option>
						</select>
					</div>

					<div>
						<label>営業時間：</label><form:input path="businessHours" />
					</div>

					<div>
						<label>市町村:</label>
						<form:select path="citiesId">
							<form:options items="${cities}" itemLabel="citiesName" itemValue="citiesId" />
						</form:select>
					</div>

					<div>
						<label>番地：</label><form:input path="address" />
					</div>

					<div>
						<label>電話番号：</label><form:input path="tel" />
					</div>

					<div>
						<label>評価：</label><form:input path="hyouka" />
					</div>

					<div>
						<label>登録日：</label><form:input path="insertDay" readonly="true" />
					</div>



					</c:forEach>

					<p><button class="btn" type="submit" name="check">確認</button></p>
				</div>
				</form:form>

				<script type="text/javascript" src="js/storeUpdateSelect.js"></script>

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>