package myplant.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;
import myplant.model.service.MyplantService;
import myplant.model.vo.Myplant;

/**
 * Servlet implementation class MyplantListServlet
 */
@WebServlet("/mplist")
public class MyplantListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyplantListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// myplant list 조회서블릿
		String action = request.getParameter("action");
		String keyword = request.getParameter("keyword");

		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		//전송온 목록 갯수가 있다면 추출함
		int limit = 8;
		MyplantService mpservice = new MyplantService();
		int listCount = mpservice.getListCount(keyword);


		
		//뷰 페이지에서 사용할 페이징 관련 값 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "mplist");
		paging.calculator();
		
		//모델 서비스로 해당 페이지에 출력할 게시글만 조회해 옴 
		ArrayList<Myplant> list = mpservice.selectMyplantList(paging, keyword);
		
		//받은 결과에 따라 성공 또는 실패 페이지 내보내기 
		RequestDispatcher view = null;
		if(list.size() >= 0) {
			view = request.getRequestDispatcher("views/diary/myplantMain.jsp");

			
			//System.out.println("listservlet result : " + userNo);
			
			request.setAttribute("list", list);
			request.setAttribute("paging", paging);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("user_no", keyword);
			request.setAttribute("action", action);
			request.setAttribute("keyword", keyword);
			
			
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			
			request.setAttribute("massage", "나의 식물 목록 로딩 실패");
				
		}
		view.forward(request, response);
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
