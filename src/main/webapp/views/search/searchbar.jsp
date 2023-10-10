<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String searchKeyword = (String) request.getAttribute("keyword");
	if(searchKeyword == null) {
		searchKeyword = "";
	}
	
	String difficulty = (String) request.getAttribute("difficulty");
	String growthRate = (String) request.getAttribute("growth_rate");
	String smell = (String) request.getAttribute("smell");
	String placement = (String) request.getAttribute("placement");
	String effectPurification = (String) request.getAttribute("effect_purification");
	
	if(difficulty == null) difficulty = "all";
	if(growthRate == null) growthRate = "all";
	if(smell == null) smell = "all";
	if(placement == null) placement = "all";
	if(effectPurification == null) effectPurification = "N";
%>
<!DOCTYPE html>
<html>
<head>
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="/malant/resources/common/css/font.css" />
<style>
 body {
            margin: 0;
            padding: 0;
        }

        /* 검색 영역 스타일 */
        .search-area {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        /* 검색바 스타일 */
        .searchbar {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .searchbox {
            flex: 1;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .searchbtn {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .searchbtn:hover {
            background-color: #0056b3;
        }

        /* 필터 스타일 */
        .filters {
            display: flex;
            align-items: center;
        }

        .filter {
            margin-right: 10px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        /* 공기정화 체크박스 스타일 */
        #purification {
            margin-left: 10px;
        }
    </style>


<script>
	function selectElement(selectName, value) {
		$(".filter[name='" + selectName + "']").each(function() {
			$(this).find('option').each(function() {
				if($(this).val() == value) {
					$(this).prop('selected', true);
				}
			});
		});
	}
	
	$(function() {
		selectElement('difficulty', '<%= difficulty %>');
		selectElement('growth_rate', '<%= growthRate %>');
		selectElement('smell', '<%= smell %>');
		selectElement('placement', '<%= placement %>');
		if('<%= effectPurification %>' == 'Y') {
			$('#purification').prop('checked', true);
		}
	});
</script>
</head>
<body>
<div class="search-area">
	<form action="/malant/plsearch" method="get">
		<div class="searchbar">
			<input type="search" class="searchbox" name="keyword" value="<%= searchKeyword %>" placeholder="검색어를 입력하세요.">
			<input type="submit" class="searchbtn" value="검색">
		</div>
		<div class="filters">
			<select class="filter" name="difficulty">
				<option value="all" selected>난이도</option>
				<option value="초보자">초보자</option>
				<option value="경험자">경험자</option>
				<option value="전문가">전문가</option>
			</select>
			<select class="filter" name="growth_rate">
				<option value="all" selected>성장속도</option>
				<option value="빠름">빠름</option>
				<option value="보통">보통</option>
				<option value="느림">느림</option>
			</select>
			<select class="filter" name="smell">
				<option value="all" selected>향</option>
				<option value="강함">강함</option>
				<option value="중간">중간</option>
				<option value="약함">약함</option>
				<option value="거의 없음">거의 없음</option>
			</select>
			<select class="filter" name="placement">
				<option value="all" selected>배치장소</option>
				<option value="거실">거실</option>
				<option value="발코니">발코니</option>
				<option value="어두운 곳">어두운 곳</option>
			</select>
			<label><input type="checkbox" id="purification" name="effect_purification" value="Y">공기정화</label>
		</div>
	</form>
</div>
</body>
</html>