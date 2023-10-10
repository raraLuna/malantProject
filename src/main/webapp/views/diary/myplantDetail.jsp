<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, myplant.model.vo.Myplant, java.sql.Date" %>   
<%
	Myplant myplant = (Myplant)request.getAttribute("myplant");
	
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>malant</title>
<link rel="stylesheet" href="/malant/resources/common/css/scrolling.css" >
<link rel="stylesheet" href="/malant/resources/common/css/font.css" >
<link rel="stylesheet" href="/malant/resources/diary/css/myplant.css">
<style type="text/css">

</style>
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="/malant/resources/diary/js/myplant.js"></script> 
<script type="text/javascript">
</script>
</head>
<body>
			<div id="container">
       			 <%@include file = "../../views/common/sidebar.jsp" %>
			<div id="main">

	
		<div class="menu1" onclick="goDiary(); return false;">다이어리</div> 
        <div class="menu2" onclick="goMyplant(); false;">반려식물</div>
		<script type="text/javascript">
		function goDiary (){
			location.href = "/malant/dlist?action=user_no&keyword=<%= loginMember.getUserNo() %>&page=1";
		}
		function goMyplant (){
			location.href = "/malant/mplist?action=user_no&keyword=<%= loginMember.getUserNo() %>&page=1";
		}
		</script>

	    
	<div id="contentbody">   
		
				<div id="myplantDetail" class="myplantDetail">

					<input type="hidden" name="MYPLANT_ID" value="<%= myplant.getMyplantId() %>">
					<input type="hidden" name="MYPLANT_IMAGE_URL" value="<%= myplant.getMyplantImageURL() %>">
					<input type="hidden" name="USER_NO" value="<%= loginMember.getUserNo() %>">
					<div class="nickname" style="font-size:15pt;font-weight:bold;"><%= myplant.getMyplantName() %>의 상세 정보</div><br>
					<div class="ID">ID : <%= myplant.getMyplantId() %></div>
					<div class="variety">품종: <% if(myplant.getMyplantVariety() != null) {%><%= myplant.getMyplantVariety() %><% }else { %><%} %></div><br>
					<div class="img" style="width:300px;height:300px;"> 
							<% if(myplant.getMyplantImageURL() != null) { %>
								<img class="image" src="/malant/resources/diary/myplant_upimages/<%= myplant.getMyplantImageURL() %>" style="width:300px;height:300px;">
							<% }else { %>
							    <img class="image" src="/malant/resources/diary/myplant_upimages/myplant_null_photo.png" style="width:300px;height:300px;">
							<% } %></div>
						<br>
					<div class="">키우기 시작한 날: <%= myplant.getMyplantStartDate() %></div>
					<div class="">메모: <% if(myplant.getMyplantMemo() != null) {%><%= myplant.getMyplantMemo() %><% }else { %><%} %></div>
					<br>
					<div class="button" >
						<input type="button" onclick="moveUpdate(); return false;" value="수정">
						<input type="button" onclick="requestDelete(); return false;" value="삭제">
						<button onclick="javascript:location.href='/malant/mplist?action=user_no&keyword=<%= loginMember.getUserNo() %>&page=<%= currentPage %>';">목록으로</button>
						<script type="text/javascript">
						function moveUpdate(){
							location.href="/malant/mpmovemodify?userNo=<%= loginMember.getUserNo() %>&myplantId=<%= myplant.getMyplantId() %>";
						}
						function requestDelete() {
							var ans = confirm('정말로 삭제 하시겠습니까?');
							
							if(ans) {
							location.href = "/malant/mpdelete?userNo=<%= loginMember.getUserNo() %>&myplantId=<%= myplant.getMyplantId() %>&MyplantImageURL=<%= myplant.getMyplantImageURL() %>";
							}
								return false;
							}
						</script>
					</div>
					
					</div> <!-- myplantDetail -->
				

	</div> <!-- content body  -->

	</div> <!-- main -->	
</div> <!-- container -->

<script type="text/javascript" src="/malant/resources/diary/js/myplant.js"></script> 

</body>
</html>