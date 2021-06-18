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

				<!-- ---------- ここから本体 ---------- -->

				<!-- ----------- ここからダミー ----------- -->
				<div class="input_form">
				<div class="input_form_inner">
					<p><label>キーワード：<input type="text" name="keyword"/></label></p>
					<p><label>カテゴリ：<select name="category">
						<option></option>
						<option>飲食店</option>
						<option>アウトドア</option>
						<option>インドア</option>
					</select></label></p>
					<p><label>場所：<select name="category">
						<option></option>
						<option>那覇市</option>
						<option>浦添市</option>
						<option>沖縄市</option>
					</select></label></p>
				</div>
					<p><label><input type="checkbox" class="btn" type="submit" name="rank"/>☆3以上のお店のみ表示</label></p>
					<p><button class="btn" type="submit" name="select">検索</button></p>
				</div>
				<!-- ----------- ここまでダミー ----------- -->

				<details open>
				<summary>店名検索</summary>
					<jsp:include page="/COMMON/table_store.jsp"/>
				</details>

				<details open>
				<summary>あいまい検索</summary>
					<jsp:include page="/COMMON/table_store.jsp"/>
				</details>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>
