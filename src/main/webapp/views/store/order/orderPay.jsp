<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="store.order.model.vo.ProductOrder, java.util.ArrayList, store.order.model.vo.ProductOrder, store.shoppingBasket.model.vo.ShoppingBasket"%>
<%
ProductOrder porder = (ProductOrder) request.getAttribute("productOrder");
session.setAttribute("porder",porder);
ArrayList<ShoppingBasket> olist = (ArrayList<ShoppingBasket>) session.getAttribute("olist");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="/malant/resources/store/css/orderInputForm.css" />
<script src="https://js.tosspayments.com/v1/payment-widget"></script>
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


</head>
<body>
	<div class="container" style="
    padding-top: 10px;
    padding-left: 100px;
    padding-right: 100px;
    padding-bottom: 100px;">
		<div class="content">
			<div class="order-productline">
				<div style="width: -webkit-fill-available; text-align: center;"
					border="1px;">
					<h1>주문 상품</h1>
					<hr border=100vh>
					<img src="<%=porder.getThumbnailImg()%>" height=250px>
					<h2><%=porder.getProductName()%></h2>
					<h1><%=porder.getTotalPrice()%>원
					</h1>
					<hr border=100vh>
				</div>
			</div>
			<div id="payment-method"></div>
			<div id="agreement"></div>
			<div style="width: -webkit-fill-available; text-align: center;"
				border="1px;">
				<input type="reset" value="취소하기">
				<button id="payment-button">결제하기</button>
			</div>
			<div style="height: 100px;"></div>
		</div>
	</div>


<script>
	
/////////////////////////////////////////////결제 API ////////////////////////////////////////////////////////////



$(document).ready(function() {
		const clientKey = "test_ck_ex6BJGQOVD9JEkOdBwXrW4w2zNbg"
    	const customerKey = "<%=porder.getUserNo()%>" // userNo
        const paymentWidget = PaymentWidget(clientKey, customerKey)
        
        console.log('<%=porder.getOrderId()%> + <%=porder.getProductName()%> + <%=porder.getBuyerName()%>')
        
        paymentWidget.renderPaymentMethods("#payment-method", { value : <%=porder.getTotalPrice()%> });
		paymentWidget.renderAgreement('#agreement')
		document.querySelector("#payment-button").addEventListener("click", () => {
 			 	  paymentWidget.requestPayment({
  				  orderId: "<%=porder.getOrderId()%>", // 주문 ID(직접 만들어주세요)
  				  orderName: "<%=porder.getProductName()%>", // 주문명
   			      customerEmail: "<%=porder.getEmail()%>",
  				  customerName: "<%=porder.getBuyerName()%>",
  				 successUrl: "http://localhost:8080/malant/opok",
  				  failUrl: "http://localhost:8080/malant/opok"
			  	})
<%-- 				.then(function(orderRes){ 
					
					 var idempotencyKey = generateUUID();
						function generateUUID() {
						  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
						    var r = Math.random() * 16 | 0,
						      v = c === 'x' ? r : (r & 0x3 | 0x8);
						    return v.toString(16);
						  });
						}
				        
				        const headers = {
						        "Authorization": "Basic dGVzdF9za19LTmJkT3ZrNXJrNGFlRGUweXBFOG4wN3hsem1qOg==",
						        "Content-Type": "application/json",
						        "Idempotency-Key": idempotencyKey
						      };
 				//결제 승인 api
 				  console.log ('넘어온 데이터'+ orderRes.amout);
   				  if(<%=porder.getTotalPrice()%> === data.getAmount){ 
 					var plz = {
 					     "paymentKey": "data.paymentKey",
 					     "orderId": "<%=porder.getOrderId()%>",
 					     "amount": <%=porder.getTotalPrice()%>
 					      };
 				
 					  $.ajax({
 						type: "POST",
 				          url: "https://api.tosspayments.com/v1/payments/confirm",
 				          data: JSON.stringify(plz),
 				          headers: headers,
 				          success: function(response) {
 				            console.log(response); 
 				            alert("결제 완료");
                  			},
 				          error: function() {
 				            console.error("API 요청에 실패했습니다.");
 				            // 요청에 문제가 있음
 				          }
 					    }); 
 				  }else{
 					
 				}    
 			}); --%>
		});
	});
	</script>
</body>