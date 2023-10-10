<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="store.order.model.vo.ProductOrder, java.util.ArrayList, store.shoppingBasket.model.vo.ShoppingBasket"%>
<%
ArrayList<ShoppingBasket> olist = (ArrayList<ShoppingBasket>) session.getAttribute("sblist");
session.setAttribute("olist", olist);
%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="/malant/resources/store/css/orderInputForm.css" />
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://js.tosspayments.com/v1/payment-widget"></script>

<style>
#popupFrame {
	position: absolute;
	top: 30%;
	left: 30%;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background-color: white;
}
</style>

</head>
<body>
	<div class="container">
		<!-- 사이드바 -->
		<%@ include file="../common/storeSidebar.jsp"%>
		<div class="content">
			<div class="order-productline">
				<h1>주문 상품</h1>
				<hr border=100vh>
				<table style="width: -webkit-fill-available; text-align: center;"
					border="1px;">
					<tr>
						<td></td>
						<td>제품 정보</td>
						<td>가 격</td>
						<td>배송비</td>
						<td>수 량</td>
						<td>총 금액</td>
					</tr>
					<%
					for (ShoppingBasket sb : olist) {
					%>
					<tr>
						<td><img width="100px" height="100px"
							src="<%=sb.getProductThumnail()%>"></td>
						<td><%=sb.getProductName()%></td>
						<td><%=sb.getPrice()%></td>
						<td><%=sb.getDeliveryChage()%></td>
						<td><%=sb.getQuantity()%></td>
						<td><%=sb.getTotalPrice()%></td>
					</tr>
					<%
					}
					%>
					<tr>
						<td colspan="6" style="text-align: right;">총금액 : <%
						int total = 0;
						for (ShoppingBasket sb : olist) {
							total += sb.getTotalPrice();
						}
						out.print(total);
						%></td>
					</tr>
				</table>
			</div>

			<div class="order-inputform">
				<h3>주문자 정보</h3>
				<form action="/malant/Osheet" method="post" id="ordersheet">
					<label>이 &nbsp; 름 : </label><input type="text" name="buyerName" required><br>
					<label>연락처 : </label><input type="tel" name="buyerContact" required><br>
					<label>이메일 : </label><input type="email" name="email" required><br>

					<h3>배송지 정보</h3>
					<label>이 &nbsp; 름 : </label> <input type="text" name="recipient" required><br>
					<label>연락처 : </label><input type="tel" name="recipient_contact" required><br>
					<!-- 주소 API -->
					<label>주 &nbsp; 소 : </label> <input type="text" id="postcode" name="postcode" placeholder="우편번호" required> &nbsp;
					<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" id="address" name="address" placeholder="주소" required><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" required>
					<input type="text" id="extraAddress" name="extraAddress" placeholder="참고주소">
					<input type="hidden" value="<%=olist.get(0).getProductName()%> 등 <%=olist.size()%> 건" name="productName">
					<input type="hidden" value="<%=olist.get(0).getProductThumnail()%>" name="productThumnail">
					<input type="hidden" value="<%=total%>" name="totalprice">
					<input type="hidden" value="<%=loginMember.getUserNo()%>" name="userno">


					<!-- iframe -->
					<div id="layer"
						style="display: none; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
						<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
							id="btnCloseLayer"
							style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
							onclick="closeDaumPostcode()" alt="닫기 버튼">
					</div>
					<br> <label>배송메모 : </label> <input type="text"
						id="delivery_note" name="delivery_note"><br>
					<hr>
				</form>
				<div id="payment-method"></div>
				<div id="agreement"></div>
				<input type="reset" value="취소하기">
				<button type="submit" id="go-pay-button" form="ordersheet">결제하기</button>
				<iframe id="popupFrame"
					style="display: none; width: 800px; height: 600px;"></iframe>
				<div style="height: 200px;"></div>
			</div>
		</div>
	</div>


	<script>
		//////////////////////////////////// 주소 API /////////////////////////////////

		// 우편번호 찾기 화면을 넣을 element
		var element_layer = document.getElementById('layer');

		function closeDaumPostcode() {
			// iframe을 넣은 element를 안보이게 한다.
			element_layer.style.display = 'none';
		}

		function execDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var addr = ''; // 주소 변수
							var extraAddr = ''; // 참고항목 변수

							//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								addr = data.roadAddress;
							} else { // 사용자가 지번 주소를 선택했을 경우(J)
								addr = data.jibunAddress;
							}

							// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
							if (data.userSelectedType === 'R') {
								// 법정동명이 있을 경우 추가한다. (법정리는 제외)
								// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								if (data.bname !== ''
										&& /[동|로|가]$/g.test(data.bname)) {
									extraAddr += data.bname;
								}
								// 건물명이 있고, 공동주택일 경우 추가한다.
								if (data.buildingName !== ''
										&& data.apartment === 'Y') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								if (extraAddr !== '') {
									extraAddr = ' (' + extraAddr + ')';
								}
								// 조합된 참고항목을 해당 필드에 넣는다.
								document.getElementById("extraAddress").value = extraAddr;

							} else {
								document.getElementById("extraAddress").value = '';
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('postcode').value = data.zonecode;
							document.getElementById("address").value = addr;
							// 커서를 상세주소 필드로 이동한다.
							document.getElementById("detailAddress").focus();

							// iframe을 넣은 element를 안보이게 한다.
							// (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
							element_layer.style.display = 'none';
						},
						width : '100%',
						height : '100%',
						maxSuggestItems : 5
					}).embed(element_layer);

			// iframe을 넣은 element를 보이게 한다.
			element_layer.style.display = 'block';

			// iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
			initLayerPosition();
		}

		// 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
		// resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
		// 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
		function initLayerPosition() {
			var width = 500; //우편번호서비스가 들어갈 element의 width
			var height = 700; //우편번호서비스가 들어갈 element의 height
			var borderWidth = 2; //border의 두께

			// 위에서 선언한 값들을 실제 element에 넣는다.
			element_layer.style.width = width + 'px';
			element_layer.style.height = height + 'px';
			element_layer.style.border = borderWidth + 'px solid';
			// 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
			element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width) / 2 - borderWidth)
					+ 'px';
			element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height) / 2 - borderWidth)
					+ 'px';
		}
 }
	</script>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	</script>
</body>
</html>