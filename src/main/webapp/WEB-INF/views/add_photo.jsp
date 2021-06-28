<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div class="input_form">
		<div class="input_form_inner">
			<form:form action="add_photo_check" modelAttribute="add_photo" method="Post" class="addStoreForm" enctype="multipart/form-data">
				<div>
					<h3>写真</h3>
					<form:input path="storeImages" type="file" multiple="true" name="storeImg" accept="image/jpeg, image/png" style="width:300px; height:34px;" onchange="OnFileSelect( this );" />
				</div>

				<div id="images">
					<p id="selectImage">選択されていません。</p>
				</div>
				<form:hidden path="storeId" value="${nowStoreId}"/>

				<p><button class="btn" type="submit" name="check">確認</button></p>
			</form:form>
		</div>
		</div>
		</div>
	</div>
	<script>
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


	<!-- 共通部品_footer -->
	<footer id="footer"></footer>
	</body>
</html>