package community.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommentService;
import community.model.vo.Comment;

/**
 * Servlet implementation class UpdateCommentServlet
 */
@WebServlet("/cmupdate")
public class UpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UpdateCommentServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		Comment comment = new Comment();
		
		comment.setBoardNo(Integer.parseInt(request.getParameter("bno")));
		comment.setCommentNo(Integer.parseInt(request.getParameter("cno")));
		comment.setCommentContent(request.getParameter("content"));
		
		int result = new CommentService().updateComment(comment);
		
		if(result > 0) {
			response.sendRedirect("/malant/bdetail?bno=" + comment.getBoardNo());
		}else {			
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");

			request.setAttribute("message",comment.getCommentContent() + " 댓글 수정 실패!!");
		
			view.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
