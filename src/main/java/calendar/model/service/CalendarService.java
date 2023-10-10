package calendar.model.service;

import java.sql.Connection;
import java.sql.Date;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;


import myplant.model.dao.MyplantDao;
import myplant.model.vo.Myplant;


import calendar.model.dao.CalendarDao;
import calendar.model.vo.Calendar;

public class CalendarService {
	
	private CalendarDao cdao = new CalendarDao();	
		
	public int insertNewCalendarTodo(Calendar calendar, String userNo, String diaryId) {
		Connection conn = getConnection();
		int result = cdao.insertNewCalendarTodo(conn, calendar, userNo, diaryId);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateCalendarTodo(Calendar calendar, String userNo, Date calendarDate) {
		Connection conn = getConnection();
		int result = cdao.updateCalendarTodo(conn, calendar, userNo, calendarDate);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;

	}

	public int deleteCalendarTodo(String userNo, Date calendarDate) {
		Connection conn = getConnection();
		int result = cdao.deleteCalendarTodo(conn, userNo, calendarDate);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}






}	
