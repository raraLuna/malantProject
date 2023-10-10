package store.shoppingBasket.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.shoppingBasket.model.service.ShoppingBasketService;
import store.shoppingBasket.model.vo.ShoppingBasket;

/**
 * Servlet implementation class AddShoppingBasketServlet
 */
@WebServlet("/sbadd")
public class AddShoppingBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShoppingBasketServlet() {
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

		String productidstr = request.getParameter("productid");
		String userno = request.getParameter("userno");
		String quantitystr = request.getParameter("quantity");
		int quantity = Integer.valueOf(quantitystr);
		int productid = Integer.valueOf(productidstr);
		
		
		int result = new ShoppingBasketService().addBasket(userno, productid, quantity);
		
		System.out.println(result);
		
		RequestDispatcher view = null;
		
		if (result > 0 ) {
			response.sendRedirect("/malant/views/store/basket/addBasketOk.jsp");
		} else {
			System.out.println("servlet실패 : ");
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "조회된 상품이 없습니다.");
			view.forward(request, response);
		}
		
	}

}
