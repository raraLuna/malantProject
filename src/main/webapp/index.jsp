<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="views/common/error.jsp" %>
<!-- index.jsp -->
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Malant</title>
    <link rel="stylesheet" href="/malant/resources/common/css/sidebar.css" />
    <link rel="stylesheet" href="/malant/resources/common/css/scrolling.css" />
    <script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
    <script src="/malant/resources/common/js/scroll_behavior.js"></script>
    <style>
    	.container {
    		align-items: center;
    	}
    </style>
  </head>
  <body>
    <div class="container">
        <!-- 사이드바 -->
        <%@ include file="./views/common/sidebar.jsp" %>
        
        <!-- 메인 검색 페이지 -->
        <%@ include file="./views/search/searchMain.jsp" %>
    </div>
  </body>
</html>
