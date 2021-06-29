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
				<h3>お店の追加</h3>

				<form:form action="add_store_check" modelAttribute="add_store" method="Post" class="addStoreForm" enctype="multipart/form-data">

					<div style="display:inline-block; width: max-content; text-align: right;">
					<div><label>名前：
						<form:input style="margin-top:20px;" path="storeName"/></label>
					</div><br>
					<div>
						<label>住所：
						<form:select path="address1">
							<form:options items="${cities}" itemValue="citiesId" itemLabel="citiesName"/>
						</form:select></label><br>
						<form:input path="address2" placeholder="例）小禄2-5-1"/>
					</div><br>
					<div>
						<label>TEL：
						<form:input type="tel" maxlength="20" path="tel" placeholder="ハイフン\"-\"不要"/></label>
					</div><br>
					<div>
						<label>営業時間：
						<form:textarea path="workTime" style=" resize:none; font-size: 17px; margin: 0px; height: 50px; width: 200px;" placeholder="例）10:00～19:00 &#13;　　火曜定休日"/></label>
					</div>
					</div><br><br>

					<div style="display:inline-block; text-align:left;">カテゴリ：</div><div style="display:inline-block; padding-left:330px; text-align:right;"><a href="/category_process" style="font-size:20px">カテゴリを追加する</a></div>
					<div class="inner_frame_hidden">
						<div class="inner_frame_left">
							<form:select path="mainCategoryId1" id="mainCate1">
								<form:option value="">-------------</form:option>
								<form:options items="${mainCategory}" itemValue="categoryId" itemLabel="categoryName"/>
							</form:select><br>
							<select id="subCate1" name="subCate1">
								<option selected>-------------</option>
							</select>
						</div>
						<div class="inner_frame_left" style="margin: 0px 5px 0px 5px;">
							<form:select path="mainCategoryId2" id="mainCate2">
								<form:option value="">-------------</form:option>
								<form:options items="${mainCategory}" itemValue="categoryId" itemLabel="categoryName"/>
							</form:select><br>
							<select id="subCate2" name="subCate2">
								<option selected>-------------</option>
							</select>
						</div>
						<div class="inner_frame_left">
							<form:select path="mainCategoryId3" id="mainCate3">
								<form:option value="">-------------</form:option>
								<form:options items="${mainCategory}" itemValue="categoryId" itemLabel="categoryName"/>
							</form:select><br>
							<select id="subCate3" name="subCate3">
								<option selected>-------------</option>
							</select>
						</div>
					</div><br><br><br>

					<div>
						<span>写真：</span>
						<form:input path="storeImages" type="file" multiple="true" name="storeImg" accept="image/jpeg, image/png" style="width:300px; height:34px;" onchange="OnFileSelect( this );" />
					</div>

					<div id="images">
						<p id="selectImage">選択されていません。</p>
					</div>

					<p><button class="btn" type="submit" name="check">確認</button></p>

				</form:form>
				</div>

				<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
				<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
				<script>
					$(function(){
						// 親カテゴリを変更したときの処理
						$("#mainCate1").change(function(){
							// 親カテゴリが選択されたときに、valueに選択された内容を入れる
							var value = $("#mainCate1").val();
							// 子カテゴリ表示
							$("#subCate1").show();
							// コントローラに送信
							$.get("pulldown2/"+value, function(data){
								console.log(data);
								var obj = data;
								$("#subCate1").html(data);
							})
						})
						$("#mainCate2").change(function(){
							// 親カテゴリが選択されたときに、valueに選択された内容を入れる
							var value = $("#mainCate2").val();
							// 子カテゴリ表示
							$("#subCate2").show();
							// コントローラに送信
							$.get("pulldown2/"+value, function(data){
								console.log(data);
								var obj = data;
								$("#subCate2").html(data);
							})
						})
						$("#mainCate3").change(function(){
							// 親カテゴリが選択されたときに、valueに選択された内容を入れる
							var value = $("#mainCate3").val();
							// 子カテゴリ表示
							$("#subCate3").show();
							// コントローラに送信
							$.get("pulldown2/"+value, function(data){
								console.log(data);
								var obj = data;
								$("#subCate3").html(data);
							})
						})
					})
					function OnFileSelect(inputElement) {
						// ファイルリストを取得
						var imgList = inputElement.files;

						// ファイルの数を取得
						var imgCount = imgList.length;

						// 読み込みが完了したファイルの数
						var loadCompleteCount = 0;
						// <p id="selectImage"></p>に挿入するタグ
						var imageList = "";

						// 選択されたファイルの数だけ処理する
						for (var i = 0; i < imgCount; i++) {
							// FileReaderを生成
							var fileReader = new FileReader();
							// ファイルを取得
							var img = imgList[i];
							// 読み込み完了時の処理を追加
							fileReader.onload = function() {
								// <img>タグの生成
								imageList += "<img src=\"" + this.result + "\" width=\"400px\" />\n";
								// 選択されたファイルすべの処理が完了したら、<p>タグに流し込む
								if (++loadCompleteCount == imgCount) {
									// <p>タグに<img>を流し込む
									document.getElementById("selectImage").innerHTML = imageList;
								}
							};
							// ファイルの読み込み(Data URI Schemeの取得)
							fileReader.readAsDataURL(img);
						}
					}

				</script>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>