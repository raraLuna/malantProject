package community.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;
import community.model.service.BoardService;
import community.model.vo.Board;

/**
 * Servlet implementation class MyBoardListServlet
 */
@WebServlet("/myblist")
public class MyCommunityListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public MyCommunityListServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userno = request.getParameter("userno");

		int limit = 10;

		BoardService bservice = new BoardService();

		int listCount = bservice.getMyListCount(userno);


		ArrayList<Board> myblist = bservice.selectMyList(userno);

		RequestDispatcher view = null;
		if(myblist.size() > 0) {
			view = request.getRequestDispatcher("views/board/myBoardList.jsp");
			/* request.setAttribute("userno", userno); */

			request.setAttribute("myblist", myblist);
			request.setAttribute("userno", userno);
			
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", userno + "가 등록한 게시글이 없습니다");
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
