<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

				<div class="input_form">
				<div class="input_form_inner">
					<p><label>キーワード：<input type="text" id="keyWord"/></label></p>

					<p><label>カテゴリ：</label>
					<select id="mainCategory">
					<c:forEach var="main" items="${mainCategory}">
						<option value="${main.categoryId}">${main.categoryName}</option>
					</c:forEach>
					</select>

					<select id="subCategory" name="name">
						<option selected>------------</option>
					</select>
					</p>

					<p><label>場所：<select id="prace">
						<c:forEach var="city" items="${cities}">
							<option value="${city.citiesId}">${city.citiesName}</option>
						</c:forEach>
					</select></label></p>
				</div>
					<p><label><input type="checkbox" id="check"/>☆3以上のお店のみ表示</label></p>
					<p><button class="btn" type="button" name="select" id="button">検索</button></p>
				</div>

				<details open>
				<summary>店名検索</summary>
				<span id="search">
					<jsp:include  page="/COMMON/table_store.jsp"/>
				</span>
				</details>

				<details open>
				<summary>あいまい検索</summary>
					<jsp:include page="/COMMON/table_store.jsp"/>
				</details>


				<script>
				$(function(){
					// 親カテゴリを変更したときの処理
					$("#mainCategory").change(function(){
						// 親カテゴリが選択されたときに、valueに選択された内容を入れる
						let value = $("#mainCategory").val();
						if(value != 0){
							// 子カテゴリ表示
							$("#subCategory").show();
							// コントローラに送信
							$.get("pulldown/"+value, function(data){
								console.log(data);
								$("#subCategory").html(data);
							})
						} else {
							$("#subCategory").hide();
						}
					})
				});

				// 検索非同期
				$("#button").click(function(){
					let keyWord = $("#keyWord").val();
					let subCategory = $("#subCategory").val();
					let prace = $("#prace").val();
					let check = $('#check').prop("checked");


					$.get("result/"+keyWord+'/'+subCategory+'/'+prace+'/'+check, "turbolinks:load",function(data){
						location.reload();
					})
				})

				</script>

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>
