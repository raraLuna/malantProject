package search.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import search.model.service.SearchService;
import search.model.vo.Plant;

/**
 * Servlet implementation class PlantDetailServlet
 */
@WebServlet("/pldetail")
public class PlantDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlantDetailServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int plantNo = Integer.parseInt(request.getParameter("pno"));
		
		Plant plant = new SearchService().selectPlant(plantNo);
		
		RequestDispatcher view = null;
		if(plant != null) {
			view = request.getRequestDispatcher("views/search/plantDetailView.jsp");
			request.setAttribute("plant", plant);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "상세보기 페이지 이동 실패. 식물 정보 추가 요망. 관리자에게 문의바랍니다.");
		}
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
