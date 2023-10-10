<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="notice.model.vo.Notice, java.util.ArrayList"%>
<%
ArrayList<Notice> myblist = (ArrayList<Notice>) request.getAttribute("nlist");
Notice notice = (Notice) request.getAttribute("notice");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Malant</title>
<link rel="stylesheet" type="text/css"
	href="/malant/resources/notice/css/noticecontents.css">
<style>
.notice-detail-container {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	padding: 100px; /* 내용 주위에 여백을 추가할 수 있습니다. */
}

.notice-image {
	width: 900px;
	height: auto;
}
</style>

</head>
<body>
	<div class="center-content">
		<div class="notice-detail">
			<div class="container">
				<%@ include file="../../views/common/sidebar.jsp"%>
			</div>
			<div class="notice-detail-container">
				<img class="notice-image" src="<%=notice.getContentImage()%>">
				
				제목 : <%=notice.getTitle()%> <br> 
				내용 : <%=notice.getContent()%> <br>
			<% if (notice.getNoticeType().equals("NOTICE")) { %>

				등록일:
				<%=notice.getPostDate()%>
			</div>
				
			<% } else if (notice.getNoticeType().equals("EVENT")) { %>
				
				이벤트 기간 :
				<%=notice.getEventStart()%> ~ <%=notice.getEventEnd()%>
			
			<% } %>

		</div>
	</div>
</body>
</html>