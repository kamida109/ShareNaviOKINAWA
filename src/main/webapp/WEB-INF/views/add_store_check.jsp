<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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

				<h2>店舗登録</h2>
				<form:form action="add_store_result" modelAttribute="add_store" method="Post" class="addStoreForm" enctype="multipart/form-data">

					名前：<form:input path="storeName" readonly="true"/>

					<div class="categoryPull">
						<div>
							<form:input path="mainCategoryName1" readonly="true"/>
							<form:input path="subCategoryName1" readonly="true"/>
						</div>
						<div>
							<form:input path="mainCategoryName2" readonly="true"/>
							<form:input path="subCategoryName2" readonly="true"/>
						</div>
						<div>
							<form:input path="mainCategoryName3" readonly="true"/>
							<form:input path="subCategoryName3" readonly="true"/>
						</div>

					</div>

					<div>
						住所：
						<form:hidden path="address1"/>
						<form:input path="address1Name" readonly="true"/><br>
						<form:input path="address2" readonly="true"/>
					</div>

					<div>
						TEL：
						<form:input type="tel" path="tel" readonly="true"/>
					</div>

					<div>
						営業時間：<form:textarea path="workTime" style="resize:none; font-size: 17px; margin: 0px; height: 50px; width: 200px;" readonly="true"/>
					</div>

					<div>
						<h3>写真</h3>
						<form:input path="storeImages" value="${formImg}" type="file" multiple="true" name="storeImg" accept="image/jpeg, image/png" onload="OnFileSelect(this);" style="width:300px; height:34px;"/>
					</div>
					<div id="images">
						<p id="selectImage"></p>
					</div>
<%-- 					<form:hidden path="storeImages" value="${formImg}"/> --%>


					<p><button class="btn" type="submit" name="update">登録</button></p>

				</form:form>


				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>