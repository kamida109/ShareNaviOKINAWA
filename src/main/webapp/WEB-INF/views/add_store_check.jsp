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

				<h2>店舗登録</h2>

				<div class="input_form">
				<h3>登録内容</h3>

				<form:form action="add_store_result" modelAttribute="add_store" method="Post" class="addStoreForm" enctype="multipart/form-data">

					<div style="display:inline-block; width: max-content; text-align: right;">
					<div><label style="font-weight:bold;">名前：
						<form:input style="margin-top:20px;" path="storeName" readonly="true"/></label>
					</div><br>
					<div>
						<label style="font-weight:bold;">住所：
						<form:hidden path="address1"/>
						<form:input path="address1Name" readonly="true"/><br>
						<form:input path="address2" readonly="true"/></label>
					</div><br>
					<div>
						<label style="font-weight:bold;">TEL：
						<form:input type="tel" path="tel" readonly="true"/></label>
					</div><br>
					<div>
						<label style="font-weight:bold;">営業時間：
						<form:textarea path="workTime" style="resize:none; font-size: 17px; margin: 0px; height: 50px; width: 200px;" readonly="true"/></label>
					</div>
					</div><br><br>

					<div style="display:inline-block; text-align:left; font-weight:bold;">カテゴリ：</div><div style="display:inline-block; padding-left:350px; text-align:right;"><a href="/category_process" style="font-size: 15px">カテゴリを追加する</a></div>
					<div class="inner_frame_hidden">
						<div class="inner_frame_left">
							<form:hidden path="subCategoryId1"/>
							<form:input path="mainCategoryName1" readonly="true"/><br>
							<form:input path="subCategoryName1" value="${cateName1}" readonly="true"/>
						</div>
						<div class="inner_frame_left" style="margin: 0px 5px 0px 5px;">
							<form:hidden path="subCategoryId2"/>
							<form:input path="mainCategoryName2" readonly="true"/><br>
							<form:input path="subCategoryName2" value="${cateName2}" readonly="true"/>
						</div>
						<div class="inner_frame_left">
							<form:hidden path="subCategoryId3"/>
							<form:input path="mainCategoryName3" readonly="true"/><br>
							<form:input path="subCategoryName3" value="${cateName3}" readonly="true"/>
						</div>
					</div><br><br><br>

					<div>
						<h3>写真</h3>
					</div>
					<div id="images">
						<p id="selectImage">
							${checkImage}
						</p>
					</div>
					<form:hidden path="storeImages" value="${setImages}"/>

					<p>
						<button class="btn" type="button" onclick="location.href='add_store'">戻る</button>
						<button class="btn" type="submit" name="update">登録</button>
					</p>

				</form:form>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>