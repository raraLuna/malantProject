<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 완료</title>
<style>
	.container {
		display: flex;
		height: 100vh;
		align-items: center;
	}
	
	.content {
		margin: auto;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="sidebar">
			<%@ include file="/views/common/sidebar.jsp" %>
		</div>
		<div class="content">
			<h1>웹사이트 회원 탈퇴 완료</h1>
			<h4>회원 탈퇴 신청이 완료되었습니다.</h4>
			<h4>이용해주셔서 감사합니다.</h4>
			<h4><a href="index.jsp">메인페이지로 이동</a></h4>
		</div>
	</div>
</body>
</html>