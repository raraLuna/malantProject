package store.shoppingBasket.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.shoppingBasket.model.service.ShoppingBasketService;
import store.shoppingBasket.model.vo.ShoppingBasket;


/**
 * Servlet implementation class SelectBasketListServlet
 */
@WebServlet("/sblist")
public class SelectBasketListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBasketListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userno = request.getParameter("userNo");
		
		System.out.println("sblist 들어온 유저 : " + userno);
		
		ArrayList<ShoppingBasket> sblist = new ShoppingBasketService().selectSblist(userno);

		
		RequestDispatcher view = null;
		if (sblist != null) {
			view = request.getRequestDispatcher("views/store/basket/ShoppingBasket.jsp");
			request.setAttribute("sblist", sblist);
			System.out.println("servlet성공 : " + sblist.toString());
		} else {
			System.out.println("servlet실패 : ");
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "조회된 상품이 없습니다.");
		}
		view.forward(request, response);
	}
}
