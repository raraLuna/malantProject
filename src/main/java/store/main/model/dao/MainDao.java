package store.main.model.dao;

import java.sql.*;
import java.util.ArrayList;

import store.main.model.vo.*;
import static common.JDBCTemplate.*;

public class MainDao {
	public MainDao(){
		
	}
	
	public ArrayList<MainContent> selectBanner(Connection conn){
		
		ArrayList<MainContent> list = new ArrayList<MainContent>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * "
				+ "FROM NOTICE "
				+ "WHERE notice_type = 'BANNER' and START_DATE <= SYSDATE AND END_DATE >= SYSDATE ";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				MainContent banner = new MainContent();
				
				banner.setBannerImage(rset.getString("THUMBNAIL"));
				banner.setBannerLink(rset.getString("CONTENT"));
				banner.setBannerPrority(rset.getInt("PRIORITY"));
				
				list.add(banner);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(stmt);
		}
		return list;	
    }
	
	
	public ArrayList<MainContent> selectMainProductList(Connection conn) {
		
		ArrayList<MainContent> list = new ArrayList<MainContent>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT PRODUCT_ID, PRODUCT_NAME, PRICE, PRODUCT_THUMBNAIL_IMG, SELLER_NO "
				+ "FROM ST_PRODUCT "
				+ "WHERE EXPOSURE_YN='Y'";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				MainContent product = new MainContent();
				
				product.setProductId(rset.getInt("PRODUCT_ID"));
				product.setProductName(rset.getString("PRODUCT_NAME"));
				product.setPrice(rset.getInt("PRICE"));
				product.setProductThumbnail(rset.getString("PRODUCT_THUMBNAIL_IMG"));
				product.setSellerNo(rset.getString("SELLER_NO"));

				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(stmt);
		}
		return list;	
	}

	public int getBannerViewCount() {
		int result=0;
		
		return result;
	}

	public int getListCount(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}
		
}
  