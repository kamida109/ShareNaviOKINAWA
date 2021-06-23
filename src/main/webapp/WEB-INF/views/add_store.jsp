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
				<form:form action="add_store_check" modelAttribute="add_store" method="Post" class="addStoreForm" enctype="multipart/form-data">

					名前：<form:input path="storeName"/>

					<div class="categoryPull">
						<div>
							<form:select path="mainCategoryId1" id="mainCate1">
								<form:options items="${mainCategory}" itemValue="categoryId" itemLabel="categoryName"/>
							</form:select>
							<select id="subCate1" name="subCate1">
								<option value=""></option>
							</select>
						</div>
						<div>
							<form:select path="mainCategoryId2" id="mainCate2">
								<form:options items="${mainCategory}" itemValue="categoryId" itemLabel="categoryName"/>
							</form:select>
							<select id="subCate2" name="subCate2">
								<option value=""></option>
							</select>
						</div>
						<div>
							<form:select path="mainCategoryId3" id="mainCate3">
								<form:options items="${mainCategory}" itemValue="categoryId" itemLabel="categoryName"/>
							</form:select>
							<select id="subCate3" name="subCate3">
								<option value=""></option>
							</select>
						</div>



						<a href="" style="font-size: 12px">カテゴリを追加する</a>
					</div>

					<div>
						住所：
						<form:select path="address1">
							<form:options items="${cities}" itemValue="citiesId" itemLabel="citiesName"/>
						</form:select><br>

						<form:input path="address2" placeholder="例）小禄2-5-1"/>
					</div>
					<div>
						TEL：
						<form:input type="tel" path="tel" placeholder="ハイフン\"-\"不要"/>
					</div>


					<div>
						営業時間：<form:textarea path="workTime" style=" resize:none; font-size: 17px; margin: 0px; height: 50px; width: 200px;" placeholder="例）10:00～19:00&#13;　　火曜定休日"/>
					</div>

					<div>
						<h3>写真</h3>
						<form:input path="storeImages" type="file" multiple="true" name="storeImg" accept="image/jpeg, image/png" style="width:300px; height:34px;" onchange="OnFileSelect( this );" />
					</div>

					<div id="images">
						<p id="selectImage">選択されていません。</p>
					</div>

					<p><button class="btn" type="submit" name="check">確認</button></p>

				</form:form>


				<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
				<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
				<script>
					$(function(){
						// 子カテゴリをはじめは非表示
						$("#subCate1").hide();
						$("#subCate2").hide();
						$("#subCate3").hide();
						// 親カテゴリを変更したときの処理
						$("#mainCate1").change(function(){
							// 親カテゴリが選択されたときに、valueに選択された内容を入れる
							var value = $("#mainCate1").val();
							// 子カテゴリ表示
							$("#subCate1").show();
							// コントローラに送信
							$.get("pulldown/"+value, function(data){
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
							$.get("pulldown/"+value, function(data){
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
							$.get("pulldown/"+value, function(data){
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