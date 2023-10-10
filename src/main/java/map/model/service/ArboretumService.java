package map.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import map.model.dao.ArboretumDao;
import map.model.vo.Arboretum;

public class ArboretumService {
	
	private ArboretumDao adao = new ArboretumDao();
	
	public ArboretumService() {}
	
	public ArrayList<Arboretum> briefInfoList() {
		Connection conn = getConnection();
		ArrayList<Arboretum> list = adao.briefInfoList(conn);
		close(conn);
		return list;
	}

	public Arboretum selectDetailInformation(String arboretumId) {
		Connection conn = getConnection();
		Arboretum arboretum = adao.selectInformation(conn, arboretumId);
		close(conn);
		return arboretum;
	}

	public ArrayList<Arboretum> searchList(String search) {
		Connection conn = getConnection();
		ArrayList<Arboretum> list = adao.searchList(conn, search);
		close(conn);
		return list;
	}
}
