<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.Paging, member.model.vo.Member" %>    
<%
	Paging paging = (Paging)request.getAttribute("paging");
	
	int startPage = paging.getStartPage();
	int endPage = paging.getEndPage();
	int maxPage = paging.getMaxPage();
	int currentPage = paging.getCurrentPage();
	int limit = paging.getLimit();
	
	String urlMapping = paging.getUrlMapping();
	
	String action = (String) request.getAttribute("action");
	String keyword = null, begin = null, end = null;
	String diff = null, rate = null, smll = null, place = null, puri = null;
	String userno = null;
	
	if(action != null){
		if(action.equals("date")) {
			begin = (String)request.getAttribute("begin");
			end = (String)request.getAttribute("end");
		} else if(action.equals("myqlist")) {
			userno = (String) request.getAttribute("userno");
		} else if(action.equals("plsearch")) {
			keyword = (String)request.getAttribute("keyword");
			diff = (String) request.getAttribute("difficulty");
			rate = (String) request.getAttribute("growth_rate");
			smll = (String) request.getAttribute("smell");
			place = (String) request.getAttribute("placement");
			puri = (String) request.getAttribute("effect_purification");
		}
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%-- 목록 페이징 처리 --%>
<% if(action == null){ %>
<div style="text-align:center;">
	<% if(currentPage <= 1){ %>
		[맨처음] &nbsp;
	<% }else{ //currentPage > 1 %>
		<a href="/malant/<%= urlMapping %>?page=1">[맨처음]</a> &nbsp;
	<% } %>
	<%-- 이전 페이지 그룹으로 이동 --%>
	<% if((currentPage - 10) < startPage && (currentPage - 10) > 1){  //이전그룹이 있다면 %>
		<a href="/malant/<%= urlMapping %>?page=<%= startPage - 10 %>">[이전그룹]</a> &nbsp;
	<% }else{ //이전그룹이 없다면 %>
		[이전그룹] &nbsp;
	<% } %>
	
	<%-- 현재 페이지가 속한 페이지그룹 숫자 출력 --%>
	<% for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage){
	%>
			<font color="blue" size="4"><b><%= p %></b></font>
	<%     }else{ %>
			<a href="/malant/<%= urlMapping %>?page=<%= p %>"><%= p %></a>
	<% }} %>
	
	<%-- 다음 페이지 그룹으로 이동 --%>
	<% if((currentPage + 10) > endPage && (currentPage + 10) < maxPage){  //다음그룹이 있다면 %>
		<a href="/malant/<%= urlMapping %>?page=<%= startPage + 10 %>">[다음그룹]</a> &nbsp;
	<% }else{ //다음그룹이 없다면 %>
		[다음그룹] &nbsp;
	<% } %>
	
	<% if(currentPage >= maxPage){ %>
		[맨끝] &nbsp;
	<% }else{ //currentPage < maxPage  %>
		<a href="/malant/<%= urlMapping %>?page=<%= maxPage %>">[맨끝]</a> &nbsp;
	<% } %>
</div>
<% } %>

<%-- 검색(제목, 작성자, 내용) 페이징 처리 --%>
<% if(action != null && keyword != null && !action.equals("plsearch")){ %>
<div style="text-align:center;">
	<% if(currentPage <= 1){ %>
		[맨처음] &nbsp;
	<% }else{ //currentPage > 1 %>
		<a href="/malant/<%= urlMapping %>?page=1&action=<%= action %>&keyword=<%= keyword %>">[맨처음]</a> &nbsp;
	<% } %>
	<%-- 이전 페이지 그룹으로 이동 --%>
	<% if((currentPage - 10) < startPage && (currentPage - 10) > 1){  //이전그룹이 있다면 %>
		<a href="/malant/<%= urlMapping %>?page=<%= startPage - 10 %>&action=<%= action %>&keyword=<%= keyword %>">[이전그룹]</a> &nbsp;
	<% }else{ //이전그룹이 없다면 %>
		[이전그룹] &nbsp;
	<% } %>
	
	<%-- 현재 페이지가 속한 페이지그룹 숫자 출력 --%>
	<% for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage){
	%>
			<font color="blue" size="4"><b><%= p %></b></font>
	<%     }else{ %>
			<a href="/malant/<%= urlMapping %>?page=<%= p %>&action=<%= action %>&keyword=<%= keyword %>"><%= p %></a>
	<% }} %>
	
	<%-- 다음 페이지 그룹으로 이동 --%>
	<% if((currentPage + 10) > endPage && (currentPage + 10) < maxPage){  //다음그룹이 있다면 %>
		<a href="/malant/<%= urlMapping %>?page=<%= startPage + 10 %>&action=<%= action %>&keyword=<%= keyword %>">[다음그룹]</a> &nbsp;
	<% }else{ //다음그룹이 없다면 %>
		[다음그룹] &nbsp;
	<% } %>
	
	<% if(currentPage >= maxPage){ %>
		[맨끝] &nbsp;
	<% }else{ //currentPage < maxPage  %>
		<a href="/malant/<%= urlMapping %>?page=<%= maxPage %>&action=<%= action %>&keyword=<%= keyword %>">[맨끝]</a> &nbsp;
	<% } %>
