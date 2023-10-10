package community.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.BoardService;
import community.model.service.CommentService;
import community.model.vo.Board;
import community.model.vo.CMBoardPhoto;
import community.model.vo.Comment;

/**
 * Servlet implementation class BoardListDetailServlet
 */
@WebServlet("/bdetail")
public class CommunityDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int bnum = Integer.parseInt(request.getParameter("bno"));
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		BoardService bservice = new BoardService();
		Board board = bservice.selectBoard(bnum);
		ArrayList<Comment> clist = new CommentService().selectCommentList(bnum);
		ArrayList<CMBoardPhoto> photoList = bservice.selectBoardPhotoList(bnum);
		
		bservice.addReadCount(bnum);
		
		RequestDispatcher view = null;
		
		if(board != null) {
			view = request.getRequestDispatcher("views/board/boardDetailList.jsp");
			request.setAttribute("clist", clist);
			request.setAttribute("board", board);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("photoList", photoList);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", bnum + "번 글 상세조회 실패!");
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
