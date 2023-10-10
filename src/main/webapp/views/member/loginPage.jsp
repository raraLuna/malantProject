<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String preReferer = (String) request.getAttribute("referer");
	String loc = (String) request.getAttribute("loc");
	if(loc == null) {
		loc = "common";
	}
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Malant</title>
	<link rel="stylesheet" href="/malant/resources/member/css/loginPage.css">
	<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
	<script>
		/* 유효성 검사 */
		function validate() {
			var id = $('#userid');
			var pwd = $('#pwd');
			var pwd2 = $('#pwd2');
			var email = $('#email');
			var nickname = $('#nickname');
			
			var id_rgx = /^[A-Za-z]{1}[A-Za-z0-9]{5,11}$/;
			var pwd_rgx = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,}$/;

			/* 아이디 유효성 검사 */
			var id_len = id.val().length;
			if(id_len < 6 || id_len > 12) {
				alert("아이디는 6글자 이상이여야합니다.");
				id.select();
				return false;
			}
			if(!id_rgx.test(id.val())) {
				alert("아이디는 영문과 숫자만을 포함해야합니다.");
				id.select();
				return false;
			}

			/* 닉네임 유효성 검사 */
			var name_len = nickname.val().length;
			if(name_len < 2 || name_len > 8) {
				alert("닉네임은 2글자 이상이여야 합니다.");
				nickname.select();
				return false;
			}

			/* 비밀번호 유효성 검사 */
			var pwd_len = pwd.val().length;

			console.log("pwd : " + pwd.val() + ", pwd2 : " + pwd2.val());
			console.log("pwd_len : " + pwd.val().length + ", pwd2_len : " + pwd2.val().length);
			if(pwd.val() != pwd2.val()) {
				alert("비밀번호가 일치하지 않습니다.");
				pwd2.select();
				return false;
			} else if(!pwd_rgx.test(pwd.val())) {
				alert("비밀번호는 영문/숫자/특수문자를 모두 포함하고, 8글자 이상이여야 합니다.");
				pwd.select();
				return false;
			}

			if(dupCheck(function(hasDuplicate) {
				if(hasDuplicate) {
					return false; // 중복이 있으면 validate 함수에서도 false를 반환
				} else {
					// 중복이 없으면 처리 계속 진행
		            return true;
				}
			})) {
				return false;
			}

			return true;
		}

		function dupCheck(callback) {
			$.ajax({
				url: '/malant/checkdup',
				type: 'post',
				data: {
					userid: $('#userid').val(),
					email: $('#email').val()
				},
				dataType: "json",
				success: function(data) {
					if(data.dupid != "ok") {
						alert("이미 사용중인 아이디입니다.");
						callback(true); // 중복된 아이디가 있다고 콜백에 알림
					} else if(data.dupemail != "ok") {
						alert("이미 사용중인 이메일입니다.");
						callback(true); // 중복된 이메일이 있다고 콜백에 알림
					} else {
						callback(false); // 중복이 없음을 콜백에 알림
					}
				},
				error: function(jqXHR, textStatus, errorThrown){
					console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
					callback(true); // 에러가 발생했을 경우 콜백에 알림
				}
			});
		}
	</script>
</head>

<body>
<div class="all-container">
	<div class="sidebar">
		<%
			if(loc.equals("common")) {
		%>
			<%@ include file="../common/sidebar.jsp" %>
		<%
			} else {
		%>
			<%@ include file="../store/common/storeSidebar.jsp" %>
		<% } %>
	</div>
	<div class="container">
		<div class="welcome">
			<div class="pinkbox">
				<div class="signup nodisplay">
					<h1 id="signup-box">회원가입</h1>
					<form action="/malant/menroll" method="post" autocomplete="off" onsubmit="return validate();">
						<input type="hidden" name="signtype" value="common">
						<input type="text" id="userid" name="userid" maxlength="12" placeholder="아이디" required>
						<input type="email" id="email" name="email" placeholder="이메일" required>
						<input type="text" id="nickname" name="nickname" maxlength="8" placeholder="닉네임" required>
						<input type="password" id="pwd" name="userpwd" maxlength="25" placeholder="비밀번호" required>
						<input type="password" id="pwd2" maxlength="25" placeholder="비밀번호 확인" required>
						<button class="button submit">가입하기</button>
					</form>
				</div>
				<div class="signin">
					<h1>MALANT</h1>
					<form action="/malant/logincheck" method="post" class="more-padding" autocomplete="off">
						<input type="hidden" name="preReferer" value="<%= preReferer %>">
						<input type="hidden" name="loc" value="<%= loc %>">
						<input type="text" name="userid" placeholder="아이디" required>
						<input type="password" name="userpwd" placeholder="패스워드" required>
						<button class="button submit">로그인</button>
					</form>
				</div>
			</div>
			<div class="leftbox">
				<h2 class="title"><span>Share</span>&<br>Grow</h2>
				<!-- <p class="desc">pick your perfect <span>bouquet</span></p> -->
				<img class="flower smaller" src="https://image.ibb.co/d5X6pn/1357d638624297b.jpg"
					alt="1357d638624297b" border="0">
				<p class="account">have an account?</p>
				<button class="button" id="signin">로그인</button>
			</div>
			<div class="rightbox">
				<h2 class="title"><span>Share</span>&<br>Grow</h2>
				<!-- <p class="desc"> pick your perfect <span>bouquet</span></p> -->
				<img class="flower" src="https://preview.ibb.co/jvu2Un/0057c1c1bab51a0.jpg" />
				<p class="account">don't have an account?</p>
				<button class="button" id="signup">회원가입</button>
			</div>
		</div>
	</div>
	<script>
		$('#signup').click(function () {
			$('.pinkbox').css('transform', 'translateX(80%)');
			$('.signin').addClass('nodisplay');
			$('.signup').removeClass('nodisplay');
		});

		$('#signin').click(function () {
			$('.pinkbox').css('transform', 'translateX(0%)');
			$('.signup').addClass('nodisplay');
			$('.signin').removeClass('nodisplay');
		});
	</script>
</div>
</body>

</html>