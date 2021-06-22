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

				<form:form class="input_form" action="searchResult" modelAttribute="userInfo" method="GET">
				<fieldset class="input_form_inner">

					<p><label>キーワード：<form:input path="storeName" /></label></p>

					<p>
					<label>カテゴリ：</label>
						<form:select id="mainCategory" path="mainCategoryId">
							<form:options items="${mainCategory}" itemLabel="categoryName" itemValue="categoryId" />
						</form:select>

						<select id="subCategory">
	 						<option value=""></option>
						</select>
					</p>

					<p>
					<label>場所：
						<form:select path="citiesId">
							<form:options items="${cities}" itemLabel="citiesName" itemValue="citiesId" />
						</form:select>
					</label>
					</p>
					</fieldset>

					<p><label><form:checkbox path="hyouka" />☆3以上のお店のみ表示</label></p>

					<form:button>検索</form:button>

				</form:form>

				<details open>
				<summary>店名検索</summary>
					<jsp:include page="/COMMON/table_store.jsp"/>
				</details>

				<details open>
				<summary>あいまい検索</summary>
					<jsp:include page="/COMMON/table_store.jsp"/>
				</details>

				<script>
					$(function(){
						// 子カテゴリをはじめは非表示
						$("#subCategory").hide();
						// 親カテゴリを変更したときの処理
						$("#mainCategory").change(function(){
							// 親カテゴリが選択されたときに、valueに選択された内容を入れる
							var value = $("#mainCategory").val();
							// 子カテゴリ表示
							$("#subCategory").show();
							// コントローラに送信
							$.get("pulldown/"+value, function(data){
								console.log(data);
								var obj = data;
								$("#subCategory").html(data);
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
