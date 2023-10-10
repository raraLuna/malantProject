<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.*, diary.model.vo.Diary, java.sql.Date, diary.model.vo.MyDiaryPhotoes" %>   
<%
   ArrayList<Diary> list = (ArrayList<Diary>)request.getAttribute("list");   

   int nowpage = 1;
   if(request.getAttribute("currentPage") != null){
      nowpage = ((Integer)request.getAttribute("currentPage")).intValue();
   }
   
   HashMap<Integer, ArrayList<MyDiaryPhotoes>> photoes = (HashMap<Integer, ArrayList<MyDiaryPhotoes>>)request.getAttribute("photoes");
  
   
%>      
   
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>malant</title>

<link rel="stylesheet" href="/malant/resources/common/css/scrolling.css" />
<link rel="stylesheet" href="/malant/resources/common/css/font.css" />
<link rel="stylesheet" href="/malant/resources/diary/css/diary.css"> 


<script type="text/javascript"
   src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="/malant/resources/diary/js/diary.js"></script>
<script type="text/javascript">
window.onload = function(){
	//선택한 사진파일 이미지 미리보기 처리
	var photofiles = document.getElementById("multifile");
	photofiles.addEventListener('change', function(event){		
		const files = event.currentTarget.files;	   
	    const previewView = document.getElementById("photoesView");	  	    
	    
	    for(var i = 0; i < files.length; i++){
	    	let file = files[i];
		    const reader = new FileReader();
	        reader.onload = (e) => {   
	        	var img = document.createElement("img");
	        	img.setAttribute('src', e.target.result);
	        	img.setAttribute('data-file', file.name);
	        	img.setAttribute('width', 100);
	        	img.setAttribute('height', 100);
	        	
	        	previewView.appendChild(img);
	        	previewView.innerHTML += "&nbsp;";
	        };
	        reader.readAsDataURL(file);  
	    }  //이미지 미리보기 출력
	    
	    addHidden();  //파일명들 hidden 태그로 추가
	}); //change event
}	
	 function addHidden(){
		 const form = document.getElementById("newDiaryBoxForm");
		 const multiFile = document.getElementById("multifile");
		 const nameView = document.getElementById("filenameView");
		 
		 for(var i = 0; i < multiFile.files.length; i++){
			 console.log(multiFile.files[i].name);
			 var fname = multiFile.files[i].name;
			 nameView.innerHTML += "<span>" + fname + "</span><br>";
			 
			 var inputTag = document.createElement("input");
			 inputTag.setAttribute("type", "hidden");
			 inputTag.setAttribute("name", "filenames");
			 inputTag.setAttribute("value", fname);
			 
			 form.appendChild(inputTag);
		 }
		 
		console.log(form.elements.length);
	 }	
</script>

</head>

<body>

