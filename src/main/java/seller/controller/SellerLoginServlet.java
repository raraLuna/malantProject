package seller.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.model.service.SellersService;
import seller.model.vo.Sellers;

/**
 * Servlet implementation class SellerLoginServlet
 */
@WebServlet("/slogin")
public class SellerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellerLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher view = null;

		String sellerId = request.getParameter("sellerid");
		String sellerPwd = request.getParameter("sellerpwd");
		System.out.println("입력id " + sellerId);
		System.out.println("입력pw " + sellerPwd);

		ArrayList<Sellers> sellers = new SellersService().slogin(sellerId, sellerPwd);

		for (int i = 0; i < sellers.size(); i++) {
			System.out.println("DBid " + sellers.get(i).getSellerId());
			System.out.println("DBpw " + sellers.get(i).getSellerPwd());

			if (sellers.get(i).getSellerId().equals(sellerId) && sellers.get(i).getSellerId().equals(sellerPwd)) {
//					view = request.getRequestDispatcher("malant/views/seller/sellerProductList.jsp");
					response.sendRedirect("malant/views/seller/sellerProductList.jsp");
					break;
			}
		}

		view.forward(request, response);
	}

}
