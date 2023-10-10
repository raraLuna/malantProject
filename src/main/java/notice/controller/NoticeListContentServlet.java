package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListContentServlet
 */
@WebServlet("/ncontentlist")
public class NoticeListContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public NoticeListContentServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		NoticeService nservice = new NoticeService();

		nservice.addReadCount(noticeNo);

		Notice notice = nservice.selectOne(noticeNo);
		
		RequestDispatcher view = null;
		if (notice != null) {
			view = request.getRequestDispatcher("views/notice/noticeDetailList.jsp");
			request.setAttribute("notice", noticeNo);
			view.forward(request, response);
		
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", noticeNo + "번 공지글 상세조회 실패");
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
