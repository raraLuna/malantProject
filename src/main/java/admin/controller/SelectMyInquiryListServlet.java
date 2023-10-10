package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import admin.model.vo.Inquiry;
import common.Paging;

/**
 * Servlet implementation class SelectMyInquiryListServlet
 */
@WebServlet("/myqlist")
public class SelectMyInquiryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectMyInquiryListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userNo = request.getParameter("userno");
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		int limit = 10;
		
		AdminService aservice = new AdminService();
		int listCount = aservice.getMyInquiryListCount(userNo);
		
		Paging paging = new Paging(listCount, currentPage, limit, "myqlist");
		paging.calculator();
		
		ArrayList<Inquiry> list = aservice.selectMyInquiryList(userNo, paging.getStartRow(), paging.getEndRow());
		
		RequestDispatcher view = null;
//		if(list.size() > 0) {
			view = request.getRequestDispatcher("views/admin/myInquiryListView.jsp");
			
			request.setAttribute("list", list);
			request.setAttribute("paging", paging);	
			request.setAttribute("currentPage", currentPage);
//		} else {
//			view = request.getRequestDispatcher("views/common/error.jsp");
//			
//			request.setAttribute("message", currentPage + "페이지에 대한 목록 조회 실패");
//		}
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
