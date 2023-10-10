<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
<style>
.container {
	width: 500px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	margin: auto;
}

.login-section {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

.form-input {
	width: 300px;
	height: 50px;
	margin: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="title"><h1>Malant 관리자</h1></div>
		<div class="login-section">
			<form action="/malant/logincheck" method="post">
				<input type="hidden" name="loc" value="admin">
				<input type="text" class="form-input" name="userid" placeholder="아이디" required><br>
				<input type="password" class="form-input" name="userpwd" placeholder="비밀번호" required><br>
				<input type="submit" class="form-input" value="로그인">
			</form>
		</div>
	</div>
</body>
</html>