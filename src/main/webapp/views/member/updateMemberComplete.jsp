<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 보기</title>
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<style>
	.container {
		display: flex;
	}
	
	.info-container {
		border: 1px solid red;
		width: 500px;
		height: 600px;
		margin: 0 auto;
	}
</style>
</head>
<body>
	<div class="container">
		<!-- 사이드바 -->
		<div class="sidebar-container">
			<%@ include file="../../views/common/sidebar.jsp" %>
		</div>
		
		<!-- 회원 정보 페이지 -->
		<div class="info-container">
			<h1>회원 정보를 수정하였습니다.</h1>
			<a href="/malant">메인페이지로 이동</a>
		</div>
	</div>
</body>
</html>