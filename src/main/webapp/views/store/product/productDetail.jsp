<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="store.product.model.vo.ProductDetail, java.util.ArrayList"%>
<%
	ArrayList<ProductDetail> pdetail = (ArrayList<ProductDetail>) request.getAttribute("list");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="/malant/resources/store/css/productDetail.css" />
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div class="container">
		<!-- 사이드바 -->
		<%@ include file="../common/storeSidebar.jsp"%>

		<div class="content">
			<class="product-info">
			<div id="pdetail-name"><%=pdetail.get(0).getProductName()%></div>
			<table id="pdetail-seller-price" cellspacing="5" cellpadding="0"
				border="1px">
				<tr>
					<th>판매처</th>
					<td><%=pdetail.get(0).getSellerName()%></td>
				</tr>
				<tr>
					<th>가 격</th>
					<td><%=pdetail.get(0).getPrice()%></td>
				</tr>
				<tr>
					<th>배송비</th>
					<td><%=pdetail.get(0).getDeliveryCharge()%></td>
				</tr>
			</table>
			
			<%if(userNo != null) {%>
			<form class="oinputform" action="/malant/sbadd" method="post" id="goorder">
				<input type="hidden" name="productid"
					value="<%=pdetail.get(0).getProductId()%>">
				<input type="hidden" name="userno" value="<%=userNo%>">
				<%}else{ %>
			<form class="login-request" action="/malant/login" method="post" id="goorder">
				<%} %>
				<div id="options">
					<label for="quantity" style=" font-size: 20px; margin-right: 15px;">수량 선택</label>
					<select id="quantity" name="quantity">
					<%
        				for (int i = 1; i <= 99; i++) {
        			%>
					<option value="<%=i%>"><%=i%></option>
					<%
       				 }
        			%>
				</select>
				<button class="orderButton" type="submit" form="goorder">구매하기</button>
				</div>
			</form>
		</div>
		
		<div class="product-images">
			<div id="pdetail-thumnail">
				<div id="thumbnail">
				<img src="<%=pdetail.get(0).getThumbnailImg()%>">
				</div>
				<div class="pdetail-detailimg">
				<img src="<%=pdetail.get(0).getProductDetailImg()%>" width="100%" align="right">
				<%
				if (pdetail.get(0).getProductDescription() != null) {
				%>
				<%=pdetail.get(0).getProductDescription()%>
				<%
				}
				%>
			</div>
		</div>
	</div>
</div>
</body>
</html>