</div>
<% } %>

<%-- 검색(등록날짜) 페이징 처리 --%>
<% if(action != null && action.equals("date")){ %>
<div style="text-align:center;">
	<% if(currentPage <= 1){ %>
		[맨처음] &nbsp;
	<% }else{ //currentPage > 1 %>
		<a href="/malant/<%= urlMapping %>?page=1&action=<%= action %>&begin=<%= begin %>&end=<%= end %>">[맨처음]</a> &nbsp;
	<% } %>
	<%-- 이전 페이지 그룹으로 이동 --%>
	<% if((currentPage - 10) < startPage && (currentPage - 10) > 1){  //이전그룹이 있다면 %>
		<a href="/malant/<%= urlMapping %>?page=<%= startPage - 10 %>&action=<%= action %>&begin=<%= begin %>&end=<%= end %>">[이전그룹]</a> &nbsp;
	<% }else{ //이전그룹이 없다면 %>
		[이전그룹] &nbsp;
	<% } %>
	
	<%-- 현재 페이지가 속한 페이지그룹 숫자 출력 --%>
	<% for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage){
	%>
			<font color="blue" size="4"><b><%= p %></b></font>
	<%     }else{ %>
			<a href="/malant/<%= urlMapping %>?page=<%= p %>&action=<%= action %>&begin=<%= begin %>&end=<%= end %>"><%= p %></a>
	<% }} %>
	
	<%-- 다음 페이지 그룹으로 이동 --%>
	<% if((currentPage + 10) > endPage && (currentPage + 10) < maxPage){  //다음그룹이 있다면 %>
		<a href="/malant/<%= urlMapping %>?page=<%= startPage + 10 %>">[다음그룹]</a> &nbsp;
	<% }else{ //다음그룹이 없다면 %>
		[다음그룹] &nbsp;
	<% } %>
	
	<% if(currentPage >= maxPage){ %>
		[맨끝] &nbsp;
	<% }else{ //currentPage < maxPage  %>
		<a href="/malant/<%= urlMapping %>?page=<%= maxPage %>&action=<%= action %>&begin=<%= begin %>&end=<%= end %>">[맨끝]</a> &nbsp;
	<% } %>
</div>
<% } %>

