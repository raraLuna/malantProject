package community.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import common.Paging;
import community.model.dao.BoardDao;
import community.model.vo.Board;
import community.model.vo.CMBaordHashtag;
import community.model.vo.CMBoardPhoto;
import community.model.vo.CMHashtag;
import community.model.vo.Comment;

public class BoardService {
	private BoardDao bdao = new BoardDao();
	
	public BoardService() {
	}

	public ArrayList<Board> selectTop3Like() {
		Connection conn = getConnection();
		ArrayList<Board> list = bdao.selectTop3Like(conn);
		close(conn);

		return list;
	}

	public int addReadCount(int boardNo) {
		Connection conn = getConnection();
		int result = bdao.addReadCount(conn, boardNo);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);

		return result;
	}

	public int updateBoardLike(int boardNo) {
		Connection conn = getConnection();
		int result = bdao.updateBoardLike(conn, boardNo);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);

		return result;
	}

	public ArrayList<CMHashtag> selectTop5Hash() {
		Connection conn = getConnection();
		ArrayList<CMHashtag> list = bdao.selectTop5Hash(conn);
		close(conn);

		return list;
	}

	public ArrayList<Board> selectListSortByDate() {
		Connection conn = getConnection();
		ArrayList<Board> list = bdao.selectListSortByDate(conn);
		close(conn);

		return list;
	}

	public Board selectBoard(int boardNo) {
		Connection conn = getConnection();
		Board board = bdao.selectBoard(conn, boardNo);
		close(conn);
		return board;
	}

	public Comment selectBestComment(int boardNo) {
		Connection conn = getConnection();
		Comment comment = bdao.selectBestComment(conn, boardNo);
		close(conn);

		return comment;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = bdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Board> selectList() {
		Connection conn = getConnection();
		ArrayList<Board> list = bdao.selectList(conn);
		close(conn);
		return list;
	}

	public ArrayList<Board> selectMyList(String userno) {
		Connection conn = getConnection();
		ArrayList<Board> list = bdao.selectMyList(conn, userno);
		close(conn);
		return list;
	}

	public int deleteBoard(int boardNo) {
		Connection conn = getConnection();
		int result = bdao.deleteBoard(conn, boardNo);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateHashtag(Board board) {
		Connection conn = getConnection();
		int result = bdao.updateHashtag(conn, board);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertBoard(Board board) {
		Connection conn = getConnection();
		int result = bdao.insertBoard(conn, board);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int getMyListCount(String userno) {
		Connection conn = getConnection();
		int listCount = bdao.getMyListCount(conn, userno);
		close(conn);
		return listCount;
	}

	public int getSearchHashtagCount(String keyword) {
		Connection conn = getConnection();
		int listCount = bdao.getSearchHashtagCount(conn, keyword);
		close(conn);
		return listCount;
	}

	public ArrayList<Board> selectSearchHashtag(String keyword, Paging paging) {
		Connection conn = getConnection();
		ArrayList<Board> list = bdao.selectSearchHashtag(conn, keyword, paging);
		close(conn);
		return list;
	}

	public ArrayList<CMBoardPhoto> selectBoardPhotoList(int bnum) {
		Connection conn = getConnection();
		ArrayList<CMBoardPhoto> list = bdao.selectBoardPhotoList(conn, bnum);
		close(conn);
		return list;
	}

	public int insertBoardPhoto(CMBoardPhoto photo) {
		Connection conn = getConnection();
		int result = bdao.insertBoardPhoto(conn, photo);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateThumbnail(String thumbFileName) {
		Connection conn = getConnection();
		int result = bdao.updateThumbnail(conn, thumbFileName);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertBoardHashtag(CMBaordHashtag boardHashTag) {
		Connection conn = getConnection();
		int result = bdao.insertBoardHashtag(conn, boardHashTag);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertHashtag(String hashtag) {
		Connection conn = getConnection();
		int result = bdao.insertHashtag(conn, hashtag);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int selectRecentBoardNo() {
		Connection conn = getConnection();
		int boardNo = bdao.selectRecentBoardNo(conn);
		close(conn);
		return boardNo;
	}

	public CMHashtag selectHashtag(String hashtagContent) {
		Connection conn = getConnection();
		CMHashtag hashtagOk = bdao.selectHashtag(conn, hashtagContent);
		close(conn);
		return hashtagOk;
	}

	public int selectHashtagNo(String hashtagContent) {
		Connection conn = getConnection();
		int hashtagNo = bdao.selectHashtagNo(conn, hashtagContent);
		close(conn);
		return hashtagNo;
	}

	public ArrayList<Comment> selectCommentList(int boardNo) {
		Connection conn = getConnection();
		ArrayList<Comment> clist = bdao.selectCommentList(conn, boardNo);
		close(conn);
		return clist;
	}

	public int deleteComment(Comment comment) {
		Connection conn = getConnection();
		int result = bdao.deleteComment(conn, comment);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<CMBaordHashtag> selectBoardHashtagList(int boardNo) {
		Connection conn = getConnection();
		ArrayList<CMBaordHashtag> bhlist = bdao.selectBoardHashtagList(conn, boardNo);
		close(conn);
		return bhlist;
	}

	public int deleteHashtag(int hashtagNo) {
		Connection conn = getConnection();
		int result = bdao.deleteHashtag(conn, hashtagNo);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteBoardHashtag(CMBaordHashtag boardHashtag) {
		Connection conn = getConnection();
		int result = bdao.deleteBoardHashtag(conn, boardHashtag);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteBoardPhoto(CMBoardPhoto photo) {
		Connection conn = getConnection();
		int result = bdao.deleteBoardPhoto(conn, photo);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

}
