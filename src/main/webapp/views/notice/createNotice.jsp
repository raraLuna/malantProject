<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

.createform{
display: flex;
justify-content: center;
border : 1px red solid;
}

</style>
</head>

<body>
	<div class="container">
		<%@ include file="../../views/common/sidebar.jsp"%>
	</div>
	<div class="createform">
		<form action="/malant/ninsert" method="post"
			enctype="multipart/form-data">
			<%
			if (loginMember != null && isAdmin) {
			%>
			
				<img name="preview-image" id="preview-image" src="/malant/resources/notice/notice_content_img/p.png"  style="width: 350px;"> <br>
				<input type="file" id="input-image" name="input-image" onchange="checkImageSelection(this);"> 
				<br> 
				<input type="hidden" name="adminno" value="<%= loginMember.getUserNo() %>" readonly>
				<br>
				 타입 <br> <br> 
				 <input type="radio" name="noticetype" value="NOTICE" checked>  NOTICE
				  <input type="radio" name="noticetype" value="EVENT">  EVENT 
				  <input type="radio" name="noticetype" value="BANNER">  BANNER <br>
					<br>
					제목 : <input type="text" name="noticeTitle"><br> <br>
					내용 : <input type="text" name="noticeContent"><br> <br>
					이벤트 시작일 :<br> <input type="date" name="startDate"><br> <br> 
					이벤트 종료일 :<br> <input type="date" name="endDate"><br>
					<input type="submit" value="쓰기"> 
					<br> <br> <br> <br>
			<%
			}
			%>

		</form>
	</div>
</body>
</html>