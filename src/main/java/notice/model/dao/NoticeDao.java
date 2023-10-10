package notice.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.Paging;
import notice.model.vo.Notice;

public class NoticeDao {

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from notice";

		try {
			stmt = conn.createStatement();

			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return listCount;
	}

	public ArrayList<Notice> selectNoticeTitle(Connection conn, Paging paging) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select NOTICE_NO, ADMIN_NO, NOTICE_TYPE, TITLE, CONTENT, THUMBNAIL, CONTENT_IMG, PRIORITY, POST_DATE, START_DATE, "
				+ "				                         END_DATE, VIEWCOUNT, ROWNUM " + "from NOTICE "
				+ "where NOTICE_TYPE = 'NOTICE' OR NOTICE_TYPE = 'EVENT' AND ROWNUM between ? and ? "
				+ "order by POST_DATE";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, paging.getStartRow());
			pstmt.setInt(2, paging.getEndRow());

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Notice notice = new Notice();

				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setAdminNo(rset.getString("ADMIN_NO"));
				notice.setNoticeType(rset.getString("NOTICE_TYPE"));
				notice.setTitle(rset.getString("TITLE"));
				notice.setContent(rset.getString("CONTENT"));
				notice.setThumbnail(rset.getString("THUMBNAIL"));
				notice.setContentImage(rset.getString("CONTENT_IMG"));
				notice.setPriority(rset.getInt("PRIORITY"));
				notice.setPostDate(rset.getDate("POST_DATE"));
				notice.setEventStart(rset.getDate("START_DATE"));
				notice.setEventEnd(rset.getDate("END_DATE"));
				notice.setViewcount(rset.getInt("VIEWCOUNT"));

				list.add(notice);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int addReadCount(Connection conn, int noticeNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update notice " + "set viewcount = viewcount + 1 " + "where notice_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Notice selectOne(Connection conn, int noticeNo) {
		Notice notice = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from notice where notice_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				notice = new Notice();

				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setAdminNo(rset.getString("ADMIN_NO"));
				notice.setNoticeType(rset.getString("NOTICE_TYPE"));
				notice.setTitle(rset.getString("TITLE"));
				notice.setContent(rset.getString("CONTENT"));
				notice.setThumbnail(rset.getString("THUMBNAIL"));
				notice.setContentImage(rset.getString("CONTENT_IMG"));
				notice.setPriority(rset.getInt("PRIORITY"));
				notice.setPostDate(rset.getDate("POST_DATE"));
				notice.setEventStart(rset.getDate("START_DATE"));
				notice.setEventEnd(rset.getDate("END_DATE"));
				notice.setViewcount(rset.getInt("VIEWCOUNT"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return notice;
	}

	public int deleteBoard(Connection conn, int noticeNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from notice where notice_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertNotice(Connection conn, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into NOTICE values (NOTICE_SEQ.NEXTVAL, ?, ?, ?, ?, null, ?,  default,  default, ?, ?, default)";

		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, notice.getAdminNo());
			pstmt.setString(2, notice.getNoticeType());
			pstmt.setString(3, notice.getTitle());
			pstmt.setString(4, notice.getContent());
			pstmt.setString(5, "/malant/resources/notice/notice_content_img/" + notice.getContentImage());
			pstmt.setDate(6, notice.getEventStart());
			pstmt.setDate(7, notice.getBannerEnd());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}



	

}
