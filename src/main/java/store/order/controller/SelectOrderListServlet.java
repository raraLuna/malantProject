package store.order.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.order.model.service.OrderService;
import store.order.model.vo.ProductOrder;

/**
 * Servlet implementation class SelectOrderListServlet
 */
@WebServlet("/olist")
public class SelectOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOrderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userno = request.getParameter("userNo");
		
		System.out.println("olist 들어온 유저 : " + userno);
		
		ArrayList<ProductOrder> olist = new OrderService().selectOrderlist(userno);

		
		RequestDispatcher view = null;
		if (olist != null) {
			view = request.getRequestDispatcher("views/store/order/myOrderList.jsp");
			request.setAttribute("olist", olist);
			System.out.println("servlet성공 : " + olist.toString());
		} else {
			System.out.println("servlet실패 : ");
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "조회된 상품이 없습니다.");
		}
		view.forward(request, response);
	}
}
