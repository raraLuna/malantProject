package store.main.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import store.main.model.dao.MainDao;
import store.main.model.vo.*;

public class MainService {
	private MainDao mdao = new MainDao();
	
	public MainService() {}
	
	public ArrayList<MainContent> selectBanner(){
		Connection conn = getConnection();
		ArrayList<MainContent> list = mdao.selectBanner(conn);
		close(conn);
		return list;
    }
	
	public ArrayList<MainContent> selectMainProductList() {
		Connection conn = getConnection();
		ArrayList<MainContent> list = mdao.selectMainProductList(conn);
		close(conn);
		return list;
	}

	public int getBannerViewCount() {
		int result=0;
		
		return result;
	}
}
