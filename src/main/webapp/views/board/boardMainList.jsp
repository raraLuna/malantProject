<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="community.model.vo.Board,java.util.ArrayList,community.model.vo.Comment"%>
<!DOCTYPE html>
<%
	ArrayList<Board> list = (ArrayList<Board>) request.getAttribute("list");
	String userno = (String)request.getAttribute("userno");
%>
<!-- 커뮤니티 내 게시글 목록 -->
<html>
<head>
<meta charset="UTF-8">
<title>MalantBoardMain</title>
<style type="text/css">

div.board-main{
	display: flex;
	align-items: center;
	flex-direction: column;
}



</style>
<script type="text/javascript"
	src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="/malant/resources/board/css/boardcontents.css">
<script>
function requestDelete(){
	//게시글(원글, 댓글, 대댓글) 삭제 요청 함수
	location.href = "/malant/bdelete";
}

$(function() {
	
	likeTop3();
	hashTop5();
   
});

function likeCount (boardNo){  
    $.ajax({
         url: "/malant/bbtn",
         type: "get",
         dataType: "text",
         data : {
            'boardNo': boardNo
         },
         success : function(data){
             $("#blike-" + boardNo).text(data);
         }
    });
    
    likeTop3();
}


function likeTop3(){
	 $.ajax({
	        url: "/malant/btoplike",
	        type: "get",
	        dataType: "json",
	        success: function(data) {
	            var str = JSON.stringify(data);
	            var json = JSON.parse(str);
	            //console.log(json);
	            values = ""; 
	            for (var i in json.blist) {
	                values += "<tr><td align='center'>" +  json.blist[i].bno +"</td><td>";
	                    
	                if(json.blist[i].bthum == null) {
	                    	values += "<img src='/malant/resources/board/images/baseSample.jpeg' width='20' hegiht='20'>"
	                }else{
	                    	values += "<img src='/malant/resources/board/images/" + json.blist[i].bthum + "' width='20' hegiht='20'>"
	                } 
	          
	                values += "</td><td><a onclick='checkLogin(" + json.blist[i].bno + ");'>" + decodeURIComponent(json.blist[i].btitle) + "</a></td><td align='center'>" 
		                    + decodeURIComponent(json.blist[i].bnick) + "</td><td align='center'>" 
		                    + json.blist[i].blike + "</td><td align='center'>"
		                    + json.blist[i].bcount + "</td><td align='center'>"
		                    + json.blist[i].bdate + "</td></tr>";
	                 
	            }
	            
	            $('#top3').html($('#top3').html() + values);
	        }
	    });
}

	function hashTop5(){
	 $.ajax({
	        url : "/malant/btophash",
	        type: "get",
	        dataType: "json",
	        success: function(data) {
	            var str = JSON.stringify(data);
	            var json = JSON.parse(str);
	            console.log(json);
	            for (var i = 0; i < json.hlist.length; i++){
	               $('#hash' + (i + 1)).text(decodeURIComponent(json.hlist[i].hashContent));

	            }
	        }
	    });
}  
function viewInput(span){
	$("#keyword").val(span.innerHTML);
}

</script>
</head>
<body>
		
<div class="container">

