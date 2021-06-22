<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

				<h2>登録情報変更</h2>

				<form:form action="user_info_check" method="post" modelAttribute="userInfo">
					<div class="out_frame">
						<div class="inner_frame_hidden">
							<div class="inner_frame_left">
								ID：<br>
								名前：<br>
								お住まいの地域：<br>
							</div>
							<div class="inner_frame_right">
								<form:input type="text" path="loginId"/><br>
								<form:input type="text" path="userName"/><br>
								<form:select path="citiesId">
									<form:options items="${cities}" itemLabel="citiesName" itemValue="citiesId" />
								</form:select><br>
							</div>
						</div>
						<div style="margin-top: 130px; clear: both;">
						<span style="font-weight: bold;">好きなカテゴリ<br></span>
						<div class="inner_frame_hidden">
							<div class="inner_frame_left">
								<form:select id="mainCategory1" path="mainCategoryId1">
									<form:options items="${mainCategory}" itemLabel="categoryName" itemValue="categoryId" />
								</form:select><br>
								<form:select id="subCategory1" path="categoryId1">
									<form:options items="${category}" itemLabel="categoryName" itemValue="categoryId" />
								</form:select>
							</div>
							<div class="inner_frame_left">
								<form:select id="mainCategory2" path="mainCategoryId2">
									<form:options items="${mainCategory}" itemLabel="categoryName" itemValue="categoryId" />
								</form:select><br>
								<form:select id="subCategory2" path="categoryId2">
									<form:options items="${category}" itemLabel="categoryName" itemValue="categoryId" />
								</form:select>
							</div>
							<div class="inner_frame_left">
								<form:select id="mainCategory3" path="mainCategoryId3">
									<form:options items="${mainCategory}" itemLabel="categoryName" itemValue="categoryId" />
								</form:select><br>
								<form:select id="subCategory3" path="categoryId3">
									<form:options items="${category}" itemLabel="categoryName" itemValue="categoryId" />
								</form:select>
							</div>
						</div>
						</div>
					</div>
					<p><form:button id="update" class="btn" type="submid" name="update">確認</form:button></p>
				</form:form>

				<script>
					$(function(){
						// 子カテゴリをはじめは非表示
						$("#subCategory1").hide();
						// 親カテゴリを変更したときの処理
						$("#mainCategory1").change(function(){
							// 親カテゴリが選択されたときに、valueに選択された内容を入れる
							var value = $("#mainCategory1").val();
							// 子カテゴリ表示
							$("#subCategory1").show();
							// コントローラに送信
							$.get("pulldown/"+value, function(data){
								console.log(data);
								var obj = data;
								$("#subCategory1").html(data);
							})
						})
					})
					$(function(){
						$("#subCategory2").hide();
						$("#mainCategory2").change(function(){
							var value = $("#mainCategory2").val();
							$("#subCategory2").show();
							$.get("pulldown/"+value, function(data){
								console.log(data);
								var obj = data;
								$("#subCategory2").html(data);
							})
						})
					})
					$(function(){
						$("#subCategory3").hide();
						$("#mainCategory3").change(function(){
							var value = $("#mainCategory3").val();
							$("#subCategory3").show();
							$.get("pulldown/"+value, function(data){
								console.log(data);
								var obj = data;
								$("#subCategory3").html(data);
							})
						})
					})

					var up = document.getElementById("update");

					up.addEventListener("click", function(e) {
						const result = confirm("更新しますか？");
						if(result==false) { e.preventDefault(); }
					})

				</script>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>