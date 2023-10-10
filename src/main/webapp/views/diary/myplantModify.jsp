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

	    
	<div id="contentbody">   

 					<form action="/malant/mpupdate" method="post" enctype="multipart/form-data">
					<div id="myplantModify" class="myplantModify"> 

					<input type="hidden" name="MYPLANT_ID" value="<%= myplant.getMyplantId() %>">
					<input type="hidden" name="USER_NO" value="<%= loginMember.getUserNo() %>">
					<input type="hidden" name="page" value="<%= currentPage %>"><br>
					<div>애칭* &nbsp; <input type="text" name="MYPLANT_NAME" value="<%= myplant.getMyplantName() %>"></div>
					<div>품종 &nbsp;&nbsp; <input type="text" name="MYPLANT_VARIETY" 
					value="<% if(myplant.getMyplantVariety() != null) {%><%= myplant.getMyplantVariety() %><% }else { %><%} %>"></div>
					<div>사진 &nbsp;&nbsp; <input type="file" id="MYPLANT_IMAGE_URL" name="MYPLANT_IMAGE_URL" value="/malant/resources/diary/">
							<div id="previewbox" style="width:280px;height:280px;border:1px slid black;padding:10px;margin:10px;">
								<%-- <% if(myplant.getMyplantImageURL() != null) {%>
								<img id="photo" src="/malant/resources/diary/myplant_upimages/<%= myplant.getMyplantImageURL() %>"
								width="260" height="260" align="center" style="position:relative;left:10px;top:10px;">
								<% }else { %> --%>
								<img id="photo" src="/malant/resources/diary/myplant_upimages/myplant_null_photo.png"
								width="260" height="260" align="center" style="position:relative;left:10px;top:10px;">
								<%-- <% } %> --%>
							</div>
							<br>
					<div>키우기 시작한 날 &nbsp; <input type="date" name="MYPLANT_START_DATE" value="<%= myplant.getMyplantStartDate()%>"> </div>
					<div>메모 &nbsp; <input type="text" name="MYPLANT_MEMO" 
					value="<% if(myplant.getMyplantMemo() != null) {%><%= myplant.getMyplantMemo() %><% }else { %><%} %>"></div>
					<br>
					<div>
						<input type="button" onclick="cancle(); return false;" class="close-btn" value="취소">
						<input type="button" onclick="requestDelete(); return false;" value="삭제">
						<input type="submit" id="save" class="save-close-btn" value="저장">
					</div>
					
					<script type="text/javascript">
					function cancle() {
						location.href = "/malant/mpinfo?userNo=<%= loginMember.getUserNo() %>&myplantId=<%= myplant.getMyplantId() %>&page=<%= currentPage %>";
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
					</div> <!-- myplantModify -->
					</form>  

	
	</div> <!-- content body  -->

	</div> <!-- main -->	
</div> <!-- container -->

<script type="text/javascript" src="/malant/resources/diary/js/myplant.js"></script> 

</body>
</html>