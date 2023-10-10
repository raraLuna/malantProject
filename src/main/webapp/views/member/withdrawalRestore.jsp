<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.Date" %>
<%
	String userNo = (String) request.getAttribute("userNo");	
	Date wdDate = (Date) request.getAttribute("withdrawalDate");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴 철회</title>
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
			<h1>고객님 아이디는 회원탈퇴 처리 중입니다.</h1>
			<h4>회원탈퇴일자 : <%= wdDate %> </h4>
			<h4>회원탈퇴일자로부터 15일의 유예기간 동안에만 회원탈퇴 취소가 가능합니다.<br>
			[회원탈퇴 취소] 후에 다시 서비스를 이용하실 수 있습니다.</h4>
			
			<input type="button" value="회원탈퇴 취소" onclick="restoreWithdrawal();">
			<script>
				function restoreWithdrawal() {
					location.href = "/malant/wdrestore?userno=<%= userNo %>";
				}
			</script>
		</div>
	</div>
</body>
</html>