package notice.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import common.Paging;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

public class NoticeService {
	private NoticeDao ndao = new NoticeDao();

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = ndao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Notice> selectNoticeTitle(Paging paging) {
		Connection conn = getConnection();
		ArrayList<Notice> list = ndao.selectNoticeTitle(conn, paging);
		close(conn);
		return list;
	}

	public void addReadCount(int noticeNo) {
		Connection conn = getConnection();
		int result = ndao.addReadCount(conn, noticeNo);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}		
		close(conn);		
	}

	public Notice selectOne(int noticeNo) {
		Connection conn = getConnection();
		Notice notice = ndao.selectOne(conn, noticeNo);
		close(conn);
		return notice;
	}

	public int deleteNotice(int noticeNo) {
		Connection conn = getConnection();
		int result = ndao.deleteBoard(conn, noticeNo);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertNotice(Notice notice) {
		Connection conn = getConnection();
		int result = ndao.insertNotice(conn, notice);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}




}
