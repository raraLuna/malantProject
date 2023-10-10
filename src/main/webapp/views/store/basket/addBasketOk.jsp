<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="store.order.model.vo.ProductOrder, java.util.ArrayList"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="/malant/resources/store/css/orderInputForm.css" />
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>

</head>
<body>
	<div class="container">
		<!-- 사이드바 -->
		<%@ include file="../common/storeSidebar.jsp"%>
		<div class="content">
		<h1>장바구니 담기 완료</h1>
		<button style="width:100px; height:50px;" onclick="javascript:history.go(-1);">이전페이지</button>
		<button style="width:100px; height:50px;" onclick="window.location.href = '/malant/sblist?userNo=<%= loginMember.getUserNo() %>'">장바구니</button>
		</div>
	</div>
</body>
</html>
