<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, myplant.model.vo.Myplant, java.sql.Date" %>   
<%
	ArrayList<Myplant> list = (ArrayList<Myplant>)request.getAttribute("list");	
	
	Myplant myplant = new Myplant();

	int nowpage = 1;
	if(request.getAttribute("currentPage") != null){
		nowpage = ((Integer)request.getAttribute("currentPage")).intValue();
	}
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
<script type="text/javascript">
window.onload = function() {
	var photofile = document.getElementById("MYPLANT_IMAGE_URL");
	photofile.addEventListener('change', function(event){
		const files = event.currentTarget.files;
		const file = files[0];
		const previewImg = document.getElementById("photo");
		console.log(file.name);
		
		const reader = new FileReader();
		reader.onload = (e) => {
			previewImg.setAttribute('src', e.target.result);
			previewImg.setAttribute('data-file', file.name);
		};	
		reader.readAsDataURL(file);
	});
	
}

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
	
	    
	<div id="contentbody" class="contentbody">   
	
	<% if (list.size() > 0 ) { %>
			
			<div id="addYourPlant" class="addYourPlant"> 반려식물을 등록해주세요!</div> 
			

			<div id="myplantAdd" class="myplantAdd"> 
			<form action="/malant/mpnew" method="post" enctype="multipart/form-data">
			<br>
					<div class="title">당신의 반려식물을 등록하세요</div>
					<br>
					<div class="addinfo">
					<input type="hidden" name="USER_NO" value="<%= loginMember.getUserNo() %>">
					애칭* &nbsp; <input type="text" name="MYPLANT_NAME"><br>
					품종 &nbsp;&nbsp; <input type="text" name="MYPLANT_VARIETY"><br>
					사진 &nbsp;&nbsp; <input type="file" name="MYPLANT_IMAGE_URL" id="MYPLANT_IMAGE_URL"><br>
					<div id="reviewbox" style="width:220px;height:220px;border:1px solid black;padding:10px;margin:10px;">
						<img id="photo" src="/malant/resources/diary/myplant_upimages/myplant_null_photo.png"
						width="200" height="200" align="center" style="position:relative;left:10px;top:10px;"><br>
					</div>
					메모 &nbsp; <input type="text" name="MYPLANT_MEMO"><br>
					키우기 시작한 날 &nbsp; <input type="date" id="theDate" name="MYPLANT_START_DATE"><br>
					<script type="text/javascript">
							var date = new Date();
		
							var day = date.getDate();
							var month = date.getMonth() + 1;
							var year = date.getFullYear();
		
							if (month < 10) month = "0" + month;
							if (day < 10) day = "0" + day;
		
							var today = year + "-" + month + "-" + day;       
							document.getElementById("theDate").value = today;
					</script>
					</div>
					<br><br>
					<div class="button">
						<input type="button" class="close-btn" value="닫기">
						<input type="submit" id="save" class="save-close-btn" value="저장">
					</div>
					</form>
					</div><!-- myplantAdd -->
	
					
					

	<div id="myplantListbody" class="myplantListbody" style="margin-top:40px;">		
	<% for (Myplant mp : list) { %>
		 
				<div id="myplantbox" class="myplantbox" onclick="moveDetail(<%= mp.getMyplantId() %>); return false;"> 
						<div class="nickname" style="height:20px;font-weight:bold;padding-top:3px;"> <%= mp.getMyplantName() %></div>
						<div>
							<% if(mp.getMyplantImageURL() != null) { %>
								<img class="image" src="/malant/resources/diary/myplant_upimages/<%= mp.getMyplantImageURL() %>">
							<% }else { %>
							    <img class="image" src="/malant/resources/diary/myplant_upimages/myplant_null_photo.png">
							<% } %>
						</div>
						<div class="information">
							<div class="ID">ID : <%= mp.getMyplantId() %></div>
							<div class="variety">품종 : 
							<% if(mp.getMyplantVariety() != null) {%><%= mp.getMyplantVariety() %><% }else { %><%} %>
							</div>
							<div class="">키우기 시작한 날: <%= mp.getMyplantStartDate() %></div>
							<div class="memo">메모 : 
							<% if(mp.getMyplantVariety() != null) {%><%= mp.getMyplantMemo() %><% }else { %><%} %>
							</div>
						</div>
					<script type="text/javascript">	

						function moveDetail(myplantId){
						 location.href="/malant/mpinfo?userNo=<%= loginMember.getUserNo() %>&myplantId=" + myplantId + "&page=<%= nowpage %>";
						}

					</script>	
					</div><!-- myplantbox -->
					
			<% } %> <!-- for문 -->	
			
			
			<div class="pagingview">
				<%@ include file="../diary/pagingView.jsp" %>
			</div>
		</div> <!-- myplantListbody -->
		
		
		<% }else if(list.size() == 0) { %>
		<div id="addYourPlant" class="addYourPlant"> 반려식물을 등록해주세요!</div> 
		
			<div id="myplantAdd" class="myplantAdd"> 
			<form action="/malant/mpnew" method="post" enctype="multipart/form-data">
			<br>
					<div class="title">당신의 반려식물을 등록하세요</div>
					<br>
					<div class="addinfo">
					<input type="hidden" name="USER_NO" value="<%= loginMember.getUserNo() %>">
					애칭* &nbsp; <input type="text" name="MYPLANT_NAME"><br>
					품종 &nbsp;&nbsp; <input type="text" name="MYPLANT_VARIETY"><br>
					사진 &nbsp;&nbsp; <input type="file" name="MYPLANT_IMAGE_URL" id="MYPLANT_IMAGE_URL"><br>
					<div id="reviewbox" style="width:220px;height:220px;border:1px solid black;padding:10px;margin:10px;">
						<img id="photo" src="/malant/resources/diary/myplant_upimages/myplant_null_photo.png"
						width="200" height="200" align="center" style="position:relative;left:10px;top:10px;"><br>
					</div>
					메모 &nbsp; <input type="text" name="MYPLANT_MEMO"><br>
					키우기 시작한 날 &nbsp; <input type="date" id="theDate" name="MYPLANT_START_DATE"><br>
					<script type="text/javascript">
							var date = new Date();
		
							var day = date.getDate();
							var month = date.getMonth() + 1;
							var year = date.getFullYear();
		
							if (month < 10) month = "0" + month;
							if (day < 10) day = "0" + day;
		
							var today = year + "-" + month + "-" + day;       
							document.getElementById("theDate").value = today;
					</script>
					</div>
					<br><br>
					<div class="button">
						<input type="button" class="close-btn" value="닫기">
						<input type="submit" id="save" class="save-close-btn" value="저장">
					</div>
					</form>
					</div> <!-- myplantAdd -->
		<% } %>
		
	</div> <!-- content body  -->

	</div> <!-- main -->	
</div> <!-- container -->
<script type="text/javascript" src="/malant/resources/diary/js/myplant.js"></script>

</body>
</html>