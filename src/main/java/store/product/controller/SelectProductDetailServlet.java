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
 * Servlet implementation class SelectProductDetailServlet
 */
@WebServlet("/pdetail")
public class SelectProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectProductDetailServlet() {
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
		
		String productid = request.getParameter("productid");
		
		ArrayList<ProductDetail> list = new ProductService().selectProductDetail(productid);
		RequestDispatcher view = null;
		if (list.size() > 0) { 
			view = request.getRequestDispatcher("views/store/product/productDetail.jsp");
			request.setAttribute("list", list);
			System.out.println("servlet성공 : " + list.toString());
		} else {
			System.out.println("servlet실패 : ");
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", productid + " 상품정보 조회 실패");
		}
		
		view.forward(request, response);
	}
}
