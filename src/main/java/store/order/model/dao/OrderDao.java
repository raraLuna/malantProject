package store.order.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import store.main.model.vo.MainContent;
import store.order.model.vo.ProductOrder;

public class OrderDao {

	public ProductOrder selectProductOrder(Connection conn, String productId, int quantity) {

		ProductOrder porder = new ProductOrder();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from ST_PRODUCT " + "join ST_SELLER using(SELLER_NO) where product_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				porder.setProductId(productId);
				porder.setProductName(rset.getString("PRODUCT_NAME"));
				porder.setPrice(rset.getInt("PRICE"));
				porder.setDeliveryCharge(rset.getInt("DELIVERY_CHARGE"));
				porder.setThumbnailImg(rset.getString("PRODUCT_THUMBNAIL_IMG"));
				porder.setQuantity(quantity);
				porder.setStoreName(rset.getString("DISPLAYED_STORE_NAME"));
			}

			System.out.println("dao : " + porder.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return porder;
	}

	public int addOrderList(Connection conn, ProductOrder productOrder) {
		int result = 0;
		
//		송장번호 입력 랜덤숫자 발생기
//		String TrackingNo = null;
//		Random random = new Random();
//      long lTrackingNo = Math.abs(random.nextLong() % 10000000000000000L);
//      TrackingNo = String.valueOf(lTrackingNo);
//      System.out.println(TrackingNo);
        
        PreparedStatement pstmt = null;
        
        
        String query = "update ST_ORDER "
        		+ "set PAYMENT_STATUS = ?, ORDER_STATE = ? "
        		+ "where order_id = ?";
        
        try {
        	pstmt = conn.prepareStatement(query);
        	
        	pstmt.setString(1, productOrder.getPaymentStatus());
        	pstmt.setString(2, "결제완료");
        	pstmt.setString(3, productOrder.getOrderId());
        	
        	System.out.println("PaymentStatus "+productOrder.getPaymentStatus());
        	System.out.println("getOrderId "+productOrder.getOrderId());
        	
        	result = pstmt.executeUpdate();
        	
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
        	close(pstmt);
        }
        
        System.out.println("result : "+ result);
        
		return result;
	}

	public int saveOrderSheet(Connection conn, ProductOrder productOrder, ArrayList<ProductOrder> porder) {

		int result = 0;
		int result2 = 0;

		PreparedStatement pstmt = null;
		
		String Address = productOrder.getDeliveryAddress() + " " + productOrder.getDeliveryAddress2()+ " " + productOrder.getShippingAddressName();
		
		System.out.println("dao 쿼리1 : " + productOrder.toString());
		System.out.println("dao 쿼리2 : " + porder.toString());

		String query = "insert into ST_ORDER values " + "(?, ?, default, ?, ?, ?, ?, ?, ?, ?, default, ?, ?, default)";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, productOrder.getOrderId()); // ORDER_ID
			pstmt.setString(2, productOrder.getUserNo()); // USER_NO
			pstmt.setString(3, productOrder.getBuyerName()); // BUYER_NAME
			pstmt.setString(4, productOrder.getBuyerContact()); // BUYER_CONTACT
			pstmt.setString(5, productOrder.getRecipient()); // RECIPIENT
			pstmt.setString(6, productOrder.getRecipientContact()); // RECIPIENT_CONTACT
			pstmt.setString(7, Address);
			pstmt.setString(8, productOrder.getCodePostal()); // DELIVERY_ZIP_CODE
			pstmt.setString(11, productOrder.getEmail()); // oreder_emil
			pstmt.setNull(10, java.sql.Types.VARCHAR); // TRACKING_NO

			if (productOrder.getDeliveryNote() != null)
				pstmt.setString(9, productOrder.getDeliveryNote()); // DELIVERY_NOTE
			else
				pstmt.setNull(9, java.sql.Types.VARCHAR); // DELIVERY_NOTE

			result = pstmt.executeUpdate();

			System.out.println("쿼리1 result : " + result);

			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		if (result > 0) {
			// 두번째 쿼리
			PreparedStatement pstmt2 = null;
			String query2 = "insert into ST_ORDER_DETAILS values " + "(ORDER_DETAILS_SEQ.NEXTVAL, ?, ?, ?)";

			try {
				pstmt2 = conn.prepareStatement(query2);

				for (int i = 0; i < porder.size(); i++) {
					pstmt2.setString(1, productOrder.getOrderId()); // ORDER_ID
					pstmt2.setString(2, porder.get(i).getProductId()); // PRODUCT_ID
					pstmt2.setInt(3, porder.get(i).getQuantity()); // QUANTITY

					result2 += pstmt2.executeUpdate();
				}

				System.out.println("쿼리2 result : " + result2);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt2);
			}
			return result2;
		} else {
			result = 0;
			result2 = 0;
		}
		return result2;
	}

	public ArrayList<ProductOrder> selectOrderlist(Connection conn, String userno) {
		
		ArrayList<ProductOrder> olist = new ArrayList<ProductOrder>();
			
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * "
				+ "from ST_ORDER "
				+ "join ST_ORDER_DETAILS using (ORDER_ID) "
				+ "join ST_product using(PRODUCT_ID) "
				+ "join member using(USER_NO) "
				+ "join ST_SELLER using(SELLER_NO) "
				+ "where order_id in (select order_id from st_order where user_no = ?) "
				+ "order by order_date desc";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductOrder po = new ProductOrder();

				
				po.setOrderId(rset.getString("ORDER_ID"));
                po.setProductId(rset.getString("PRODUCT_ID"));
                po.setUserNo(rset.getString("USER_NO"));
                po.setQuantity(rset.getInt("QUANTITY"));
                po.setOrderDate(rset.getDate("ORDER_DATE"));
                po.setBuyerName(rset.getString("BUYER_NAME"));
                po.setBuyerContact(rset.getString("BUYER_CONTACT"));
                po.setRecipient(rset.getString("RECIPIENT"));
                po.setRecipientContact(rset.getString("RECIPIENT_CONTACT"));
                po.setPrice(rset.getInt("PRICE"));
                po.setDeliveryCharge(rset.getInt("DELIVERY_CHARGE"));
                po.setEmail(rset.getString("BUYER_EMAIL"));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
                po.setOrderState(rset.getString("ORDER_STATE"));
                po.setProductName(rset.getString("PRODUCT_NAME"));
                po.setThumbnailImg(rset.getString("PRODUCT_THUMBNAIL_IMG"));
                po.setStoreName(rset.getString("DISPLAYED_STORE_NAME"));
               
                po.setCodePostal(rset.getString("DELIVERY_ZIP_CODE"));
                po.setDeliveryAddress(rset.getString("DELIVERY_ADDRESS"));
                
               
                po.setDeliveryNote(rset.getString("DELIVERY_NOTE"));
                po.setPaymentStatus(rset.getString("PAYMENT_STATUS"));
				
				olist.add(po);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return olist;	
	}

}
