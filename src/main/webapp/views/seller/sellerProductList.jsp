<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="store.product.model.vo.ProductDetail, java.util.ArrayList, member.model.vo.Member"%>
<%
	Member loginMember = (Member)request.getAttribute("loginMember");
	ArrayList<ProductDetail> sellplist = (ArrayList<ProductDetail>)request.getAttribute("sellplist"); 
	session.setAttribute("sellplist", sellplist);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="/malant/resources/common/css/font.css" />
<link rel="stylesheet" href="/malant/resources/store/css/seller.css">
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
</head>
<body>

	<a class="add-button" onclick="javascript: location.href='/malant/views/seller/UpdateProduct.jsp'">상품 등록</a>
	<h1>상품 목록</h1>
	<table border="1">
		<thead>
			<tr>
				<th>대표사진</th>
				<th>상품명</th>
				<th>가격</th>
				<th>등록일</th>
				<th>수정/삭제</th>
			</tr>
		</thead>
		<tbody>
			<% if (sellplist != null && !sellplist.isEmpty()) { %>
			<% for (ProductDetail product : sellplist) { %>
			<tr>
				<td><img src="<%= product.getThumbnailImg() %>" width="100" height="100"></td>
				<td><%= product.getProductName() %></td>
				<td><%= product.getPrice() %></td>
				<td><%= product.getProductRegistDate()%></td>
				<td>
				<form action="/malant/sellpdelete" method="post">
				<input type="hidden" name="pid" value="<%= product.getProductId() %>">
				<input type="hidden" name="sellerNo" value="<%= product.getSellerNo() %>">
				<input type="hidden" name="Thumnail" value="<%= product.getThumbnailImg() %>">
				<input type="hidden" name="productId" value="<%= product.getProductId() %>">
				<input type="hidden" name="detailimg" value="<%= product.getDetailImage() %>">				
				<input type="submit" value="삭제">
				</form>
				</td>
			</tr>
			<% } %>
			<% } else { %>
			<tr>
				<td colspan="6">조회된 상품이 없습니다.</td>
			</tr>
			<% } %>

		</tbody>
	</table>
</body>
</html>
