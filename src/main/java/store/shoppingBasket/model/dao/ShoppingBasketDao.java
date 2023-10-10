package store.shoppingBasket.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import store.shoppingBasket.model.vo.ShoppingBasket;

public class ShoppingBasketDao {

	public ArrayList<ShoppingBasket> selectSblist(Connection conn, String userNo) {
		
		ArrayList<ShoppingBasket> sblist = new ArrayList<ShoppingBasket>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * "
				+ "from ST_BASKET "
				+ "join ST_PRODUCT using(PRODUCT_ID) "
				+ "join ST_SELLER using(SELLER_NO) "
				+ "where USER_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNo);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				ShoppingBasket sb = new ShoppingBasket();
				int totalPrice = ((rset.getInt("PRICE") * (rset.getInt("QUANTITY")) + rset.getInt("DELIVERY_CHARGE")));
				System.out.println(totalPrice);
				
				sb.setProductId(rset.getString("PRODUCT_ID"));
                sb.setUserNo(rset.getString("USER_NO"));
                sb.setQuantity(rset.getInt("QUANTITY"));
                sb.setPrice(rset.getInt("PRICE"));
                sb.setDeliveryChage(rset.getInt("DELIVERY_CHARGE"));
                sb.setTotalPrice(totalPrice);
                sb.setProductThumnail(rset.getString("PRODUCT_THUMBNAIL_IMG"));
                sb.setProductName(rset.getString("PRODUCT_NAME"));
                sb.setStoreName(rset.getString("DISPLAYED_STORE_NAME"));

                System.out.println(sb+"\n");
                
                sblist.add(sb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return sblist;
	}

	public int deleteShoppingBasket(Connection conn, String userNo, String[] productIdArray) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from st_basket where user_no = ? and product_id = ?";
		
		try {
			
			for(int i =0; i<productIdArray.length; i++) {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNo);
			pstmt.setString(2, productIdArray[i]);
			result = pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int addBasket(Connection conn, String userNo, int productId, int quantity) {
		int result = 0;

		PreparedStatement pstmt = null;

		String query = "INSERT INTO ST_BASKET VALUES (BASKET_SEQUENCE.NEXTVAL, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userNo);
			pstmt.setInt(2, productId);
			pstmt.setInt(3, quantity);

			result = pstmt.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
