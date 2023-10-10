<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, search.model.vo.Plant, common.Paging" %>
<%
	String keywd = (String) request.getAttribute("keyword");
	/* String difficulty = (String) request.getAttribute("difficulty");
	String growthRate = (String) request.getAttribute("growth_rate");
	String smell = (String) request.getAttribute("smell");
	String placement = (String) request.getAttribute("placement");
	String effectPurification = (String) request.getAttribute("effect_purification"); */

	ArrayList<Plant> list = (ArrayList<Plant>) request.getAttribute("list");
	int nowPage = ((Integer) request.getAttribute("currentPage")).intValue();
	
	int listCount = ((Paging) request.getAttribute("paging")).getListCount();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= keywd %> 검색 결과</title>
<style>
/* 전체 페이지 스타일 */
body {
    margin: 0;
    padding: 0;
}

/* 컨텐츠 영역 스타일 */
.content{
	min-width: 800px;
	padding-left: 20px;
	padding-top: 20px;
	margin: 0 auto;
	flex-grow: 1;
	max-width: calc(100% - 300px);
	margin-left: 250px;
    flex: 2; 
    background-color: #fff;
    /* box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); */
    border-radius: 5px;
    }

/* 검색 영역 스타일 */
.search-area {
	margin
    background-color: #f9f9f9;
    padding: 20px;
    border-radius: 5px;
    margin-bottom: 20px;
}

/* 검색바 스타일 */
.searchbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
}

.searchbox {
	max-width: 100%;
    flex: 1;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-right: 15px;
}

.searchbtn {
    background-color: #007bff;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
    margin-right: 10px;
}

.searchbtn:hover {
    background-color: #0056b3;
}

/* 필터 스타일 */
.filters {
    display: flex;
    justify-content: center;
    align-items: center;
}

.filter {
    padding: 10px 20px;
    margin: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #fff;
}

/* 검색 결과 스타일 */
.results {
    margin-top: 20px;
    max-width: 800px;
    margin:0 auto;
}

.result-guide {
    width: 800px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: auto;
}

.result-message {
    font-size: 18px;
    font-weight: bold;
}

.sort-items {
    display: flex;
    align-items: center;
}

.sort-item {
    padding: 10px 20px;
    margin: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.sort-item:hover {
    background-color: #f4f4f4;
}

/* 결과 카드 스타일 */
.result-cards {
    width: 800px;
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    align-items: flex-start;
    margin-top: 20px;
    margin: auto;
}

.plant-card {
    width: 140px;
    height: 180px;
    border: 1px solid #ccc;
    margin: 10px;
    border-radius: 5px;
    cursor: pointer;
    transition: box-shadow 0.3s;
    padding:10px
}

.plant-card:hover {
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.card-image img {
    max-width: 100%;
    max-height: 100%;
    object-fit: cover;
    border-radius: 5px;
}

.card-title {
    padding: 10px;
    text-align: center;
    font-weight: bold;
}

</style>
</head>
<body>
	<div class="container">
		<div class="sidebar">
			<%@ include file="../common/sidebar.jsp" %>
		</div>
		<div class="content">
			<%@ include file="searchbar.jsp" %>
			
			<div class="results">
				<div class="result-guide">
					<% if(list.size() == 0) { %>
						<h4>검색 결과가 존재하지 않습니다.</h4>
					<% } else { %>
						<div class="result-message">
							<h4>'<%= keywd %>' 에 대한 검색 결과입니다.(<%= listCount %> 건)</h4>
						</div>
<!-- 						<div class="sort-items">
							관련도순 어떻게 검색해올지 고민
							<div class="sort-item"><a href="#">관련도순</a></div>
							 -->
							<!-- 
							<div class="sort-item" id="sort-name"><a href="#">이름순</a></div>
							<div class="sort-item" id="sort-viewcount"><a href="#">인기순</a></div>
							 -->
						</div>
					<% } %>
				</div>
				
				<% if(list.size() > 0) { %>
				<div class="result-view">
					<div class="result-cards">
						<% for (Plant plant : list) { %>
						<div class="plant-card" onclick="javascript: location.href='/malant/pldetail?pno='+<%= plant.getPlantNo() %>">
							<div class="card-image">
								<img src=<%= plant.getPlantImg() %> width="140" height="140">
							</div>
							<div class="card-title">
								<center><p4><%= plant.getPlantName() %></p4></center>
							</div>
						</div>
					<%	} // for %>
					</div>
					<div class="paging">
						<%@ include file="../common/pagingView.jsp" %>
					</div>
				<% } else { %>
					<div class="result-cards">
						<img src="/malant/resources/common/images/mandragora_character.jpg">
					</div>
				<% } %>
				</div>
			</div>
		</div>
	</div>
</body>
</html>