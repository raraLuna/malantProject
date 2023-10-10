package calendar.controller;

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
 * Servlet implementation class CalendarUpadateTodoServlet
 */
@WebServlet("/cupdate")
public class CalendarUpadateTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarUpadateTodoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  request.setCharacterEncoding("utf-8");
		   
	      Calendar calendar = new Calendar();
	      
	      String userNo = request.getParameter("user_no");
		  Date calendarDate = Date.valueOf(request.getParameter("calendar_date"));
	      
	      calendar.setUserNo(request.getParameter("user_no"));
	      calendar.setCalendarDate(Date.valueOf(request.getParameter("calendar_date")));
	      calendar.setDiaryId(request.getParameter("diary_id")); 
	      calendar.setCalendarMemo(request.getParameter("calendar_memo"));

	      
	      //모델 서비스로 전달하고 결과 받기
	      int result = new CalendarService().updateCalendarTodo(calendar, userNo, calendarDate);
	      
	      //성공/실패 페이지 내보내기
	      if (result > 0) {
	         response.sendRedirect("/malant/cnew");
	      }else {
	         RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
	         request.setAttribute("message", "새 일정 등록 실패");
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
