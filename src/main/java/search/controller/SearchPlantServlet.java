package search.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;
import search.model.service.SearchService;
import search.model.vo.Plant;

/**
 * Servlet implementation class SearchPlantServlet
 */
@WebServlet("/plsearch")
public class SearchPlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPlantServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 식물 검색용 컨트롤러
		SearchService service = new SearchService();
		int listCount = 0;
		ArrayList<Plant> list = null;
		
		// request에서 값 꺼내기
		request.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		
		String difficulty = request.getParameter("difficulty");
		String growthRate = request.getParameter("growth_rate");
		String smell = request.getParameter("smell");
		String placement = request.getParameter("placement");
		String effectPurification = request.getParameter("effect_purification");
		if(effectPurification == null) {
			effectPurification = "N";
		}
		
		Map<String, String> filters = new HashMap<>();
		filters.put("difficulty", difficulty);
		filters.put("growth_rate", growthRate);
		filters.put("smell", smell);
		filters.put("placement", placement);
		filters.put("effect_purification", effectPurification);
		
		System.out.println("\n***검색 결과 	잘 받아왔는지 체크***");
		System.out.println("keyword : " + keyword);

		filters.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });
		
		
		// 페이징 처리 준비
		String page = request.getParameter("page");
		int currentPage = page != null ? Integer.parseInt(page) : 1;
		int limit = 8;
		Paging paging = null;
		
		// 검색 수행
		if(keyword.length() > 0) { // 키워드가 있을 경우 키워드 검색
			listCount  = service.getListCount(keyword);
			
			paging = new Paging(listCount, currentPage, limit, "plsearch");
			paging.calculator();
			
			list = service.selectPlantList(keyword, paging.getStartRow(), paging.getEndRow());
			
			System.out.println("keyword : " + keyword);
			System.out.println("list : " + list);
		} else { // 키워드가 없을 경우 필터 검색
			listCount  = service.getListCountByFilter(filters);
			
			paging = new Paging(listCount, currentPage, limit, "plsearch");
			paging.calculator();
			
			list = service.selectPlantListByFilter(filters, paging.getStartRow(), paging.getEndRow());
			System.out.println("\n***filter search***");
			filters.forEach((key, value) -> {
	            System.out.println(key + " : " + value);
	        });
			System.out.println("list : " + list);
		}
		
		// 결과 전송
		RequestDispatcher view = null;
		view = request.getRequestDispatcher("views/search/searchResultView.jsp");
		
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);	
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("action", "plsearch");
		request.setAttribute("keyword", keyword);
		for(String key : filters.keySet()) {
			request.setAttribute(key, filters.get(key));
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