<div id="container">
        <%@include file = "../../views/common/sidebar.jsp" %>
        
   <div id="main">

        <div class="menu1" onclick="goDiary(); return false;">다이어리</div> 
        <div class="menu2" onclick="goMyplant(); return false;">반려식물</div>
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
     
          <div id="newDiaryGo" class="newDiaryGo"> 일기 쓰기</div> 
        
   		 <div id="newDiaryBox" class="newDiaryBox" >	
   			 <form action="/malant/dnew" id="newDiaryBoxForm" method="post" enctype="multipart/form-data">
					<input type="hidden" name="user_no" value="<%= loginMember.getUserNo() %>">
   			 	<table>
   			 	<tr>
   			 		<td>
   			 			<div class="title"> 오늘의 일기</div><br>
						<div class="addContentBox">
						&nbsp;&nbsp;<textarea name="diary_content" style="width:500px;height:100px;" placeholder="오늘은 어떤 일이 있었나요?"></textarea><br>
						&nbsp;&nbsp;<input type="file" name="multifile" id="multifile" multiple>
						&nbsp;&nbsp;<div id="filenameView"></div><br>
							<div id="photoesView" style="width:500px;height:220px;border:1px solid black;padding:10px;margin:10px;background:#616161">
							</div><br>
							<div style="text-align:center;">
								<input type="button" class="close-btn" value="닫기"> &nbsp;
								<input type="submit" id="save" class="save-close-btn" value="저장">
							</div>
						</div>	
					</td>
					</tr>
					</table>
				</form>
			</div> 
		
			
	<div class="diaryBody">
	<table class="diaryListTable">
	<tr>
	<td>
     <% for(Diary d : list) {%>
     		
			<div class="diaryBox">
			
	        <div class="diaryContent"><%= d.getDiaryContent() %></div><br>
	        <div class="diaryImg">

	        <%   Set<Integer> keyset = photoes.keySet();
	        	Iterator<Integer> keyiter = keyset.iterator();
	       	 while(keyiter.hasNext()) {
	        	int diary_id = (Integer)keyiter.next();
	        	
	        	if(diary_id == d.getDiaryId()){
	        	ArrayList<MyDiaryPhotoes> photoList = photoes.get(diary_id);
	        	
	        	for(MyDiaryPhotoes p : photoList){
	        	%>

	        <img src="/malant/resources/diary/diary_upimages/<%= p.getFileName() %>" style="clear:both;border-radius:5px;" width="150" height="150" > &nbsp;
	        
	        <%} } }%> 
	       </div> 
	        <br>
	        <div class="diaryDate" style="font-size:15px;"><%= d.getDiaryWritingDate() %></div><br>
	        <div>
	        <input type="submit" value="수정페이지로 이동" onclick="requestMoveUpdate(<%= d.getDiaryId() %>); return false;">
	        <input type="button" value="삭제" onclick="requestDelete(<%= d.getDiaryId() %>,'<%= d.getUserNo() %>'); return false;">
	        </div> 

       </div> <!-- diaryBox -->
       <br>
     <% } %>
			<div class="pagingview">
				<%@ include file="../diary/pagingView.jsp" %>
			</div>
       </td>
       </tr>
       </table>
       <br>
       <br>
       
	<% }else if(list.size() == 0) { %>

		
       <div id="newDiaryGo" class="newDiaryGo"> 일기 쓰기</div> 
      
        
        
   		 <div id="newDiaryBox" class="newDiaryBox" >	
   			 <form action="/malant/dnew" id="newDiaryBoxForm" method="post" enctype="multipart/form-data">
					<input type="hidden" name="user_no" value="<%= loginMember.getUserNo() %>">
   			 	<table>
   			 	<tr>
   			 		<td>
   			 			<div class="title"> 오늘의 일기</div><br>
						<div class="addContentBox">
						&nbsp;&nbsp;<textarea name="diary_content" style="width:300px;height:100px;" placeholder="오늘은 어떤 일이 있었나요?"></textarea><br>
						&nbsp;&nbsp;<input type="file" name="multifile" id="multifile" multiple>
						&nbsp;&nbsp;<div id="filenameView"></div><br>
							<div id="photoesView" style="width:500px;height:220px;border:1px solid black;padding:10px;margin:10px;background:#616161">
							</div><br>
							<div style="text-align:center;">
								<input type="button" class="close-btn" value="닫기"> &nbsp;
								<input type="submit" id="save" class="save-close-btn" value="저장">
							</div>
						</div>	
					</td>
					</tr>
					</table>
				</form>
			</div> 
		
 	 </div><!--diaryBody  -->
 	 	 
  		 <% } %>  
  		 
  	<script type="text/javascript">
    
	function requestMoveUpdate(diaryId){
		location.href="/malant/dmoveup?diaryId=" + diaryId + "&page=<%= nowpage %>";
	}
	function requestDelete(diaryId, userNo) {
		var ans = confirm('정말로 삭제 하시겠습니까?');
		
		if(ans) {
		location.href = "/malant/ddelete?diaryId=" + diaryId + "&userNo=" + userNo; 
	}	return false;
	}
	
	function goDiary (){
		location.href = "/malant/dlist?action=user_no&keyword=<%= loginMember.getUserNo() %>&page=1";
	}
	
	</script>
	</div> <!-- content body -->

   </div> <!-- main -->
   </div> <!-- container --> 
   
   
<script type="text/javascript" src="/malant/resources/diary/js/diary.js"></script>
</html>