package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import admin.model.vo.Inquiry;

/**
 * Servlet implementation class InsertInquiryServlet
 */
@WebServlet("/qinsert")
public class InsertInquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertInquiryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userNo = request.getParameter("user_no");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Inquiry inquiry = new Inquiry();
		inquiry.setInquirerNo(userNo);
		inquiry.setInquiryTitle(title);
		inquiry.setInquiryContent(content);
		
		int result = new AdminService().insertInquiry(inquiry);
		
		if(result > 0) {
			response.sendRedirect("/malant/myqlist?userno=" + userNo + "&action=myqlist"); // 내 문의글 페이지로 이동
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "문의 등록 실패");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
