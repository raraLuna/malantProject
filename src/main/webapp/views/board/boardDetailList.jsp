<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="community.model.vo.Board, community.model.vo.CMBoardPhoto, java.util.ArrayList, community.model.vo.Comment"%>
<%
	Board board = (Board)request.getAttribute("board");
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	ArrayList<CMBoardPhoto> photoList = (ArrayList<CMBoardPhoto>)request.getAttribute("photoList");
	ArrayList<Comment> clist = (ArrayList<Comment>)request.getAttribute("clist");
%>  
<!DOCTYPE html>
<!-- 커뮤니티 디테일 화면 -->
<html>
<head>
<meta charset="UTF-8">
<title>first</title>
<script type="text/javascript"
	src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<style type="text/css">

div.detailview{
	display: flex;
	align-items: center;
	flex-direction: column;
}

</style>
<script type="text/javascript">

function moveUpdatePage(){
	location.href = "/malant/bupview?board=<%= board.getBoardNo() %>&page=<%= currentPage %>";
}

function requestDelete(boardNo) {
	location.href = "/malant/bdelete?boardNo=" + boardNo;
}

</script>
</head>
<body>
<%@ include file="../../views/common/sidebar.jsp"%>
		<div id="hashlist" class="detailview">
		<%-- 제목 검색 폼 --%>
		<button onclick="javascript:location.href='/malant/bdlist';">목록</button>
		<hr>
	<table width="700" border="0" style="display: block; margin:10px; border-radius : 10px; border : 1px solid  rgba(154, 179, 213, 0.2); background: rgba(154, 179, 213, 0.1);">
			<tr height="30">
				<th align="center" width="100" align="center" style="background: rgba(154, 179, 213, 0.5);">
					<%= board.getBoardNo() %></th>
				<td width="400">
					<%= board.getBoardTitle() %></td>
				<td width="100" align="center" style="background: rgba(154, 179, 213, 0.5 b);"> 
					<img src="/malant/resources/board/images/likebtn.jpg" width="20" height="20"> &nbsp;
					<%= board.getBoardLike() %></td>
				<td  width="100" align="center" >
					조회수 : <%=board.getViewcount() %></td>
			</tr>
			<tr>
				<th align="center"  style="background: rgba(154, 179, 213, 0.5);">작성자</th>
				<td><%= board.getNickname() %></td>
				<td colspan="2" align="right"><%= board.getBoardDate() %></td>
			</tr>
			<tr height="30"> 
				<th align="center"  style="background: rgba(154, 179, 213, 0.5);">내용</th>
				<td colspan="3"><%= board.getBoardContent() %></td>
			</tr>		
			<tr height="30"> 
				<th align="center"  style="background: rgba(154, 179, 213, 0.5);">첨부사진</th>
				<td colspan="3">
					<%if(photoList.size() > 0){ 
						for(CMBoardPhoto p : photoList){
					%>
						<img style="margin-top:5px; margin-right: 5px;" src="/malant/resources/board/images/<%= p.getFilename() %>" width="400" height="300">
					<% }}else{ %>
						&nbsp;
					<% } %>
				</td>
			</tr>
			<tr>
		<th colspan="4">
			<%-- 댓글달기 버튼은 로그인한 경우에만 보이게 함 --%>
			<% if(loginMember != null){ //로그인한 상태이면
					if(loginMember.getUserNo().equals(board.getUserNo())){
						//로그인한 회원 아이디와 글작성자가 같다면 (본인이 올린 글이면)
			%>
				<button onclick="moveUpdatePage(); return false;">수정페이지로 이동</button> &nbsp;
				<button onclick="requestDelete(<%= board.getBoardNo() %>); return false;">글삭제</button> &nbsp;
			<% }}%>
			 <button onclick="javascript:location.href='/malant/bdlist?page=<%= currentPage %>';">목록</button> 
		</th>		
	</tr>
		</table>
		
			<div class="comment">
				<form action="/malant/cminsert">
					<input type="hidden" name="userno" value="<%=loginMember.getUserNo()%>"> 
					<input type="hidden" name="bno" value="<%=board.getBoardNo()%>">
					<input type="hidden" name="nickname" value="<%=loginMember.getNickname()%>"> 
					<input type="text" name="comment" placeholder="댓글을 입력하세요"> 
					<input type="submit">
				</form>
				<div style="overflow-y: scroll; width: 700px; height: 23.25em; border:1px solid rgba(154, 179, 213); 
				background: rgba(154, 179, 213, 0.2); resize: none" class="commentbox" name="commentbox" placeholder="댓글을 입력해주세요" >
				<% for (int i = 0; i < clist.size(); i++) { %>
				<%=clist.get(i).getCommentContent()%>
				<%=clist.get(i).getNickname()%>
				<% if (loginMember.getUserNo().equals(clist.get(i).getUserNo())) { %>

				<!-- 수정 -->
				<form action='/malant/cmupdate'>
					<input type="hidden" name="cno" value="<%= clist.get(i).getCommentNo() %>">
					<input type="hidden" name="bno" value="<%= board.getBoardNo() %>">
					<input type="text" name="content" value="<%= clist.get(i).getCommentContent() %>">
					<input type="submit" value="수정">
				</form>
				<!-- 삭제 -->
				<form action='/malant/cmdelete'>
					<input type="hidden" name="cno" value="<%= clist.get(i).getCommentNo() %>">
					<input type="hidden" name="bno" value="<%= board.getBoardNo() %>">
					<input type="submit" value="삭제">
				</form>
				<% } %>
				<br>
				<hr>
				<% } %>
			</div>
			</div>
<br>

<hr>
</div>
</body>
</html>