<%@ include file="../../views/common/sidebar.jsp"%>

 <script type="text/javascript">
        function toggleLike(boardNo) {
            $.ajax({
                url: "/malant/bbtn",
                type: "post",
                dataType: "text",
                data: {
                    'boardNo': boardNo
                },
                success: function(data) {
                    if (data === "liked") {
                        $("#likeCount_" + boardNo).text(parseInt($("#likeCount_" + boardNo).text()) + 1);
                        $("#likeBtn_" + boardNo).text("좋아요 취소");
                    } else {
                        // 사용자가 좋아요를 취소한 경우
                        $("#likeCount_" + boardNo).text(parseInt($("#likeCount_" + boardNo).text()) - 1);
                        $("#likeBtn_" + boardNo).text("좋아요");
                    }
                }
            });
        }
        
        function checkLogin(boardNo){
        	var member = '<%=loginMember%>';
        	console.log("member : " + member);
            if (member == null){
                if (confirm("로그인 하시겠습니까?")) {
                   location.href = "/malant/login?loc=common";
                } 
            } else {
                location.href="/malant/bdetail?bno=" + boardNo;
            }
        }
    
        </script>
        
        <div class="board-main">
		<div id="toplist" class="board">
			<h2>인기 게시글</h2>
			<table  id="top3" width="700" style="border-radius : 10px; border : 1px solid  rgba(154, 179, 213, 0.2);">
				<tr style="background: rgba(164, 194, 109, 0.5);">
					<th>번호</th>
					<th>&nbsp;</th>
					<th>제목</th>
					<th>작성자</th>
					<th>좋아요</th>
					<th>조회수</th>
					<th>등록날짜</th>
				</tr>
			</table>
			
		</div>
		<hr>
		<div id="hashlist" class="board">
		<%-- 제목 검색 폼 --%>
		<table  width="700" style="border-radius : 10px; border : 1px solid  rgba(154, 179, 213, 0.2);">
			<form id="titleform" class="sform" action="/malant/searchtag" method="post">
				<input type="hidden" name="action" value="searchtag">	
				<fieldset style="background: rgba(154, 179, 213, 0.3);">
				<legend>검색할 해시태그를 입력하세요.</legend>
				<input type="search" name="keyword" size="50" id="keyword"> &nbsp;
				<input type="submit" value="검색">
				<br>
				<b><span style="font-size:8pt;">가장 많이 사용된 해시태그</span></b> &nbsp; &nbsp; 
				<b><span onclick="viewInput(this);" id="hash1" style="font-size:8pt; color:blue;">#</span></b> &nbsp;
				<b><span onclick="viewInput(this);" id="hash2" style="font-size:8pt; color:magenta;">#</span></b> &nbsp;
				<b><span onclick="viewInput(this);" id="hash3" style="font-size:8pt; color:olive;">#</span></b> &nbsp;
				<b><span onclick="viewInput(this);" id="hash4" style="font-size:8pt; color:orange;">#</span></b> &nbsp;
				<b><span onclick="viewInput(this);" id="hash5" style="font-size:8pt; color:cyan;">#</span></b> &nbsp;
				</fieldset>
			</form>
		</table>
		</div>
		<button onclick="javascript:location.href='/malant/bdlist';">목록</button>
		<hr>
		<%=  %>
		<div id="dlist" class="board"> 
		<% for(Board b : list){ %>
		<table onclick="checkLogin('<%= b.getBoardNo() %>');" width="500" border="0" style="display: block; margin:10px;
		 border-radius : 10px; border : 1px solid  rgba(154, 179, 213, 0.2); background: rgba(154, 179, 213, 0.1);">
			<tr height="30">
				<td width="40" align="center" style="background: rgba(154, 179, 213, 0.5);"><%= b.getBoardNo() %></td>
				<td width="130" style="background: rgba(154, 179, 213, 0.3);"><%= b.getNickname() %></td>
				<td width="130" align="center" style="background: rgba(154, 179, 213, 0.5 b);"><%= b.getBoardDate() %></td>
				<td rowspan="3" width="200" align="right" >
					<% if(b.getThumbnail() != null){ %>
					<img style="margin-top:5px; margin-right: 5px;" class="title-style" src="/malant/resources/board/images/<%= b.getThumbnail()  %>" 
					width="150" height="150">
					<% }else{ %>
					<img style="margin-top:5px; margin-right: 5px;" class="title-style" src="/malant/resources/board/images/baseSample.jpeg"
					 width="150" height="150">
					<% } %>
				</td>
			</tr>
				
			<tr>
				<td colspan="3"><%= b.getBoardTitle() %><br>
				<p><%= b.getBoardContent().substring(0) %></p>
				<!-- 서브스트링 값 변경-->
				</td>
			</tr>
			<tr height="30"> 
				<td colspan="3">조회수 : <%= b.getViewcount() %> &nbsp; &nbsp; &nbsp; &nbsp;	
				<button class="likeBtn-style" onclick="toggleLike('<%= b.getBoardNo() %>');">좋아요</button>
    			<span id="likeCount_<%= b.getBoardNo() %>"><%= b.getBoardLike() %></span>
				</td>
			</tr>
			 	<tr colspan="4">
			 		&nbsp; <button onclick="javasciprt:location.href='/malant/views/board/myBoardInsert.jsp';">글쓰기</button> &nbsp; 
				 	&nbsp; <button onclick="moveUpdatePage(); return false;">수정페이지로 이동</button> &nbsp;
				 	<button onclick="requestDelete(); return false;">글삭제</button>
				    
			
			 	</tr>
		</table>
		<br>
           
		<% } %>
		
			   </div>
		</div>
	</div>

</body>
</html>