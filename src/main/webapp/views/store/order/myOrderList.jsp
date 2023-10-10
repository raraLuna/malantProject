<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="store.order.model.vo.ProductOrder, java.util.ArrayList"%>
<%
 ArrayList<ProductOrder> olist = (ArrayList<ProductOrder>)request.getAttribute("olist");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="/malant/resources/store/css/orderInputForm.css" />
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script>

<style>
#all-buy {
	display: block;
	margin: 0 auto;
	text-align: center;
	margin-top: 20px;
	width: 100px;
	height: 50px;
}

 #delete-selected-items {
 	display: block;
	margin: 0 auto;
	text-align: center;
	margin-top: 20px;
	width: 100px;
	height: 50px;
 }
 
</style>


</head>
<body>
	<div class="container">
		<!-- 사이드바 -->
		<%@ include file="../common/storeSidebar.jsp"%>
		<div class="content">
			<div class="order-productline">
				<h1>주문 목록</h1>
				<hr border=100vh>
				<div class="status-buttons">
					<button id="filter-all">전체</button>
					<button id="filter-pending">결제전</button>
					<button id="filter-completed">결제완료</button>
					<button id="filter-shipping">배송중</button>
					<button id="filter-delivered">배송완료</button>
					<button id="filter-confirmed">구매확정</button>
				</div>
				<div class="order-table">
				<table style="width: -webkit-fill-available; text-align: center;"
					border="1px;">
					<tr>
						<td></td>
						<td>제품 정보</td>
						<td>주문일</td>
						<td>가 격</td>
						<td>배송비</td>
						<td>수 량</td>
						<td>총 금액</td>
						<td>상 태</td>
					</tr>
					<%
					 for (ProductOrder sb : olist) { 
					%>
					<tr>
						<td><img width="100px" height="100px"
							src="<%=sb.getThumbnailImg()%>"></td>
						<td><%=sb.getProductName()%></td>
						<td><%=sb.getOrderDate()%></td>
						<td><%=sb.getPrice()%></td>
						<td><%=sb.getDeliveryCharge()%></td>						
						<td><%=sb.getQuantity()%></td>
						<td><%=((sb.getPrice()*sb.getQuantity())+sb.getDeliveryCharge())%></td>
						<td><%=sb.getOrderState()%></td>
					</tr>
					<%
					}
					%> 
				</table>
			</div>
		</div>
	</div>
</div>

	<script>
		// 필터 버튼 클릭 이벤트 처리
		document.getElementById('filter-all').addEventListener('click', function () {
			filterOrders('전체');
		});
		document.getElementById('filter-pending').addEventListener('click', function () {
			filterOrders('결제전');
		});
		document.getElementById('filter-completed').addEventListener('click', function () {
			filterOrders('결제완료');
		});
		document.getElementById('filter-shipping').addEventListener('click', function () {
			filterOrders('배송중');
		});
		document.getElementById('filter-delivered').addEventListener('click', function () {
			filterOrders('배송완료');
		});
		document.getElementById('filter-confirmed').addEventListener('click', function () {
			filterOrders('구매확정');
		});

		// 주문 목록 필터링 함수
    function filterOrders(status) {
        const rows = document.querySelectorAll('.order-table table tr');

        rows.forEach(function (row, index) {
            if (index === 0) {
                // 첫 번째 행 (타이틀 행)은 항상 보이도록 설정
                row.style.display = 'table-row';
            } else {
                const statusCell = row.querySelector('td:last-child');
                if (statusCell) {
                    const orderStatus = statusCell.innerText.trim();
                    if (status === '전체' || orderStatus === status) {
                        row.style.display = 'table-row';
                    } else {
                        row.style.display = 'none';
                    }
                }
            }
        });
    }
		// 페이지 로드시 전체 주문 목록 보여주기
		window.addEventListener('load', function () {
			filterOrders('전체');
		});
	</script>
</body>
</html>
