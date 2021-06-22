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
				<form:form action="add_store_check" modelAttribute="add_store" method="Post" class="addStoreForm">

					名前：<form:input path="storeName"/>

					<div class="categoryPull">
						<div>
							<form:select path="mainCategory" class="mainCate">
								<form:options items="${mainCategory}" itemValue="categoryId" itemLabel="categoryName"/>
							</form:select>
							<form:select path="category" class="subCate">
								<form:options items="${childCategory}" itemValue="categoryId" itemLabel="categoryName"/>
							</form:select>
						</div>

						<div>
							<form:select path="mainCategory" class="mainCate">
								<form:options items="${mainCategory}" itemValue="categoryId" itemLabel="categoryName"/>
							</form:select>
							<form:select path="category" class="subCate">
								<form:options items="${childCategory}" itemValue="categoryId" itemLabel="categoryName"/>
							</form:select>
						</div>

						<div>
							<form:select path="mainCategory" class="mainCate">
								<form:options items="${mainCategory}" itemValue="categoryId" itemLabel="categoryName"/>
							</form:select>
							<form:select path="category" class="subCate">
								<form:options items="${childCategory}" itemValue="categoryId" itemLabel="categoryName"/>
							</form:select>
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
						営業時間：<form:textarea path="workTime" style=" resize:none; font-size: 17px; margin: 0px; height: 50px; width: 200px;" placeholder="例）10:00～19:00&#13;　　火曜定休日"/>
					</div>

					<div>
						<h3>写真</h3>
						<input type="file" name="storeImg" accept="image/jpeg, image/png" width="100px">
					</div>

				<p><button class="btn" type="submit" name="check">確認</button></p>

				</form:form>
				<form action="add_store_check">
					<p><button class="btn" type="submit" name="check">確認</button></p>
				</form>

				<!-- ---------- ここまで本体 ---------- -->

			</div>
			<!-- 共通部品_footer -->
			<footer id="footer"></footer>
		</div>
	</body>
</html>