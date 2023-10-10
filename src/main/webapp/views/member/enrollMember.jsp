<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Malant</title>
<style type="text/css">
	table th { background-color: lightgreen; }
	table#outer { border: 2px solid navy; }
	.profile {
		visibility: hidden;
	}
</style>

<script type="text/javascript" src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
	dupIdFlag = true;
	dupEmailFlag = true;

	// function dupIdCheck() {
	// 	$.ajax({
	// 		url: '/malant/checkdupid',
	// 		type: 'post',
	// 		data: { userid: $('#userid').val() },
	// 		success: function(data) {
	// 			if(data == "ok") {
	// 				dupIdFlag = false;
	// 				alert('사용 가능한 아이디입니다.');
	// 			} else {
	// 				dupIdFlag = true;
	// 				alert('이미 사용중인 아이디입니다.');
	// 				$('#userid').select();
	// 			}
	// 		},
	// 		error: function(jqXHR, textStatus, errorThrown){
	// 			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
	// 		}
	// 	});
		
	// 	return false;
	// }
	
	function confirmEmail() {
		$.ajax({
			url: '/malant/econfirm',
			type: 'post',
			data: { email: $('#email').val() },
			success: function(data) {
				if(data == "ok") {
					alert('이메일로 인증 코드가 전송되었습니다. 이메일을 확인해주세요.');
				} else {
					alert('이미 사용중인 이메일입니다.');
				}
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
			}
		});
		
		return false;
	}
	
	function validateId() {
		$.ajax({
			url: '/malant/checkdupid',
			type: 'post',
			data: { userid: $('#userid').val() },
			success: function(data) {
				if(data != "ok") {
					dupIdFlag = true;
				}
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
			}
		});

		return false;
	}

	function dupIdCheck() {
		var id = $('#userid');
		var cid = /^(?=.*[a-zA-Z0-9])[A-Za-z0-9]{8,12}$/;

		if (!cid.test(id.val())) {
			alert("아이디는 영어 대소문자와 숫자를 포함해서 8~12글자로 작성해주세요.");
			id.select();
			return false;
		} else {
			$.ajax({
				url: '/malant/checkdupid',
				type: 'post',
				data: { userid: id.val() },
				success: function(data) {
					if(data == "ok") {
						dupIdFlag = false;
						alert('사용 가능한 아이디입니다.');
					} else {
						dupIdFlag = true;
						alert('이미 사용중인 아이디입니다.');
						id.select();
					}
				},
				error: function(jqXHR, textStatus, errorThrown){
					console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
				}
			});
			
			return false;
		}
	}


	function validate() {
		var id = $('#userid');
		var pwd = $('#userpwd');
		var pwd2 = $('#userpwd2');
		var nick = $('#nickname');
		var email = $('#email');
		
		//유효성검사를 위한 조건식 지정
		var cid = /^(?=.*[a-zA-Z0-9])[A-Za-z0-9]{8,12}$/;
		var cpwd = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$])[a-zA-Z\d@!#$]{6,12}[a-zA-Z\d@!#$]$/;
		var cnick = /^[A-Za-z0-9가-힣ㄱ-ㅎㅏ-ㅣ]{0,8}$/;
		var cemail = /^[A-Za-z0-9]+@[A-Za-z0-9]+\.[A-Za-z0-9]{2,}$/;
		
		validateId();

		if(dupIdFlag) {
			alert("아이디 중복 검사를 진행해주세요.");
			id.select();
			return false;
		}
		if(dupEmailFlag) {
			alert("이메일 인증을 진행해주세요.");
			email.select();
			return false;
		}
		if (!cid.test(id.val())) {
			alert("아이디는 영어 대소문자와 숫자를 포함해서 8~12글자로 작성해주세요.");
			id.select();
			return false;
		}
		if (!cpwd.test(pwd.val())) {
			alert("패스워드는 영어 대,소문자와 숫자를 포함하고,!@#$기호중 하나를 포함시켜 6~12글자로 사용해주세요.");
			pwd.select();
			return false;
		}
		if(pwd.val() != pwd2.val()) {
			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			pwd2.select();
			return false;
		}
		if (!cnick.test(nick.val())) {
			alert("닉네임은 8자 이하로 작성해주세요.");
			nick.select();
			return false;
		}
		if (!cemail.test(email.val())) {
			alert("이메일은 test@enroll.com 형태로 작성해주세요.");
			email.select();
			return false;
		}
		return true;
	}
	
	function uploadProfile() {
		$('.profile').click();
	}
</script>
</head>
<body>
	<h1>개인회원 가입 페이지</h1>
	<br>
	<form action="/malant/menroll" method="post" onsubmit="return validate();">
		<input type="file" name="profile" class="profile" id="profile" accept="image/*">
		<input type="hidden" name="signtype" value="common">
		<table id="enroll-table" align="center" width="600" cellspacing="5" cellpadding="0">
			<tr><th colspan="3">회원가입 (* 필수항목)</th></tr>
			<tr>
				<th width="150">아이디*</th>
				<td>
					<input type="text" name="userid" id="userid" placeholder="아이디" required> 
					<input type="button" value="중복체크" onclick="return dupIdCheck();"> <br>
				</td>
				<!-- 업로드한 프로필 사진이 보여질 영역 -->
				<td rowspan="4" width="150" height="200" bgcolor="lightgreen" class="profile-area">프로필 사진 영역</td>
			</tr>
			<tr>
				<th>비밀번호*</th>
				<td><input type="password" name="userpwd" id="userpwd" placeholder="비밀번호" required></td>
			</tr>
			<tr>
				<th>비밀번호확인*</th>
				<td><input type="password" id="userpwd2" placeholder="비밀번호 확인" required></td>
			</tr>
			<tr>
				<th>닉네임*</th>
				<td><input type="text" name="nickname" id="nickname" placeholder="닉네임" required></td>
			</tr>
			<tr>
				<th>이메일*</th>
				<td>
					<input type="email" id="email" name="email" placeholder="이메일" required> 
					<input type="button" value="인증 요청" onclick="return checkEmail();">
				</td>
				<!-- 프로필 사진 첨부 버튼 -->
				<td><input type="button" value="사진 추가" onclick="return uploadProfile();"></td>
			</tr>
			<!-- 이메일 인증 구현
			<tr>
				<th>이메일 인증*</th>
				<td>
					<input type="number" class="econfirm" name="inputtoken" placeholder="인증번호" disabled> 
					<input type="button" class="econfirm" value="인증" disabled>
				</td>
			</tr>
			 -->
			<tr>
				<th>이메일 수신 여부</th>
				<td colspan="2">
					<input type="checkbox" id="check-alarm" name="alarm"> 식물 관리 알림<br>
					<input type="checkbox" id="check-notice" name="notice"> 이벤트 및 마케팅 정보
				</td>
			</tr>
			<tr>
				<th colspan="3">
					<input type="submit" value="가입하기"> &nbsp;
					<input type="button" value="이전페이지로" onclick="javascript: history.go(-1);"> &nbsp;
					<input type="button" value="메인페이지로" onclick="javascript: location.href='/malant/index.jsp';">
				</th>
			</tr>
		</table>
	</form>
</body>
</html>