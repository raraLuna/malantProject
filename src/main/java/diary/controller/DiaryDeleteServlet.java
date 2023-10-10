package diary.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diary.model.service.DiaryService;
import diary.model.vo.MyDiaryPhotoes;

/**
 * Servlet implementation class DiaryDeleteServlet
 */
@WebServlet("/ddelete")
public class DiaryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DiaryDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		int diaryId = Integer.parseInt(request.getParameter("diaryId"));
		String userNo = request.getParameter("userNo");

		DiaryService dservice = new DiaryService();
		
		ArrayList<MyDiaryPhotoes> list = dservice.selectDiaryPhotoes(diaryId);
		if(list.size() > 0) {
			String savePath = request.getSession().getServletContext()
					.getRealPath("/resources/diary/diary_upimages");
			for(MyDiaryPhotoes p : list) {
				new File(savePath + "\\" + p.getFileName()).delete();
			}
		}
		
		dservice.deleteDiaryPhotoes(diaryId);
		int result = dservice.deleteDiary(diaryId); 
		
		if (result > 0) {

			response.sendRedirect("/malant/dlist?action=user_no&keyword=" + userNo + "&page=1");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "일기 삭제 실패");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
