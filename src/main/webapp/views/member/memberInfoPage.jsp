<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member member = (Member) request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 보기</title>
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<style>
	.container {
   		display: flex;
   		height: 100vh;
   		align-items: center;
   	}
	
	.content {
		/* border: 1px solid red; */
		border-radius: 10px;
	 	background-color: #f2f2f2;
		width: 500px;
		height: 400px;
		display : flex;
  		align-items : center;
		justify-content: center;
		margin: auto;
	}

	.vertical-align-center {
		vertical-align : middle;
	}

	.email-confirm-btn {
		display: none;
	}
</style>
<script>
	function changeNicknameBoxState() {
		$('#nickname').attr('disabled', !$('#nickname').attr('disabled'));
	}
	
	function changeEmailBoxState() {
		$('#email').attr('disabled', !$('#email').attr('disabled'));
		$('.email-change-btn').hide();
		$('.email-confirm-btn').show();
	}

	function changePwdBoxState() {
		$('.password').attr('disabled', !$('.password').attr('disabled'));
	}
	
	/* 유효성 검사 */
	function validate() {
		var pwd = $('#newpwd');
		var pwd2 = $('#newpwd2');
		var email = $('#email');
		var nickname = $('#nickname');
		
		console.log("id : " + $('#userid').val());
		console.log("email : " + email.val());
		console.log("pwd : " + pwd.val());
		console.log("pwd2 : " + pwd2.val());

		var pwd_rgx = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,}$/;

		/* 닉네임 유효성 검사 */
		console.log("nickname disabled : " + nickname.attr('disabled'));
		if(nickname.attr('disabled') != "disabled") {
			var name_len = nickname.val().length;
			if(name_len < 2 || name_len > 8) {
				alert("닉네임은 2글자 이상이여야 합니다.");
				nickname.select();
				return false;
			}
		}

		/* 비밀번호 유효성 검사 */
		console.log("pwd disabled : " + pwd.attr('disabled'));
		if(pwd.attr('disabled') != "disabled") {
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
		}

		console.log("email.attr('disabled') : " + email.attr('disabled'));
		
		if(email.attr('disabled') != "disabled") {
			if(dupCheck()) {
				return false;
			}
		}

		return true;
	}

	function checkDupEmail() {
		$.ajax({
			url: '/malant/checkdup',
			type: 'post',
			data: {
				userid: $('#userid').val(),
				email: $('#email').val()
			},
			dataType: "json",
			async: false,
			success: function(data) {
				console.log("dupid : " + data.dupid);
				console.log("dupemail : " + data.dupemail);
				if(data.dupemail != "ok") {
					alert('이미 존재하는 이메일입니다.');
					return true;
				} else {
					alert('사용 가능한 이메일입니다.');
					return false;
				}
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
			}
		});

		return false;
	}
	
	// 기존 비밀번호와 새 비밀번호가 일치하는지 검사하는 함수. 나중에 추가할것
	
	
</script>

</head>
<body>
	<div class="container">
		<!-- 사이드바 -->
		<div class="sidebar">
			<%@ include file="../../views/common/sidebar.jsp" %>
		</div>
		
		<!-- 회원 정보 페이지 -->
		<div class="content">
			<form action="/malant/mupdate" method="post" onsubmit="return validate();">
				<input type="hidden" id="userid" name="userid" value="<%= loginMember.getUserId() %>">
				<table class="info-table" border="1" cellspacing="0" cellpadding="1">
					<tr><th>아이디</th><td colspan="2"><%= loginMember.getUserId() %></td></tr>
					<tr>
						<th>닉네임</th>
						<td><input type="text" id="nickname" name="nickname" value="<%= member.getNickname() %>" maxlength="8" placeholder="<%= member.getNickname() %>" disabled></td>
						<td><input type="button" class="nick-change-btn" value="변경" onclick="return changeNicknameBoxState();"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" id="email" name="email" value="<%= member.getEmail() %>" placeholder="<%= member.getEmail() %>" disabled></td>
						<td>
							<input type="button" class="email-change-btn" value="변경" onclick="return changeEmailBoxState();">
							<input type="button" class="email-confirm-btn" value="중복 확인" onclick="return checkDupEmail();">
						</td>
					</tr>
					<tr>
						<th>새 비밀번호</th>
						<td><input type="password" class="password" id="newpwd" name="newpwd" maxlength="25" disabled></td>
						<td rowspan="2"><input type="button" class="pwd-change-btn" value="변경" onclick="return changePwdBoxState();"></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td colspan="2"><input type="password" class="password" id="newpwd2" name="newpwd2" maxlength="25" disabled></td>
					</tr>
					
					<tr><th rowspan="2">수신 설정</th><td colspan="2"><label><input type="checkbox" id="check-alarm" name="check_alarm"
					<% if(member.getAlarmYn().equals("Y")) { %>
						checked
					<% } %>
					>관리 알림 메일</label></td></tr>
					<tr><td colspan="2"><label><input type="checkbox" id="check-notice" name="check_notice"
					<% if(member.getNoticeYn().equals("Y")) { %>
						checked
					<% } %>
					>공지사항 및 이벤트 소식</label></td></tr>
					<tr>
						<th colspan="3">
							<input type="button" value="회원탈퇴" onclick="return withdraw();"> &nbsp; &nbsp; &nbsp;
							<script>
								function withdraw() {
									var ans = confirm('정말로 탈퇴하시겠습니까?');
									
									if(ans) {
										location.href = '/malant/mleave?userno=<%= loginMember.getUserNo()%>';
									}
									
									return false;
								}
							</script>
							<input type="submit" value="변경하기">
						</th>
					</tr>
				</table>
			</form>
			
		</div>
	</div>
</body>
</html>