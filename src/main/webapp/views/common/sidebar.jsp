<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member" %>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
	if(isAdmin == null) {
		isAdmin = false;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="/malant/resources/common/css/sidebar.css" />
<link rel="stylesheet" href="/malant/resources/common/css/font.css" />
</head>
<body>
	<div class="sidebar">
		<div class="sidebar-header">
			<span class="sidebar-title"><a href="/malant">
				<img src="/malant/resources/common/images/main_tilte.gif" alt="식물 검색" />
			</a></span>
		</div>
		<section>
			<ul id="new_categories">
				<li><a href="/malant">식물 검색</a></li>
				<li><a href="/malant/bdlist">커뮤니티</a></li>
				<li><a 
				<% if(loginMember == null) { %>
					href="/malant/views/member/loginPage.jsp"
				<% } else { %>
					href="/malant/dlist?action=user_no&keyword=<%= loginMember.getUserNo() %>&page=1"
				<% } %>>다이어리</a></li>
				<li><a href="/malant/arbriefinfo">전국 식물원</a></li>
				<li><a href="/malant/smplist">스토어</a></li>
				<li><a href="/malant/ntitlelist">공지/이벤트</a></li>
			</ul>
		</section>
		<!-- 로그인 영역 -->
		<% if (loginMember == null) { // 로그인하지 않았을 때 %>
			<div class="login-section" onclick="javascript: location.href='/malant/login?loc=common'">
				<img src="/malant/resources/common/images/loginButton.png" id = "login-button">
			</div>
		<% } else if(isAdmin) { // 관리자인 경우 %>
			<div class="login-section">
				<div class="login-section-top">
					<%= loginMember.getNickname() %>님
				</div>
				<div class="login-section-bottom">
					<a href="/malant/qlist">관리페이지</a> &nbsp;&nbsp; 
					<a href="/malant/logout?loc=common">로그아웃</a>
				</div>
			</div>
		<% } else { // 회원인 경우 %>
			<div class="login-section">
				<div class="login-section-top">
					<%= loginMember.getNickname() %>님
				</div>
				<div class="login-links">
					<a href="/malant/moveminfo?userid=<%= loginMember.getUserId() %>" class="my-page-button">마이페이지</a> &nbsp;&nbsp;
					<a href="/malant/myblist?userno=<%= loginMember.getUserNo() %>" class="my-board-button">내 게시글 보기</a> &nbsp;&nbsp;  
					<a href="/malant/logout?loc=common" class="logout-button">로그아웃</a>
				</div>
			</div>
		<% } %>
	</div>
</body>
</html>
