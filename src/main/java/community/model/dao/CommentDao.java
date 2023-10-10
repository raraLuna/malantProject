package community.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import community.model.vo.Comment;

public class CommentDao {

	public ArrayList<Comment> selectCommentList(Connection conn, int bnum) {
		ArrayList<Comment> list = new ArrayList<Comment>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select *  "
						+ "from cm_comment  "
						+ "where board_no = ? "
						+ "order by comment_no desc";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, bnum);

			rset = pstmt.executeQuery();

			while (rset.next()) {

				Comment comment = new Comment();
				comment.setBoardNo(rset.getInt("BOARD_NO"));
				comment.setCommentNo(rset.getInt("COMMENT_NO"));
				comment.setUserNo(rset.getString("USER_NO"));
				comment.setNickname(rset.getString("NICKNAME"));
				comment.setCommentContent(rset.getString("COMMENT_CONTENT"));
				comment.setCommentDate(rset.getDate("COMMENT_DATE"));

				list.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int insertComment(Connection conn, Comment comment) {
		int result = 0;

		PreparedStatement pstmt = null;
		// System.out.println("comment : "+ comment.toString());

		String query = "insert into cm_comment values (?, (select max(comment_no) +1 from cm_comment), "
					+ " ?, ?, ?, default)";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, comment.getBoardNo());
			pstmt.setString(2, comment.getUserNo());
			pstmt.setString(3, comment.getNickname());
			pstmt.setString(4, comment.getCommentContent());

			result = pstmt.executeUpdate();
			System.out.println("들어간다 댓글");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteComment(Connection conn, int commentNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from cm_comment where comment_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateComment(Connection conn, Comment comment) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update cm_comment  "
					 + "set comment_content = ? "
					 + "where board_no = ? and comment_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, comment.getCommentContent());
			pstmt.setInt(2, comment.getBoardNo());
			pstmt.setInt(3, comment.getCommentNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
