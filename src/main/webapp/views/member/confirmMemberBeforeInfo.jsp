<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%
	String userid = request.getParameter("userid");
%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원정보 확인</title>
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<style>
.container {
	display: flex;
	height: 100vh;
	align-items: center;
}

.content {
	width: 500px;
	height: 300px;
	/* border: 1px solid red; */
	margin: auto;
	border-radius: 10px;
	background-color: #f2f2f2;
	padding: 30px;
	text-align: center;
}


</style>
</head>

<body>
	<div class="container">
		<!-- 사이드바 -->
		<div class="sidebar">
			<%@ include file="../common/sidebar.jsp"%>
		</div>

		<!-- 컨텐츠 영역 -->
		<div class="content">
		<br><br>
			<h1>회원정보 확인</h1><br>
			<h4><%= loginMember.getNickname() %> 님의 정보를 안전하게 보호하기 <br> 위해 비밀번호를 다시 한번 확인합니다.</h4>
			<form action="/malant/loginminfo" method="post" >
				<input type="hidden" id="userid" name="userid" value="<%= userid %>">

				<table style="display: grid;justify-content: center;justify-items: center;">
					<tr>
						<th>아이디</th>
						<td><%= userid %></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" id="userpwd" name="userpwd"
							required></td>
					</tr>
				</table>
				<br>
				<input type="submit" value="확인"> <input type="button"
					value="취소" onclick="javascript:history.go(-1)">
			</form>
		</div>
	</div>		
</body>

</html>