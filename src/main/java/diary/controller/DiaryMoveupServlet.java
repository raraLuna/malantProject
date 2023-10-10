package diary.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diary.model.service.DiaryService;
import diary.model.vo.Diary;
import diary.model.vo.MyDiaryPhotoes;

/**
 * Servlet implementation class DiaryMoveupServlet
 */
@WebServlet("/dmoveup")
public class DiaryMoveupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiaryMoveupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int diaryId = Integer.parseInt(request.getParameter("diaryId"));
		int currentPage = Integer.parseInt(request.getParameter("page"));
		
		DiaryService dservice = new DiaryService();
		Diary diary = dservice.selectDiary(diaryId);
		ArrayList<MyDiaryPhotoes> list = dservice.selectDiaryPhotoes(diaryId);
		
		
		RequestDispatcher view = null;
		if(diary != null) {
			view = request.getRequestDispatcher("views/diary/diaryModify.jsp");
			request.setAttribute("diary", diary);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("list", list);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "일기 수정 페이지로 이동 실패");
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
