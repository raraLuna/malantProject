package store.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.product.model.service.ProductService;
import store.product.model.vo.ProductDetail;

/**
 * Servlet implementation class SelectProductListServlet
 */
@WebServlet("/plist")
public class SelectProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectProductListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productid = request.getParameter("categoryid");

		ArrayList<ProductDetail> list = new ProductService().selectProductList(productid);
		RequestDispatcher view = null;
		if (list.size() > 0) {
			view = request.getRequestDispatcher("views/store/product/selectProductView.jsp");
			request.setAttribute("list", list);
			System.out.println("servlet성공 : " + list.toString());
		} else {
			System.out.println("servlet실패 : ");
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "조회된 상품이 없습니다.");
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
