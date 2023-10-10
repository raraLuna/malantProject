<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, map.model.vo.Arboretum"%>
<%
	ArrayList<Arboretum> list = (ArrayList<Arboretum>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">

.container {
	display:flex;
}

.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:13px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{text-decoration: none;}
.map_wrap {position:relative;width:83%;height:500px;}
#menu_wrap {position:absolute;top:0;right:0;bottom:0;width:250px;margin:60px 20px -300px 0;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap ul hr {display: block; height: 1px;border: 0; border-top: 1px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option button {margin-left:5px;}
.page a:hover{color: #ff6600;}
#placesList a {
  font-size:17px;
  font-weight:bold;
  color:#0077FF;
  text-decoration: none;
}
#placesList div a:hover{
  color: blueviolet;
}
</style>
<meta charset="utf-8">
<title>수목원 맵</title>

</head>
<body>
	<div class="container">
		<div><%@ include file="../common/sidebar.jsp"%></div>
		<hr>
		<div class="map_wrap">
    <div id="map" style="width:100%;height:100vh;position:absolute;top:0;right:0;"></div>
	
			<div id="menu_wrap" class="bg_white">
				<div class="option">
					<div>
						<form action="/malant/arsearch" method="get">
							<input style="width:189px;" type="text" value="" id="keyword" placeholder="지역 or 이름을 입력하세요.">
							<button id="set-value-button" type="submit">검색</button>
							<input type="hidden" id="hidden-input" name="search">
						</form>
					</div>
				</div>
				<hr>
				<ul id="placesList">
					<% for(Arboretum a : list){ %>
						<div>
						<a href="/malant/ardetailinfo?arid=<%= a.getArboretum_id() %>">
														<%= a.getArboretum_name() %></a></div><br>
						<div style="font-size: 15px"><%= a.getArboretum_address() %></div><br>
						<% if(a.getArboretum_tel() != null) { %>
							<div class="content" style="font-size:14px; color:green;"><%= a.getArboretum_tel() %></div>
						<% }else{ %>
							등록된 번호가 없습니다.
						<% } %>
						<hr>
					<% } %>
				</ul>
			</div>
		</div>
		<div style="position:absolute;bottom:0;left:0;"><button type="submit" onclick="javascript:location.href='/malant/views/map/mapSortation.html';">폴리곤</button></div>
	</div>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ebd5781688fcaeab7febed1207bcf8f3&libraries&libraries=clusterer"></script>
	<script type="text/javascript"
		src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
	<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(36.2683, 128.3), // 지도의 중심좌표
        level: 13 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

var imageSrc = '/malant/resources/map/images/namu.png', // 마커이미지의 주소입니다    
imageSize = new kakao.maps.Size(45, 45), // 마커이미지의 크기입니다
imageOption = {offset: new kakao.maps.Point(27, 40)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

// 마커가 표시될 위치입니다 
<% for(Arboretum a : list) { %>
	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
		markerPosition = new kakao.maps.LatLng(<%= a.getArboretum_latitude() %>, <%= a.getArboretum_longitude() %>)
	var marker = new kakao.maps.Marker({
	    position: markerPosition,
	    image: markerImage,
	});
	
	marker.setMap(map);
	

	var infowindow = new kakao.maps.InfoWindow({
		content : '<div class="infowindo">' + 
					'<div class="title"><%= a.getArboretum_name() %></div>' + 
					'<div class="page"><a href="/malant/ardetailinfo?arid=<%= a.getArboretum_id() %>">상세정보 보기</a></div>'
			  + '</div>',
					removable : true,
					zIndex : 1
				});

		(function(marker, infowindow) {
			kakao.maps.event.addListener(marker, 'click', function() {
				// 마커 위에 인포윈도우를 표시합니다
				infowindow.open(map, marker);
			});

			kakao.maps.event.addListener(map, 'click', function() {
				infowindow.close();
			});

		})(marker, infowindow);
		
	<%}%>
	
	$(document).ready(function () {
        // 버튼 클릭 시 hidden input의 값을 설정
        $("#set-value-button").click(function () {
            // 제이쿼리를 사용하여 hidden input의 값을 설정
            $("#hidden-input").val(document.getElementById("keyword").value);
        });
    });
	
	
	</script>
</body>
</html>