package community.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.BoardService;
import community.model.vo.CMBaordHashtag;
import community.model.vo.CMBoardPhoto;
import community.model.vo.Comment;

/**
 * Servlet implementation class DeleteBoardServlet
 */
@WebServlet("/bdelete")
public class DeleteCommunityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCommunityServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardService bservice = new BoardService();
		RequestDispatcher view = null;
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		ArrayList<CMBoardPhoto> list = bservice.selectBoardPhotoList(boardNo);
		
		for(CMBoardPhoto photo : list) {
			int result = bservice.deleteBoardPhoto(photo);
			
			if(result > 0) {
				String fileName = photo.getFilename();
				if(fileName != null) {
					String savePath = request.getSession().getServletContext()
							.getRealPath("/resources/board/images");
					new File(savePath + "\\" + fileName).delete();
					System.out.println(fileName + " 파일 삭제 성공");
				}
			} else {
				view = request.getRequestDispatcher("views/common/error.jsp");
				request.setAttribute("message", photo.getBoardNo() + "번 게시글 " + photo.getPhotoNo() + "번 사진 삭제 실패!");
				view.forward(request, response);
			}
		}
		
		ArrayList<Comment> clist = bservice.selectCommentList(boardNo);
		
		if(clist.size() > 0) {
			for(Comment comment : clist) {
				int result = bservice.deleteComment(comment);
				
				if(result > 0) {
					System.out.println(comment + " 삭제 성공");
				} else {
					view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", comment.getBoardNo() + "번 게시글 " + comment.getCommentNo() + "번 댓글 삭제 실패!");
					view.forward(request, response);
				}
			}
		}
		
		
		ArrayList<CMBaordHashtag> bhlist = bservice.selectBoardHashtagList(boardNo);
		
		if(bhlist.size() > 0) {
			for(CMBaordHashtag boardHashtag : bhlist) {
				int result = bservice.deleteBoardHashtag(boardHashtag);
				
				if(result > 0) {
					System.out.println(boardHashtag.getBoardNo() + "번 게시글의 " + boardHashtag.getHashtagNo() + "번 해시태그 삭제 성공");
				} else {
					view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", boardHashtag.getBoardNo() + "번 게시글의 " + boardHashtag.getHashtagNo() + "번 해시태그 삭제 실패!");
					view.forward(request, response);
				}
			}
		}
		
		
		int result = bservice.deleteBoard(boardNo);
		if(result > 0) {
			response.sendRedirect("bdlist");
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", boardNo + "번 게시글 삭제 실패!");
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
