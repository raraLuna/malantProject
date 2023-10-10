package admin.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import admin.model.vo.Inquiry;

public class AdminDao {

	public int insertInquiry(Connection conn, Inquiry inquiry) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into customer_service"
				+ "values (customer_service_seq, ?, sysdate, ?, ?, default, default, default, default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inquiry.getInquirerNo());
			pstmt.setString(2, inquiry.getInquiryTitle());
			pstmt.setString(3, inquiry.getInquiryContent());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getMyInquiryListCount(Connection conn, String userNo) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from customer_service where inquirer_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		
		return listCount;
	}

	public ArrayList<Inquiry> selectMyInquiryList(Connection conn, String userNo, int startRow, int endRow) {
		ArrayList<Inquiry> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * "
				+ "from (select rownum rnum, customer_service.* "
				+ "from customer_service "
				+ "where inquirer_no = ?) "
				+ "where rnum >= ? and rnum <= ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			int cnt = 1;
			pstmt.setString(cnt++, userNo);
			pstmt.setInt(cnt++, startRow);
			pstmt.setInt(cnt++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Inquiry inquiry = new Inquiry();
				
				inquiry.setInquiryNo(rset.getInt("inquiry_no"));
				inquiry.setInquirerNo(rset.getString("inquirer_no"));
				inquiry.setInquiryDate(rset.getDate("inquiry_date"));
				inquiry.setInquiryTitle(rset.getString("inquiry_title"));
				inquiry.setInquiryContent(rset.getString("inquiry_content"));
				inquiry.setReplierNo(rset.getString("replier_no"));
				inquiry.setReplyContent(rset.getString("reply_content"));
				inquiry.setStatus(rset.getString("status"));
				inquiry.setCompletionDate(rset.getDate("completion_date"));
				
				list.add(inquiry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

}
