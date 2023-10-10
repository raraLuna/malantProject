<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Malant</title>
<style type="text/css">
	.input-container {
		width: 500px;
		height: 630px;
		border: 1px solid red;
		margin: 0 auto;
		display: flex;
		justify-content: center;
	}
	
	.inputform {
		width: 450px;
		height: 30px;
		margin: 5px auto;
	}
	
	.inputform-phone {
		width: 375px;
		height: 30px;
		margin: 5px auto;
	}
	
	.checkbox-container {
		width: 400px;
		height: 70px;
		margin: 0 auto;
		border: 1px solid red;
	}
	
	.submit-btn_show {
		width: 450px;
		height: 40px;
		margin: auto;
		border: 1px solid red;
		margin-top: 10px;
		margin-bottom: 10px;
	}
	
	.submit-btn_hide {
		width: 450px;
		height: 40px;
		margin: auto;
		display: none;
	}
	
	.form-title {
		border: 1px solid red;
		width: 450px;
		height: 40px;
		text-align: center;
		margin: auto;
		margin-top: 15px;
	}
	
	.cert-request-btn {
		display: inline;
		border: 1px solid red;
	}
</style>

<script type="text/javascript" src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<script text>
	function clickSubmitBtn() {
		$('.submit-btn_hide').click();
	}
	
	$(function() {
		$("#checkall").change(function () {
            if ($(this).prop("checked")) {
                $(".checkbox").prop("checked", true);
            } else {
                $(".checkbox").prop("checked", false);
            }
        });
	});
</script>
</head>
<body>
	
	<div class="input-container">
		
		<form action="/malant/senroll" method="post">
			<input type="hidden" name="signtype" value="seller">
			<div class="form-title">판매회원 가입</div><br>
			<div class="input-name">
				<input type="text" class="inputform" name="businessno" size="20" placeholder="사업자번호" required><br>
				<div class="input-guide-name"></div>
			</div>
			
			<!-- 사업자번호 확인 API 추가 요망 -->
			<div><input type="button" value="사업자번호 인증 요청"></div>
			<div class="input-name">
				<input type="text" class="inputform" name="storename" size="20" placeholder="상호명" required><br>
				<div class="input-guide-name"></div>
			</div>
			
			<!-- 주소검색 API 추가 요망 -->
			<div class="input-name">
				<input type="text" class="inputform" name="address" size="20" placeholder="사업자 주소" required><br>
				<div class="input-guide-address"></div>
			</div>
			
			<div class="input-id">
				<input type="text" class="inputform" name="userid" size="20" placeholder="아이디" required><br>
				<div class="input-guide-id"></div>
			</div>
			<div><input type="button" id="dupid-btn" value="아이디 중복 검사"></div>
			<div class="input-pwd">
				<input type="password" class="inputform" name="userpwd" placeholder="비밀번호(영문+숫자+특수문자)" required><br>
				<div class="input-guide-pwd"></div>
			</div>
			<div class="input-pwd2">
				<input type="password" class="inputform" name="userpwd2" placeholder="비밀번호 확인" required><br>
				<div class="input-guide-pwd2"></div>
			</div>
			<div class="input-email">
				<input type="email" class="inputform" name="email" placeholder="이메일" required><br>
				<div class="input-guide-email"></div>
			</div>
			<div class="input-contact">
				<input type="tel" class="inputform" name="contact" placeholder="휴대폰 번호" required><br>
				<div class="input-guide-contact"></div>
			</div>
			<hr>
			<div style="position: relative; left: 25px;"><input type="checkbox" id="checkall">약관 모두 동의 체크박스</div>
			<div class="checkbox-container">
				<!-- 필수 이용약관에 모두 동의해야 가입 진행되도록 유효성 검사 -->
				<div><input type="checkbox" class="checkbox" name="terms_sell" required>판매 이용약관</div>
				<div><input type="checkbox" class="checkbox" name="terms_financial_transaction" required>전자금융거래이용약관</div>
				<div><input type="checkbox" class="checkbox" name="agree_personal_info" required>판매자 개인정보 수집 및 이용 동의</div>
			</div>
			<div class="submit-btn_show" onclick="clickSubmitBtn();">동의하고 가입하기</div>
			<input type="submit" class="submit-btn_hide" value="동의하고 가입하기">
		</form>
	</div>
</body>
</html>