<%-- 식물 검색 페이징 처리 --%>
<% if(action != null && action.equals("plsearch")){ %>
<div style="text-align:center;">
	<% if(currentPage <= 1){ %>
		[맨처음] &nbsp;
	<% }else{ //currentPage > 1 %>
		<a href="/malant/<%= urlMapping %>?page=1&action=<%= action %>&keyword=<%= keyword %>&difficulty=<%= diff %>&growth_rate=<%= rate %>&smell=<%= smll %>&placement=<%= place %>&effect_purification=<%= puri %>">[맨처음]</a> &nbsp;
	<% } %>
	<%-- 이전 페이지 그룹으로 이동 --%>
	<% if((currentPage - 10) < startPage && (currentPage - 10) > 1){  //이전그룹이 있다면 %>
		<a href="/malant/<%= urlMapping %>?page=<%= startPage - 10 %>&action=<%= action %>&keyword=<%= keyword %>&difficulty=<%= diff %>&growth_rate=<%= rate %>&smell=<%= smll %>&placement=<%= place %>&effect_purification=<%= puri %>">[이전그룹]</a> &nbsp;
	<% }else{ //이전그룹이 없다면 %>
		[이전그룹] &nbsp;
	<% } %>
	
	<%-- 현재 페이지가 속한 페이지그룹 숫자 출력 --%>
	<% for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage){
	%>
			<font color="blue" size="4"><b><%= p %></b></font>
	<%     }else{ %>
			<a href="/malant/<%= urlMapping %>?page=<%= p %>&action=<%= action %>&keyword=<%= keyword %>&difficulty=<%= diff %>&growth_rate=<%= rate %>&smell=<%= smll %>&placement=<%= place %>&effect_purification=<%= puri %>"><%= p %></a>
	<% }} %>
	
	<%-- 다음 페이지 그룹으로 이동 --%>
	<% if((currentPage + 10) > endPage && (currentPage + 10) < maxPage){  //다음그룹이 있다면 %>
		<a href="/malant/<%= urlMapping %>?page=<%= startPage + 10 %>&action=<%= action %>&keyword=<%= keyword %>&difficulty=<%= diff %>&growth_rate=<%= rate %>&smell=<%= smll %>&placement=<%= place %>&effect_purification=<%= puri %>">[다음그룹]</a> &nbsp;
	<% }else{ //다음그룹이 없다면 %>
		[다음그룹] &nbsp;
	<% } %>
	
	<% if(currentPage >= maxPage){ %>
		[맨끝] &nbsp;
	<% }else{ //currentPage < maxPage  %>
		<a href="/malant/<%= urlMapping %>?page=<%= maxPage %>&action=<%= action %>&keyword=<%= keyword %>&difficulty=<%= diff %>&growth_rate=<%= rate %>&smell=<%= smll %>&placement=<%= place %>&effect_purification=<%= puri %>">[맨끝]</a> &nbsp;
	<% } %>
</div>
<% } %>

<%-- 문의사항 페이징 처리 --%>
<% if(action != null && action.equals("myqlist")){ %>
<div style="text-align:center;">
	<% if(currentPage <= 1){ %>
		[맨처음] &nbsp;
	<% }else{ //currentPage > 1 %>
		<a href="/malant/<%= urlMapping %>?page=1&action=<%= action %>&userno=<%= userno %>">[맨처음]</a> &nbsp;
	<% } %>
	<%-- 이전 페이지 그룹으로 이동 --%>
	<% if((currentPage - 10) < startPage && (currentPage - 10) > 1){  //이전그룹이 있다면 %>
		<a href="/malant/<%= urlMapping %>?page=<%= startPage - 10 %>&action=<%= action %>&userno=<%= userno %>">[이전그룹]</a> &nbsp;
	<% }else{ //이전그룹이 없다면 %>
		[이전그룹] &nbsp;
	<% } %>
	
	<%-- 현재 페이지가 속한 페이지그룹 숫자 출력 --%>
	<% for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage){
	%>
			<font color="blue" size="4"><b><%= p %></b></font>
	<%     }else{ %>
			<a href="/malant/<%= urlMapping %>?page=<%= p %>&action=<%= action %>&userno=<%= userno %>"><%= p %></a>
	<% }} %>
	
	<%-- 다음 페이지 그룹으로 이동 --%>
	<% if((currentPage + 10) > endPage && (currentPage + 10) < maxPage){  //다음그룹이 있다면 %>
		<a href="/malant/<%= urlMapping %>?page=<%= startPage + 10 %>&action=<%= action %>&userno=<%= userno %>">[다음그룹]</a> &nbsp;
	<% }else{ //다음그룹이 없다면 %>
		[다음그룹] &nbsp;
	<% } %>
	
	<% if(currentPage >= maxPage){ %>
		[맨끝] &nbsp;
	<% }else{ //currentPage < maxPage  %>
		<a href="/malant/<%= urlMapping %>?page=<%= maxPage %>&action=<%= action %>&userno=<%= userno %>">[맨끝]</a> &nbsp;
	<% } %>
</div>
<% } %>

</body>
</html>