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
				<h3>お店情報編集</h3>

				<div class="inner_frame_hidden">
					<c:forEach items="${storeDitails}" var="update">

					<div class="error">
						${errMsg}
						<form:errors path="storeName"/>
					</div>

					<form:hidden path="storeId"/>
					<form:hidden path="reviewId"/>

					<div class="inner_frame_hidden">
						<div class="inner_frame_left">
							<p><label>店舗名：</label></p>
							<p><label>営業時間：</label></p>
							<p style="margin-top:43px;"><label>住所：</label></p>
							<p>&emsp;</p>
							<p><label>電話番号：</label></p>
							<p><label>評価：</label></p>
						</div>
						<div class="inner_frame_right">
							<p><form:input path="storeName"/></p>
							<p><form:textarea style=" resize:none; font-size: 17px; margin: 0px; height: 50px; width: 200px;" path="businessHours" /></p>
							<p><form:select path="citiesId">
									<form:options items="${cities}" itemLabel="citiesName" itemValue="citiesId" />
								</form:select><br>
								<form:textarea style=" resize:none; font-size: 17px; margin-top: 3px; height: 50px; width: 192px;" path="address" />
							</p>
							<p><form:input path="tel" /></p>
							<p><form:input path="hyouka" /></p>
						</div>
					</div><br><br>

					<div style="display:inline-block; text-align:left;">カテゴリ：
					<a href="/category_process" style="padding-left:320px; font-size:20px">カテゴリを追加する</a></div>
					<div class="inner_frame_hidden">
						<div class="inner_frame_left">
							<select id="mainCategory1">
							<c:forEach var="main" items="${mainCategory}">
								<option value="${main.categoryId}">${main.categoryName}</option>
							</c:forEach>
							</select><br>
							<select id="subCategory1" name="category1">
								<option selected>------------</option>
							</select>
						</div>
						<div class="inner_frame_left" style="margin: 0px 5px 0px 5px;">
							<select id="mainCategory2" >
							<c:forEach var="main" items="${mainCategory}">
								<option value="${main.categoryId}">${main.categoryName}</option>
							</c:forEach>
							</select><br>
							<select id="subCategory2" name="category2">
								<option selected>------------</option>
							</select>
						</div>
						<div class="inner_frame_left">
							<select id="mainCategory3">
							<c:forEach var="main" items="${mainCategory}">
								<option value="${main.categoryId}">${main.categoryName}</option>
							</c:forEach>
							</select><br>
							<select id="subCategory3" name="category3">
								<option selected>------------</option>
							</select>
						</div>
					</div>

					</c:forEach>

					<br><br><p>
						<button id="update" class="btn" type="submit" name="updateDetails">変更</button>
						<button class="btn" type="submit" name="returnDetails">戻る</button>
					</p>
				</div>
				</form:form>

				<script type="text/javascript" src="js/storeUpdateSelect.js"></script>

			</div>

			<script>
				var up = document.getElementById("update");

				up.addEventListener("click", function(e) {
					const result = confirm("更新しますか？");
					if(result==false) { e.preventDefault(); }
				})
			</script>

			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>