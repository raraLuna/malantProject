package admin.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import admin.model.dao.AdminDao;
import admin.model.vo.Inquiry;

public class AdminService {
	AdminDao adao = new AdminDao();

	public int insertInquiry(Inquiry inquiry) {
		Connection conn = getConnection();
		int result = adao.insertInquiry(conn, inquiry);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int getMyInquiryListCount(String userNo) {
		Connection conn = getConnection();
		int listCount = adao.getMyInquiryListCount(conn, userNo);
		close(conn);
		return listCount;
	}

	public ArrayList<Inquiry> selectMyInquiryList(String userNo, int startRow, int endRow) {
		Connection conn = getConnection();
		ArrayList<Inquiry> list = adao.selectMyInquiryList(conn, userNo, startRow, endRow);
		close(conn);
		return list;
	}

	
	
	
}
