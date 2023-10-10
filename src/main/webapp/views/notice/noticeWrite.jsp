<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="notice.model.vo.Notice, java.util.ArrayList"%>
<%
	ArrayList<Notice> nlist = (ArrayList<Notice>) request.getAttribute("nlist");
	Notice notice = (Notice) request.getAttribute("nno");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function readImage(input) {
    if (input.files && input.files[0]) {
       const reader = new FileReader();
       reader.onload = function(e) {
          const previewImage = document.getElementById("preview-image");
          previewImage.src = e.target.result;
       }
       reader.readAsDataURL(input.files[0]);
    }
 }

 const inputImage = document.getElementById("input-image");
 inputImage.addEventListener("change", function() {
    readImage(this);
 });

 function checkImageSelection(inputElement) {
     var previewImage = document.getElementById("preview-image");

     if (inputElement.files.length === 0) {
         alert("파일을 선택하세요."); 
         previewImage.src = "/malant/resources/board/images/8.png"; 
     } else {

         var reader = new FileReader();
         reader.onload = function(e) {
             previewImage.src = e.target.result;
         };
         reader.readAsDataURL(inputElement.files[0]);
     }
 }
 </script>
</head>
<body>
<div class="notice-main">
		<div class="container">
			<%@ include file="../../views/common/sidebar.jsp"%>
		</div>
		<div id ="contentbady">
			<header>
				<div id="newNotice" class="newNotice">공지사항 등록하기</div>
			</header>
			<div id="newNoticeBox" class="newNoticeBox">
				<form action="/malant/ninsert" id="newNoticeBoxForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="userno" value="<%= loginMember.getUserNo() %>">
				<div>
					<input type="radio" name="noticetype" value="BANNER"> BANNER
					<input type="radio" name="noticetype" value="NOTICE"> NOTICE
					<input type="radio" name="noticetype" value="EVENT"> EVENT
					<br><br>
					제목 :  <input type="text" name="title" readonly><br><br>
					내용 :  <input type="text" name="content"><br><br>
					
					이벤트 시작일 :<br>
					<input type="date" name="eventstart"><br><br>
					
					이벤트 종료일 :<br>
					<input type="date" name="eventend"><br>
					<input type="file" name="multifile" id="multifile" multiple>
					<div id="filenameView"></div>
					<div id="photoesView"></div>
					<input type="submit" value="쓰기">
				</div>
					<input type="reset" class="close-btn" value="취소"> &nbsp;
					<input type="submit" id="save" class="save-close-btn" value="저장">
				</form>
			</div>
		</div>
	</div>
</body>
</html>