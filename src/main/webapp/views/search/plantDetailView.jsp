<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="search.model.vo.Plant"%>
<%
	Plant plant = (Plant) request.getAttribute("plant");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= plant.getPlantName ()%> 상세정보</title>
<style>
body {
    margin: 0;
    padding: 0;
    text-align: center; 
}

.content {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    background-color: transparent;
    border-radius: 5px;
    text-align: left; 
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}


.simple-wrapper {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
}

.simple-info {
    width: 65%;
    padding: 20px;
    border-radius: 5px;
    background-color: #f9f9f9;
}

.simple-item-wrapper {
    margin-bottom: 10px;
}


.plant-photo {
    width: 50%;
    text-align: center;
}

.plant-photo img {
    max-width: 100%;
    max-height: 100%;
    border-radius: 5px;
}


.main-feature-table {
    width: 100%;
    border-collapse: collapse; 
}

.main-feature-table th,
.main-feature-table td {
    padding: 8px;
    text-align: left;
}


.tips-and-precautions {
    margin-top: 20px;
}

.tips-title {
    font-weight: bold;
    font-size: 18px;
    margin-bottom: 10px;
}

.tips {
    line-height: 1.6;
}


.managing-info {
    margin-top: 20px;
}

.managing-item {
    padding: 10px;
    border-radius: 5px;
    margin-bottom: 10px;
}

.managing-item th {
    text-align: left;
    font-weight: bold;
}


.title {
    text-align: center;
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 20px;
}
</style>

</head>
<body>
	<div class="container">
		<div class="sidebar">
			<%@ include file="../common/sidebar.jsp" %>
		</div>
		<div class="content">
			<div class="simple-wrapper">
				<div class="simple-info">
					<div class="simple-item-wrapper">
						<table>
							<tr><th>이름</th><td><%= plant.getPlantName() %></td></tr>
							<tr><th>영명</th><td><%= plant.getEnglishName() %></td></tr>
							<tr><th>학명</th><td><%= plant.getScientificName() %></td></tr>
							<tr><th>과명</th><td><%= plant.getFamilyName() %></td></tr>
						</table>
					</div>
					<div class="main-feature-wrapper">
						<table class="main-feature-table">
							<tr><th colspan="2">주요 특징</th></tr>
							<% if(plant.getDifficulty() != null) { %>
								<tr><th>키움 난이도</th><td><%= plant.getDifficulty() %></td></tr>
							<% } %>
							<% if(plant.getTemperature() != null) { %>
								<tr><th>적정 온도</th><td><%= plant.getTemperature() %></td></tr>
							<% } %>
							<% if(plant.getHumidity() != null) { %>
								<tr><th>적정 습도</th><td><%= plant.getHumidity() %></td></tr>
							<% } %>
							<% if(plant.getLight() != null) { %>
								<tr><th>햇빛</th><td><%= plant.getLight() %></td></tr>
							<% } %>
							<% if(plant.getWateringSpring() != null) { %>
								<tr><th>물주기</th><td><%= plant.getWateringSpring() %></td></tr>
							<% } %>
							
						</table>
					</div>
				</div>
				<div class="plant-photo"><img src="<%= plant.getPlantImg() %>" width="230" height="280"></div>
			</div>
			<div class="detail-wrapper">
				<div class="tips-and-precautions">
					<% if(plant.getAdviceInfo() != null || plant.getUsefulInfo() != null || plant.getManagingTips() != null) { %>
					<div class="tips-title">추가적인 정보 및 주의사항 영역</div>
					<div class="tips">
						<% if(plant.getAdviceInfo() != null) { %>
							<%= plant.getAdviceInfo() %><br>
						<% } %>
						
						<% if(plant.getUsefulInfo() != null) { %>
							<%= plant.getUsefulInfo() %><br>
						<% } %>
					</div>
					<% } %>
				</div>
				<div class="managing-info">
					<div class="managing-item">
						<table width="750">
							<% if(plant.getSoil() != null) { %>
								<tr><th>토양</th><td><%= plant.getSoil() %></td></tr>
							<% } %>
							<% if(plant.getFertilizer() != null) { %>
								<tr><th>비료</th><td><%= plant.getFertilizer() %></td></tr>
							<% } %>
							<% if(plant.getManagingDiseasesPests() != null) { %>
								<tr><th>병충해관리</th><td><%= plant.getManagingDiseasesPests() %></td></tr>
							<% } %>
							<% if(plant.getSeasonBlooming() != null) { %>
								<tr><th>개화시기</th><td><%= plant.getSeasonBlooming() %></td></tr>
							<% } %>
							<% if(plant.getSeasonFruiting() != null) { %>
								<tr><th>결실시기</th><td><%= plant.getSeasonFruiting() %></td></tr>
							<% } %>
							<% if(plant.getSeasonPropagation() != null) { %>
								<tr><th>번식시기</th><td><%= plant.getSeasonPropagation() %></td></tr>
							<% } %>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>