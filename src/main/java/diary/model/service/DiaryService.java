package diary.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import common.Paging;
import diary.model.dao.DiaryDao;
import diary.model.vo.Diary;
import diary.model.vo.MyDiaryPhotoes;

public class DiaryService {
	
	private DiaryDao ddao = new DiaryDao();
	
	public DiaryService () {}

	
	public int getDiaryCount(String userNo) {
		Connection conn = getConnection();
		int listcount = ddao.getDiaryCount(conn, userNo);
		close(conn);
		return listcount;
	}


	public ArrayList<Diary> selectList(String userNo, Paging paging) {
		Connection conn = getConnection();
		ArrayList<Diary> list = ddao.selectList(conn, userNo, paging);
		close(conn);
		return list;
	}

	public int insertNewDiray(Diary diary) {
		Connection conn = getConnection();
		int result = ddao.insertNewDiray(conn, diary);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	
	}

	public int updateDiary(Diary diary) {
		Connection conn = getConnection();
		int result = ddao.updateMyplant(conn, diary);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteDiary(int diaryId) {
		Connection conn = getConnection();
		int result = ddao.deleteDiary(conn, diaryId);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Diary selectDiary(int diaryId) {
		Connection conn = getConnection();
		Diary diary = ddao.selectDiary(conn, diaryId);
		close(conn);
		return diary;
	}


	public ArrayList<MyDiaryPhotoes> selectDiaryPhotoes(int diaryId) {
		Connection conn = getConnection();
		ArrayList<MyDiaryPhotoes> list = ddao.selectDiaryPhotoes(conn, diaryId);
		close(conn);
		return list;
	}


	public int selectDiaryId(String userNo) {
		Connection conn = getConnection();
		int diaryId = ddao.selectDiaryId(conn, userNo);
		close(conn);
		return diaryId;
	}


	public int insertMyDiaryPhoto(int diaryId, String fname) {
		Connection conn = getConnection();
		int result = ddao.insertMyDiaryPhoto(conn, diaryId, fname);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
		
	}


	public int selectPhotoLastSeq() {
		Connection conn = getConnection();
		int seqId = ddao.selectPhotoLastSeq(conn);
		close(conn);
		return seqId;
	}


	public int deleteDiaryPhotoes(int diaryId) {
		Connection conn = getConnection();
		int result = ddao.deleteDiaryPhotoes(conn, diaryId);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}




	
}
