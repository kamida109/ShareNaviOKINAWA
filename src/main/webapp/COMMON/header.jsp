<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<header>

	<script>
		$(function(){
			path = location.pathname
			if(path.match("home")){
				$("header .home a").addClass("current");
			}
			if(path.match("my_page")){
				$("header .my_page a").addClass("current");
			}
			if(path.match("search")){
				$("header .search a").addClass("current");
			}
			if(path.match("add_store")){
				$("header .add_store a").addClass("current");
			}
			if(path.match("contact")){
				$("header .contact a").addClass("current");
			}
		});
	</script>

	<a href="/home"><h1>ShareNaviOKINAWA</h1></a>
	<span class="signin_userinfo"><a href="/user_info">${fn:escapeXml(signInUser.userName)}</a>さん</span>
	<a class="signout" href="/sign_out">LOGOUT</a>
	<div>
		<ul>
			<li class="home"><a href="/home">HOME</a></li>
			<li class="my_page"><a href="/my_page">MY PAGE</a></li>
			<li class="search"><a href="/search">SEARCH</a></li>
			<li class="add_store"><a href="/add_store">ADD STORE</a></li>
			<li class="contact"><a href="/contact">CONTACT</a></li>
		</ul>
	</div>

</header>