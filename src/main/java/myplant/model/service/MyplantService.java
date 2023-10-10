package myplant.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import common.Paging;
import myplant.model.dao.MyplantDao;
import myplant.model.vo.Myplant;

public class MyplantService {
	
	private MyplantDao mpdao = new MyplantDao();
	
	public MyplantService(){}
	

	public int getListCount(String userNo) {
		Connection conn = getConnection();
		int listCount = mpdao.getListCount(conn, userNo);
		close(conn);
		return listCount;
	}

	public ArrayList<Myplant> selectMyplantList(Paging paging, String userNo) {
		Connection conn = getConnection();
		ArrayList<Myplant> list = mpdao.selectList(conn, paging, userNo);
		close(conn);
		return list;
	}

	public int updateMyplant(Myplant myplant) {
		Connection conn = getConnection();
		int result = mpdao.updateMyplant(conn, myplant);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int insertMyplantInformation(Myplant myplant) {
		Connection conn = getConnection();
		int result = mpdao.insertMyplantInformation(conn, myplant);
		if(result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteMyplant(String userNo, String myplantId) {
		Connection conn = getConnection();
		int result = mpdao.deleteMyplant(conn, userNo, myplantId);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	public Myplant selectMyplant(String userNo) {
		Connection conn = getConnection();
		Myplant myplant = mpdao.selectMyplant(conn, userNo);
		close(conn);
		return myplant;
	}

	public Myplant selectMyplantInfo(String userNo, String myplantId) {
		Connection conn = getConnection();
		Myplant myplant = mpdao.selectMyplantInfo(conn, userNo, myplantId);
		close(conn);
		return myplant;
	}


	public Myplant selectRecentlyAdd(String userNo) {
		Connection conn = getConnection();
		Myplant myplant = mpdao.selectRecentlyAdd(conn, userNo);
		close(conn);
		return myplant;
	}


	
}
