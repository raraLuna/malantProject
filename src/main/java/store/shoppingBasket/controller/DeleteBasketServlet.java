package store.shoppingBasket.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import store.shoppingBasket.model.service.ShoppingBasketService;

@WebServlet("/sbdelete")
public class DeleteBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteBasketServlet() {
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
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		String selectedProductIds = request.getParameter("selectedProductIds");
		String userNo = request.getParameter("userNo");
		String[] productIdArray = selectedProductIds.split(",");

		int result = new ShoppingBasketService().deleteShoppingBasket(userNo, productIdArray);
		
		for (String productId : productIdArray) {
			out.println("삭제할 제품 ID: " + productId + "<br>");
		}
		
		JSONObject jsonResponse = new JSONObject();

		if (result > 0) {
		    jsonResponse.put("success", true);
		} else {
		    jsonResponse.put("success", false);
		}

		PrintWriter outs = response.getWriter();
		outs.print(jsonResponse.toString());
		outs.flush();
	}
}

