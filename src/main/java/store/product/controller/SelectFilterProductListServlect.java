package store.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.main.model.vo.MainContent;
import store.product.model.service.ProductService;

/**
 * Servlet implementation class SelectFilterProductListServlect
 */
@WebServlet("/plistf")
public class SelectFilterProductListServlect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectFilterProductListServlect() {
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

		ArrayList<String> options = new ArrayList<String>();

		request.setCharacterEncoding("utf-8");
		String option5 = request.getParameter("option5");
		String option6 = request.getParameter("option6");

		for (int i = 1; i <= 8; i++) {
			if (request.getParameter("option" + i) != null) {
				if (!(request.getParameter("option" + i).equals("난이도")
						|| request.getParameter("option" + i).equals("일조량")
						|| request.getParameter("option" + i).equals("습도")
						|| request.getParameter("option" + i).equals("정화능력")
						|| request.getParameter("option" + i).equals("가습효과")
						|| request.getParameter("option" + i).equals("크기")
						|| request.getParameter("option" + i).equals("용도")
						|| request.getParameter("option" + i).equals("재질")
						|| request.getParameter("option" + i).equals("종류"))) {
					if ("유".equals(request.getParameter("option" + i)) && i == 5) {
						option5 = "purification";
					} else if ("유".equals(request.getParameter("option" + i)) && i == 6) {
						option6 = "humidifying";
					} else {
						option5 = null;
						option6 = null;
					}
					options.add(request.getParameter("option" + i));
				}
			}
		}

		System.out.println(options.toString());
		
		ArrayList<MainContent> plistf = new ProductService().selectFilterList(options);

		RequestDispatcher view = null;

		if (plistf.size() > 0) {
			view = request.getRequestDispatcher("views/store/product/selectProductView.jsp");
			request.setAttribute("plistf", plistf);
		} else {
			System.out.println("servlet실패 : ");
			view = request.getRequestDispatcher("views/store/product/selectProductView.jsp");
			request.setAttribute("message", "조회된 상품이 없습니다.");
		}
		view.forward(request, response);
	}

}
