<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Malant</title>
</head>
<body>
<h1>결제 실패 </h1>

 	<h3>결제 오류 : <%= request.getAttribute("message") %></h3>

 <a href="/malant/index.jsp">스토어 메인으로</a>
 
</body>
</html>
