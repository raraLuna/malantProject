package calendar.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.model.service.CalendarService;
import calendar.model.vo.Calendar;

/**
 * Servlet implementation class CalendarManagingServlet
 */
@WebServlet("/CalendarManagingServlet")
public class CalendarDeleteTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalendarDeleteTodoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		Calendar calendar = new Calendar();
		
		String userNo = request.getParameter("user_no");
		Date calendarDate = Date.valueOf(request.getParameter("calendar_date"));

		
		calendar.setUserNo(request.getParameter("user_no"));
		calendar.setCalendarDate(Date.valueOf(request.getParameter("calendar_date")));
		
		calendar.setCalendarMemo(request.getParameter("calendar_memo"));
		
		if (new CalendarService().deleteCalendarTodo(userNo, calendarDate) > 0) {
			
			response.sendRedirect("/malant/views/diary/calendar.jsp");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "캘린더 일정 삭제 실패");
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
