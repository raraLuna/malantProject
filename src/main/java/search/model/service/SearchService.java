package search.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import search.model.dao.SearchDao;
import search.model.vo.Plant;

public class SearchService {
	//DI(Dependency Injection : 의존성 주입)
	private SearchDao sdao = new SearchDao();
	
	public ArrayList<Plant> selectPlantList(String keyword, int startRow, int endRow) {
		Connection conn = getConnection();
		ArrayList<Plant> list = sdao.selectPlantList(conn, keyword, startRow, endRow);
		close(conn);
		return list;
	}

	public Plant selectPlant(int plantNo) {
		Connection conn = getConnection();
		Plant plant = sdao.selectPlant(conn, plantNo);
		close(conn);
		return plant;
	}

	public int getListCount(String keyword) {
		Connection conn = getConnection();
		int listCount = sdao.getListCount(conn, keyword);
		close(conn);
		return listCount;
	}
	
	public int insertPlant(Plant plant) {
		Connection conn = getConnection();
		int result = sdao.insertPlant(conn, plant);
		close(conn);
		return result;
	}

	public int getListCountByFilter(Map<String, String> filters) {
		Connection conn = getConnection();
		int listCount = sdao.getListCountByFilter(conn, filters);
		close(conn);
		return listCount;
	}

	public ArrayList<Plant> selectPlantListByFilter(Map<String, String> filters, int startRow, int endRow) {
		Connection conn = getConnection();
		ArrayList<Plant> list = sdao.selectPlantListByFilter(conn, filters, startRow, endRow);
		close(conn);
		return list;
	}

}
