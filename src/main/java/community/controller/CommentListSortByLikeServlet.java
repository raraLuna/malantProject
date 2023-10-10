package community.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import community.model.service.BoardService;
import community.model.service.CommentService;
import community.model.vo.Board;
import community.model.vo.Comment;

/**
 * Servlet implementation class CommentListSortByLikeServlet
 */
@WebServlet("/cmllist")
public class CommentListSortByLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public CommentListSortByLikeServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bno"));
		BoardService bservice = new BoardService();
		ArrayList<Comment> clist = new CommentService().selectCommentList(bnum);
		
		Board board = bservice.selectBoard(bnum);
		
		RequestDispatcher view = null;
		
		if(board != null) {
			view = request.getRequestDispatcher("views/board/boardDetailList.jsp");
			request.setAttribute("clist", clist);
			request.setAttribute("board", board);
			
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			
			request.setAttribute("message", bnum + "번 글 상세조회 실패!");
		}
		
		view.forward(request, response);
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
