package diary.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;
import diary.model.service.DiaryService;
import diary.model.vo.Diary;
import diary.model.vo.MyDiaryPhotoes;

/**
 * Servlet implementation class DiaryListServlet
 */
@WebServlet("/dlist")
public class DiaryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiaryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//다이어리 목록 출력 서블렛
		String action = request.getParameter("action");
		String keyword = request.getParameter("keyword");
		
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		//한 페이지 당 출력할 목록 개수 지정
		int limit = 5;
		
		//조회용 모델 측 서비스 객체 생성
		DiaryService dservice = new DiaryService();
		int listCount = dservice.getDiaryCount(keyword);
		
		//뷰 페이지에서 사용할 페이징 관련 값 계산 처리
		Paging paging = new Paging(listCount, currentPage, limit, "dlist");
		paging.calculator();
		
		ArrayList<Diary> list = dservice.selectList(keyword, paging);	
		
		HashMap<Integer, ArrayList<MyDiaryPhotoes>> photoes = new HashMap<Integer, ArrayList<MyDiaryPhotoes>>();
		for(Diary d : list) {
			ArrayList<MyDiaryPhotoes> photoList = dservice.selectDiaryPhotoes(d.getDiaryId());
			photoes.put(d.getDiaryId(), photoList);
		}
	
		//받은 결과에 따라 성공 또는 실패 페이지 내보내기
		RequestDispatcher view = null;
		if(list.size() >= 0) {
			view = request.getRequestDispatcher("views/diary/diaryList.jsp");
			
			request.setAttribute("list", list);
			request.setAttribute("paging", paging);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("photoes", photoes);
			request.setAttribute("action", action);
			request.setAttribute("keyword", keyword);
			
			
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			
			request.setAttribute("message", "다이어리 목록 조회 실패");
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
