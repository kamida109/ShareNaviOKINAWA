<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="jp">
	<!-- 共通部品_head -->
	<jsp:include page="/COMMON/head.jsp"/>
	<body>
		<div class="white_noise1">
			<div class="sign_up_frame">

				<!-- ---------- ここから本体 ---------- -->

				<h2>新規登録</h2>

				<p>IDとPASSは半角英数字で入力してください</p>

				<p class="error">${msg}</p>

				<form:form action="sign_up" method="post" modelAttribute="signUp" >
				<div><div class="sign_form_hidden">
					<p><label>NAME : <form:input type="text" path="userName"/></label></p>
					<p><label>  ID : <form:input type="text" path="loginId"/></label></p>
					<p><label>PASS : <form:input type="password" path="password"/></label></p>
					<p><label>PASS(再) : <form:input type="password" path="passwordRe"/></label></p>
				</div></div>
				<div class="sign_form">
					お住まいの地域 または、宿泊地域を選択してください<br>
					<form:select id="prace" path="citiesId">
						<c:forEach var="city" items="${cities}">
							<form:option value="${city.citiesId}">${city.citiesName}</form:option>
						</c:forEach>
					</form:select>
				</div><br>
				<div class="sign_form" style="margin-top:10px">
					この中であなたが好きなものを3つまで選んでください<br>
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
					<p><form:button id="insert" class="btn" type="submit" name="insert">登録</form:button></p>
				</form:form>

				<p><button class="btn" type="button" onclick="location.href='sign_in'">戻る</button></p>

				<script>
					$(function(){
						// valueに選択された内容を入れる
						var value1 = $("#mainCategory1").val();
						var value2 = $("#mainCategory2").val();
						var value3 = $("#mainCategory3").val();
						// コントローラに送信
						$.get("selected/"+value1+"/0", function(data){
							console.log(data);
							var obj = data;
							$("#subCategory1").html(data);
						})
						$.get("selected/"+value2+"/1", function(data){
							console.log(data);
							var obj = data;
							$("#subCategory2").html(data);
						})
						$.get("selected/"+value3+"/2", function(data){
							console.log(data);
							var obj = data;
							$("#subCategory3").html(data);
						})
					})
					$(function(){
						// 親カテゴリを変更したときの処理
						$("#mainCategory1").change(function(){
							// 親カテゴリが選択されたときに、valueに選択された内容を入れる
							let value = $("#mainCategory1").val();
							if(value != 0){
								// 子カテゴリ表示
								$("#subCategory1").show();
								// コントローラに送信
								$.get("pulldown/"+value, function(data){
									console.log(data);
									$("#subCategory1").html(data);
								})
							} else {
								$("#subCategory1").hide();
							}
						})
					})
					$(function(){
						// 親カテゴリを変更したときの処理
						$("#mainCategory2").change(function(){
							// 親カテゴリが選択されたときに、valueに選択された内容を入れる
							let value = $("#mainCategory2").val();
							if(value != 0){
								// 子カテゴリ表示
								$("#subCategory2").show();
								// コントローラに送信
								$.get("pulldown/"+value, function(data){
									console.log(data);
									$("#subCategory2").html(data);
								})
							} else {
								$("#subCategory2").hide();
							}
						})
					})
					$(function(){
						// 親カテゴリを変更したときの処理
						$("#mainCategory3").change(function(){
							// 親カテゴリが選択されたときに、valueに選択された内容を入れる
							let value = $("#mainCategory3").val();
							if(value != 0){
								// 子カテゴリ表示
								$("#subCategory3").show();
								// コントローラに送信
								$.get("pulldown/"+value, function(data){
									console.log(data);
									$("#subCategory3").html(data);
								})
							} else {
								$("#subCategory3").hide();
							}
						})
					})

					var up = document.getElementById("insert");

					up.addEventListener("click", function(e) {
						const result = confirm("この内容で登録しますか？");
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
