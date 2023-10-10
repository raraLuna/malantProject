package diary.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.Paging;
import diary.model.vo.Diary;
import diary.model.vo.MyDiaryPhotoes;

public class DiaryDao {

	public DiaryDao() {
	}

	public int getDiaryCount(Connection conn, String userNo) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from my_diary where user_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
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

	public ArrayList<Diary> selectList(Connection conn, String userNo, Paging paging) {
		ArrayList<Diary> list = new ArrayList<Diary>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select *  " + "from (select rownum rnum, diary_id, user_no,  "
				+ "diary_writing_date, diary_content  " 
				+ "from (select * from my_diary where user_no = ? "
				+ "order by diary_writing_date desc)) " 
				+ "where rnum >= ? and rnum <= ?";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, userNo);
			pstmt.setInt(2, paging.getStartRow());
			pstmt.setInt(3, paging.getEndRow());

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Diary diary = new Diary();

				diary.setUserNo(rset.getString("user_no"));
				diary.setDiaryId(rset.getInt("diary_id"));
				diary.setDiaryWritingDate(rset.getDate("diary_writing_date"));
				diary.setDiaryContent(rset.getString("diary_content"));

				list.add(diary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int insertNewDiray(Connection conn, Diary diary) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into my_diary  " + "(user_no, diary_id, diary_writing_date, diary_content )  "
				+ "values (?, my_diary_seq.nextval, default, ?)";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, diary.getUserNo());
			pstmt.setString(2, diary.getDiaryContent());
//			System.out.println(result);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateMyplant(Connection conn, Diary diary) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update my_diary " + "set diary_content = ? " + "where diary_id = ?";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, diary.getDiaryContent());
			pstmt.setInt(2, diary.getDiaryId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteDiary(Connection conn, int diaryId) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from my_diary where diary_id = ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, diaryId);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Diary selectDiary(Connection conn, int diaryId) {
		Diary diary = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from my_diary where diary_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, diaryId);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				diary = new Diary();

				diary.setUserNo(rset.getString("user_no"));
				diary.setDiaryId(rset.getInt("diary_id"));
				diary.setDiaryWritingDate(rset.getDate("diary_writing_date"));
				diary.setDiaryContent(rset.getString("diary_content"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return diary;

	}

	public ArrayList<MyDiaryPhotoes> selectDiaryPhotoes(Connection conn, int diaryId) {
		ArrayList<MyDiaryPhotoes> list = new ArrayList<MyDiaryPhotoes>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from my_diary_photoes where diary_id = ?";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, diaryId);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				MyDiaryPhotoes photo = new MyDiaryPhotoes();

				photo.setPhotoId(rset.getInt("photo_id"));
				photo.setDiaryId(rset.getInt("diary_id"));
				photo.setFileName(rset.getString("file_name"));

				list.add(photo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int selectDiaryId(Connection conn, String userNo) {
		int diaryId = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select MAX(diary_id) from my_diary where user_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				diaryId = rset.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return diaryId;
	}

	public int insertMyDiaryPhoto(Connection conn, int diaryId, String fname) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query1 = "select count(*) from my_diary_photoes";
		String query2 = "insert into my_diary_photoes  " + "(photo_id, diary_id, file_name)  "
				+ "values ((select MAX(photo_id) + 1 from my_diary_photoes), ? , ?)";
		String query3 = "insert into my_diary_photoes  " + "(photo_id, diary_id, file_name)  "
				+ "values (1, ? , ?)";

		try {
			pstmt = conn.prepareStatement(query1);
			rset = pstmt.executeQuery();	
			if(rset.next()) {
				int count = rset.getInt(1);
				if(count == 0) {
					pstmt = conn.prepareStatement(query3);
				}else if(count > 0) {
					pstmt = conn.prepareStatement(query2);
				}
			}
			
			pstmt.setInt(1, diaryId);
			pstmt.setString(2, fname);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	public int selectPhotoLastSeq(Connection conn) {
		int seqId = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query1 = "select count(*) from my_diary_photoes";
		String query2 = "select MAX(photo_id) from my_diary_photoes";

		try {
			pstmt = conn.prepareStatement(query1);
			rset = pstmt.executeQuery();
			int count = 0;
			if (rset.next()) {
				count = rset.getInt(1);
			}
			if (count > 0) {
				pstmt = conn.prepareStatement(query2);
				rset = pstmt.executeQuery();

				if (rset.next()) {
					seqId = rset.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return seqId;
	}

	public int deleteDiaryPhotoes(Connection conn, int diaryId) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from my_diary_photoes where diary_id = ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, diaryId);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
