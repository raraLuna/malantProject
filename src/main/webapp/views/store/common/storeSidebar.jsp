<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="store.main.model.vo.MainContent, java.util.ArrayList,member.model.vo.Member"%>
<%
	ArrayList<MainContent> plistf = (ArrayList<MainContent>) request.getAttribute("plistf");
	ArrayList<MainContent> plist = (ArrayList<MainContent>) request.getAttribute("plist");
	
	Member loginMember = (Member) session.getAttribute("loginMember");
	String userNo = (loginMember != null) ? loginMember.getUserNo() : null;
	
	Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="/malant/resources/store/css/storeSidebar.css" />
<link rel="stylesheet" href="/malant/resources/common/css/font.css" />
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>

</head>
<body>
	<div class="sidebar">
		<div class="sidebar-header">
			<span class="sidebar-title"> <span class="back-to-main"><a
					href="/malant/index.jsp"><img
						src="/malant/resources/store/images/gotomain.gif"></a></span> <a
				href="/malant/smplist"> <img
					src="/malant/resources/store/images/store_tilte.gif" alt="스토어메인" /></a></span>
		</div>
		<section>
			<ul id="new_categories">
				<li><a href="javascript:location.href='/malant/plistf?option1=식물';">식&nbsp;&nbsp;&nbsp;물</a></li>
				<li><a href="javascript:location.href='/malant/plistf?option1=화분';">화&nbsp;&nbsp;&nbsp;분</a></li>
				<li><a href="javascript:location.href='/malant/plistf?option1=자갈/모래/흙';">자갈/모래/흙</a></li>
				<li><a href="javascript:location.href='/malant/plistf?option1=영양제/비료';">영양제/비료</a></li>
				<li><a href="javascript:location.href='/malant/plistf?option1=식물조명';">식물조명</a></li>
				<li><a href="javascript:location.href='/malant/plistf?option1=기타';">기&nbsp;&nbsp;&nbsp;타</a></li>
			</ul>
			 	</section>
		<!-- 로그인 영역 -->
		<% if (loginMember == null) { // 로그인하지 않았을 때 %>
			<div class="login-section" onclick="javascript: location.href='/malant/login?loc=common'">
				<img src="/malant/resources/common/images/loginButton.png" id = "login-button">
			</div>
		<% } else if(isAdmin != null && isAdmin) { // 관리자인 경우 %>
			<div class="login-section">
				<div class="login-section-top">
					<%= loginMember.getNickname() %>님
				</div>
				<div class="login-section-bottom">
					<a href="/malant/qlist">관리페이지</a> &nbsp;&nbsp; 
					<a href="/malant/logout?loc=store">로그아웃</a>
				</div>
			</div>
		<% } else { // 회원인 경우 %>
			<div class="login-section">
				<div class="login-section-top">
					<%= loginMember.getNickname() %>님
				</div>
				<% if(loginMember.getSellerYn().equals("Y")) {%>
		     <div class="login-links">
		     	<a href="javascript:location.href = '/malant/sellplist?sellerNo=<%= plist.get(0).getSellerNo() %>';" class="seller-button">판매관리</a>
            	<a href="/malant/logout?loc=store" class="logout-button">로그아웃</a>
            	</div>
            <% }else{ %>
            <div class="login-links">
            	<a href="/malant/sblist?userNo=<%= loginMember.getUserNo() %>" class="cart-button">장바구니</a>
                <a href="/malant/olist?userNo=<%= loginMember.getUserNo() %>" class="order-button">주문리스트</a>
                <a href="/malant/moveminfo?userid=<%= loginMember.getUserId() %>" class="my-page-button">마이 페이지</a>
                <a href="/malant/logout?loc=store" class="logout-button">로그아웃</a>
            </div>
            <%}} %>
		</div>
	</div>
</body>
</html>
