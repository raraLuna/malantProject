<%@page import="member.controller.LoginMemberInfoServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, admin.model.vo.Inquiry" %>
<%
	ArrayList<Inquiry> list = (ArrayList<Inquiry>) request.getAttribute("list");

	int nowpage = 1;
	if(request.getAttribute("currentPage") != null){
		nowpage = ((Integer) request.getAttribute("currentPage")).intValue();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 문의 목록</title>
<style type="text/css">
fieldset#ss {
	width: 600px;
	position: relative;
	left: 450px;
}
form fieldset {
	width: 600px;	
}
form.sform {
	background: lightgray;
	width: 630px;
	position: relative;
	left: 400px;
	display: none;  /* 안 보이게 함 */
}
</style>
<script type="text/javascript" src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
$(function() {
	var listSize = '<%= list.size() %>';
	
	//input 태그의 name 이 item 의 값이 바뀌면(change) 작동되는 이벤트 핸들러 작성
	$('input[name=item]').on('change', function(){
		//여러 개의 태그 중에서 체크표시가 된 태그를 선택
		$('input[name=item]').each(function(index){
			//선택된 radio 순번대로 하나씩 checked 인지 확인함
			if($(this).is(':checked')){
				//체크 표시된 아이템에 대한 폼이 보여지게 처리함
				$('form.sform').eq(index).css('display', 'block');
			}else{
				//체크 표시 안된 아이템의 폼은 안 보이게 처리함
				$('form.sform').eq(index).css('display', 'none');
			}
		});  //each
	});  //on
});  //document ready

function showWriteForm(){
	location.href = "/malant/views/admin/adminWriteForm.jsp";
}
</script>
</head>
<body>
<%@ include file="../common/sidebar.jsp" %>
<hr>
<h1 align="center">내 문의 목록</h1>
<br>

<%-- 조회된 게시글 목록 출력 --%>
<table align="center" border="1" cellspacing="0" width="700">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>날짜</th>
		<th>처리 상태</th>
	</tr>
	<% for(Inquiry inquiry : list){ %>
		<tr>
			<td align="center"><%= inquiry.getInquiryNo() %></td>
			<td><a href="/malant/qdetail?qno=<%= inquiry.getInquirerNo() %>&page=<%= nowpage %>"><%= inquiry.getInquiryTitle() %></a></td>
			<td align="center"><%= inquiry.getInquiryDate() %></td>
			<td align="center"><%= inquiry.getStatus() %></td>
		</tr>
	<% } %>
</table>
<br>

<%-- 페이징 처리 뷰 포함 처리 --%>
<%@ include file="../common/pagingView.jsp" %>
</body>
</html>