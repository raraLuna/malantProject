package map.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import map.model.vo.Arboretum;
import static common.JDBCTemplate.close;
public class ArboretumDao {
	
	public ArboretumDao() {}
	
	public ArrayList<Arboretum> briefInfoList(Connection conn){
		ArrayList<Arboretum> list = new ArrayList<Arboretum>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//관리자를 제외한 일반회원만 전체 조회
		String query = "select arboretum_latitude, arboretum_longitude, arboretum_name, arboretum_address, arboretum_id, arboretum_tel"
					+ " from arboretum";
		try {
			pstmt = conn.prepareStatement(query);					
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Arboretum arboretum = new Arboretum();
				
				//결과매핑 : 컬럼값 꺼내서 필드에 옮기기
				arboretum.setArboretum_id(rset.getString("arboretum_id"));
				arboretum.setArboretum_name(rset.getString("arboretum_name"));
				arboretum.setArboretum_latitude(rset.getDouble("arboretum_latitude"));
				arboretum.setArboretum_longitude(rset.getDouble("arboretum_longitude"));
				arboretum.setArboretum_address(rset.getString("arboretum_address"));
				arboretum.setArboretum_tel(rset.getString("arboretum_tel"));
				
				list.add(arboretum);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	
	public Arboretum selectInformation(Connection conn, String arboretumId) {
		Arboretum arboretum = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from arboretum where arboretum_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, arboretumId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				arboretum = new Arboretum();
				
				arboretum.setArboretum_id(rset.getString("arboretum_id"));
				arboretum.setArboretum_name(rset.getString("arboretum_name"));
				arboretum.setArboretum_address(rset.getString("arboretum_address"));
				arboretum.setArboretum_homepage(rset.getString("arboretum_homepage"));
				arboretum.setArboretum_tel(rset.getString("arboretum_tel"));
				arboretum.setEntrance_fee_yn(rset.getString("entrance_fee_yn"));
				arboretum.setFee_adult(rset.getInt("fee_adult"));
				arboretum.setFee_teenage(rset.getInt("fee_teenage"));
				arboretum.setFee_child(rset.getInt("fee_child"));
				arboretum.setFee_disabled(rset.getInt("fee_disabled"));
				arboretum.setFee_etc(rset.getString("fee_etc"));
				arboretum.setOpen_days(rset.getString("open_days"));
				arboretum.setClosed_days(rset.getString("closed_days"));
				arboretum.setWith_pet_yn(rset.getString("with_pet_yn"));
				arboretum.setWith_guidedog_yn(rset.getString("with_guidedog_yn"));
				arboretum.setEdu_program_yn(rset.getString("edu_program_yn"));
				arboretum.setEdu_program_name(rset.getString("edu_program_name"));
				arboretum.setEdu_pro_reservation(rset.getString("edu_pro_reservation"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return arboretum;
	}

	public ArrayList<Arboretum> searchList(Connection conn, String search) {
		ArrayList<Arboretum> list = new ArrayList<Arboretum>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//관리자를 제외한 일반회원만 전체 조회
		String query = "select arboretum_latitude, arboretum_longitude, arboretum_name, arboretum_address, arboretum_id, arboretum_tel"
					+ " from arboretum where arboretum_address like ? or arboretum_name like ?";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Arboretum arboretum = new Arboretum();
				
				//결과매핑 : 컬럼값 꺼내서 필드에 옮기기
				arboretum.setArboretum_id(rset.getString("arboretum_id"));
				arboretum.setArboretum_name(rset.getString("arboretum_name"));
				arboretum.setArboretum_latitude(rset.getDouble("arboretum_latitude"));
				arboretum.setArboretum_longitude(rset.getDouble("arboretum_longitude"));
				arboretum.setArboretum_address(rset.getString("arboretum_address"));
				arboretum.setArboretum_tel(rset.getString("arboretum_tel"));
				
				list.add(arboretum);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
}
