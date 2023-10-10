<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="map.model.vo.Arboretum"%>
<% 
   Arboretum arboretum = (Arboretum)request.getAttribute("arboretum"); 
%>
<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>수목원 정보</title>
<style type="text/css">

  .outer-container {
    display: flex;
    justify-content: center; /* 수평 중앙 정렬 */
    margin-left: 200px; /* 왼쪽 여백 설정 */
    padding: 20px; /* 컨텐츠 내부 여백 설정 */
    min-height: 100vh;
    
  }
  .table-container {
     margin: 0 auto; /* 가로 가운데 정렬 */
     max-width: 800px; /* 테이블의 최대 너비 설정 */
     padding: 20px; /* 컨텐츠 내부 여백 설정 */
     align-self: center;
  }
  
  .content {
    margin-left: 20px; /* 왼쪽 여백 설정 */
    padding: 20px; /* 컨텐츠 내부 여백 설정 */
  }

  table {
  	 
  }
  
  table td {
   
   	padding:10px;
    width: 400px;
    border-radius : 10px;
    border : 1px solid  rgba(154, 179, 213, 0.4);
    background: rgba(154, 179, 213, 0.1);
    vertical-align: top;

  }
  
   table td.title-td {
	 background: rgba(154, 179, 213, 0.2);
	 border: none;
	 vertical-align: inherit;
	 padding: 15px; /* 내부 여백을 추가하여 눈에 띄게 만듭니다. */
  	 font-weight: bold; /* 글꼴 굵기 설정 */
	
   }
  
  table td h2 {
     text-align:center;
     top: 0px;
     background:rgba(154, 179, 213, 0.3);
     border-radius : 10px;
  }
  
  table td h1 {
     text-align:center;
     
  }
</style>
</head>

<body>
<script type="text/javascript" src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
	var text = "<%= arboretum.getFee_etc() %>";
	var words = text.split(",");
	
	var	result = words.join("<br>");
</script>
<div class="outer-container">
   <div style="position:absolute;top:0;left:0;"><%@ include file="../common/sidebar.jsp"%></div>
   
   <div class="table-container">
      <table>
         <tr>
            <td class="title-td" style="background: rgba(154, 179, 213, 0.2);">
               <h1><%= arboretum.getArboretum_name() %></h1>
            </td>
            
            <td>
               <h2 id="address">주소</h2>
               <br>
               <h4 style="text-align:center;"><%= arboretum.getArboretum_address() %></h4>
            </td>
         </tr>
         <tr>
            <td>
               <h2 id="website">홈페이지</h2>
               <br>
               <% if(arboretum.getArboretum_homepage() == null) { %>
                 <h4 style="text-align:center;">홈페이지를 개설하지 않았습니다.</h4>
               <% }else{ %>
               
                   <% if(arboretum.getArboretum_homepage().contains("http://")) { %>
                      <h4 style="text-align:center;"><a style="text-align:center;" href="<%= arboretum.getArboretum_homepage() %>"><%= arboretum.getArboretum_homepage() %></a></h4>
                   <% }else { %>
                      <h4 style="text-align:center;"><a style="text-align:center;" href="https://<%= arboretum.getArboretum_homepage() %>"><%= arboretum.getArboretum_homepage() %></a></h4>
                   <% } %>
                   
               <% } %>
            </td>
            
            <td>
               <h2 id="openClose">Open/Close</h2>
               <br>개관 :
               <%= arboretum.getOpen_days() %><br>
               <br>휴관 :
               <%= arboretum.getClosed_days() %>
            </td>
         </tr>
         <tr>
            <td>
               <h2 id="entranceFee">입장료</h2>
               <% if(arboretum.getEntrance_fee_yn().equals("Y")) { %>
                  <br>성인 :
                  <%= arboretum.getFee_adult() %>원
                  <br>청소년 :
                  <%= arboretum.getFee_teenage() %>원
                  <br>어린이입장료 :
                  <%= arboretum.getFee_child() %>원
                  <br>장애인 :
                  <%= arboretum.getFee_disabled() %>원
                  <br>기타 : <br>
					<script type="text/javascript">
					    document.write(result);
					</script>
               <% }else{ %>
                  <br>
                     <h4 style="text-align:center;">무료</h4>
               <% } %>
            </td>
               
            <td>
               <h2 id="phone" style="top:0px;">전화번호</h2>
               <br>
               <h4 style="text-align:center;">
               <% if(arboretum.getArboretum_tel() != null) { %>
                  <%= arboretum.getArboretum_tel() %>
               <% }else{ %>
                  등록된 번호가 없습니다.
               <% } %></h4>
            </td>
         </tr>
         <tr>
            <td>
               <h2 id="companion">동반 입장</h2>
               <br>반려동물 동반 : 
               <% if(arboretum.getWith_pet_yn().equals("Y")) { %>
                  가능<br>
               <% }else{ %>
                  불가<br>
               <% } %>
               <br>안내견 동반 : 
               <% if(arboretum.getWith_guidedog_yn().equals("Y")) { %>
                  가능<br>
               <% }else{ %>
                  불가<br>
               <% } %>
            </td>
               
            <td>
               <h2 id="education">교육프로그램</h2>
               <% if(arboretum.getEdu_program_yn().equals("Y")) { %>
                  <br>교육프로그램명 :
                  <%= arboretum.getEdu_program_name() %><br>
                  <br>교육프로그램 예약 여부 : 가능 
               <% }else{ %>
                  <br><h4 style="text-align:center;">교육프로그램을 운영하지 않습니다.</h3>
               <% } %>
            </td>
         </tr>
      </table>
   </div>
</div>

</body>
</html>