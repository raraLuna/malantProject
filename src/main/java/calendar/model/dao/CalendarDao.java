package calendar.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import calendar.model.vo.Calendar;
import static common.JDBCTemplate.*;

public class CalendarDao {
	
	public CalendarDao() {}

	public int insertNewCalendarTodo(Connection conn, Calendar calendar, String userNo, String diaryId) {
		int result = 0; 
		PreparedStatement pstmt = null;
		
		String query = "insert into my_calendar  "
					+ "(USER_NO, CALENDAR_DATE, DIARY_ID, CALENDAR_MEMO) "
					+ "values (?, SYSDATE , ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, calendar.getUserNo());
			pstmt.setString(2, calendar.getDiaryId());
			pstmt.setString(3, calendar.getCalendarMemo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateCalendarTodo(Connection conn, Calendar calendar, String userNo, Date calendarDate) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update my_calendar set calendar_memo = ? where user_no = ? and calendar_date = ?";

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, calendar.getCalendarMemo());
			pstmt.setString(2, calendar.getCalendarMemo());
			pstmt.setString(3, calendar.getCalendarMemo());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		
	return result;
	}

	public int deleteCalendarTodo(Connection conn, String userNo, Date calendarDate) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from calendar where user_no = ? and calendar_date = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNo);
			pstmt.setDate(2, calendarDate);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}



}
