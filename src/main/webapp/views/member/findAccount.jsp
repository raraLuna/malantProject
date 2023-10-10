<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String findType = request.getParameter("find");
%>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보 찾기</title>
</head>
<body>
	<div class="choice">
		<input type="radio" name="choice" value="findid"> 아이디 찾기 
		<input type="radio" name="choice" value="findpwd"> 비밀번호 재설정
	</div>
	<div class="view-choice">
	</div>
</body>
</html>