package community.model.service;

import static common.JDBCTemplate.*;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import community.model.dao.CommentDao;
import community.model.vo.Comment;

public class CommentService {

	private CommentDao cdao = new CommentDao();

	public CommentService() {
	}

	public ArrayList<Comment> selectCommentList(int bnum) {
		Connection conn = getConnection();
		ArrayList<Comment> list = cdao.selectCommentList(conn, bnum);
		close(conn);

		return list;
	}

	public int insertComment(Comment comment) {
		Connection conn = getConnection();
		int result = cdao.insertComment(conn, comment);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteComment(int commentNo) {
		Connection conn = getConnection();
		int result = cdao.deleteComment(conn, commentNo);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateComment(Comment comment) {
		Connection conn = getConnection();
		int result = cdao.updateComment(conn, comment);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

}
