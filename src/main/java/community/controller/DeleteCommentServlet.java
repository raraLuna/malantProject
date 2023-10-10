package community.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommentService;

/**
 * Servlet implementation class DeleteCommentServlet
 */
@WebServlet("/cmdelete")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DeleteCommentServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 삭제
		String cnoParam = request.getParameter("cno");
	    String bnoParam = request.getParameter("bno");

	    if (cnoParam != null && bnoParam != null) {
	        try {
	            int commentNo = Integer.parseInt(cnoParam);
	            int boardNo = Integer.parseInt(bnoParam);

	            if (new CommentService().deleteComment(commentNo) > 0) {

	            	 response.sendRedirect(request.getContextPath() + "/bdetail?bno=" + boardNo);
	            } else {
	                RequestDispatcher view = request.getRequestDispatcher("view/common/error.jsp");
	                request.setAttribute("message", commentNo + "번 댓글 삭제 실패");
	                view.forward(request, response);
	            }
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }
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
