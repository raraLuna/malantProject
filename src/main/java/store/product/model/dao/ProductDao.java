package store.product.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import store.main.model.vo.MainContent;
import store.product.model.vo.ProductDetail;

public class ProductDao {

	public ArrayList<MainContent> selectFilterList(Connection conn, ArrayList<String> options) {
		System.out.println(options.toString());
		ArrayList<Integer> productids = new ArrayList<Integer>();
		ArrayList<MainContent> list = new ArrayList<MainContent>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		StringBuilder queryBuilder = new StringBuilder();

		queryBuilder.append("select product_id from ");

		for (int i = 1; i <= options.size(); i++) {
			if (1 == options.size()) {
				queryBuilder.append(
						"(select product_id from ST_PROD_CATE where CATEGORY_ID = (select CATEGORY_ID from ST_CATEGORY where category_name= ? )) ");
				break;
			}
			if (i == 1)
				queryBuilder.append(
						"(select product_id from ST_PROD_CATE where CATEGORY_ID = (select CATEGORY_ID from ST_CATEGORY where category_name= ? )) ");
			if (i < options.size() && i != 1)
				queryBuilder.append(
						"join (select product_id from ST_PROD_CATE where CATEGORY_ID = (select CATEGORY_ID from ST_CATEGORY where category_name= ? )) using(product_id) ");
			if (i == options.size())
				queryBuilder.append(
						"join (select product_id from ST_PROD_CATE where CATEGORY_ID = (select CATEGORY_ID from ST_CATEGORY where category_name= ? )) using(product_id) ");
		}

		String query = queryBuilder.toString();
		System.out.println(query);

		try {
			pstmt = conn.prepareStatement(query);

			for (int i = 1; i <= options.size(); i++) {
				pstmt.setString(i, options.get(i - 1).trim());

			}

			rset = pstmt.executeQuery();

			while (rset.next()) {
				productids.add(rset.getInt("PRODUCT_ID"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		System.out.println("productids : " + productids.toString());

		if (productids != null) {
			PreparedStatement pstmtT = null;
			ResultSet rsetT = null;

			StringBuilder queryBuilder2 = new StringBuilder();

			queryBuilder2.append(
					"select * " + "from ST_PRODUCT " + "join ST_SELLER using(SELLER_NO) " + "where PRODUCT_ID in (");

			for (int i = 1; i <= productids.size(); i++) {
				queryBuilder2.append("?");
				if (i < productids.size()) {
					queryBuilder2.append(",");
				}
			}
			queryBuilder2.append(")");

			String query2 = queryBuilder2.toString();
			System.out.println(queryBuilder2.toString());

			System.out.println("productids size : " + productids.size());
			try {
				pstmtT = conn.prepareStatement(query2);

				for (int i = 1; i <= productids.size(); i++) {
					pstmtT.setInt(i, productids.get(i - 1));
					System.out.println(productids.get(i - 1).toString());
				}

				rsetT = pstmtT.executeQuery();

				while (rsetT.next()) {
					MainContent pdetail = new MainContent();
					
					pdetail.setProductId(rsetT.getInt("PRODUCT_ID"));
					pdetail.setProductName(rsetT.getString("PRODUCT_NAME"));
					pdetail.setPrice(rsetT.getInt("PRICE"));
					pdetail.setProductThumbnail(rsetT.getString("PRODUCT_THUMBNAIL_IMG"));
					pdetail.setExposureYn(rsetT.getString("EXPOSURE_YN"));

					list.add(pdetail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(rsetT);
				close(pstmtT);
			}
			System.out.println("dao 마지막 값 : " + list.toString());
			return list;
		} else {
			return list;
		}
	}

//	public int getListCount(Connection conn) {
//		int listCount = 0;
//		Statement stmt = null;
//		ResultSet rset = null;
//		
//		String query = "select count(*) from *****(테이블명)*******";
//		try {
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery(query);
//	
//			if(rset.next()) {
//				listCount = rset.getInt(1);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(rset);
//			close(stmt);
//		}
//		return listCount;
//	}

	public ArrayList<ProductDetail> selectProductDetail(Connection conn, String productid) {
		ArrayList<ProductDetail> list = new ArrayList<ProductDetail>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * " + "from ST_PRODUCT " + "join ST_SELLER using(SELLER_NO) "
				+ "join ST_PROD_CATE using(PRODUCT_ID) " + "join ST_CATEGORY using(CATEGORY_ID) "
				+ "where PRODUCT_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productid);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ProductDetail pdetail = new ProductDetail();

				pdetail.setCategoryId(rset.getString("CATEGORY_ID"));
				pdetail.setProductId(rset.getString("PRODUCT_ID"));
				pdetail.setProductName(rset.getString("PRODUCT_NAME"));
				pdetail.setSellerName(rset.getString("DISPLAYED_STORE_NAME"));
				pdetail.setPrice(rset.getInt("PRICE"));
				pdetail.setDeliveryCharge(rset.getInt("DELIVERY_CHARGE"));
				pdetail.setDetailImage(rset.getString("PRODUCT_DETAIL_IMG"));
				pdetail.setProductDescription(rset.getString("PRODUCT_DESCRIPTION"));
				pdetail.setProductRegistDate(rset.getDate("PRODUCT_REGIST_DATE"));
				pdetail.setParentCategoryId(rset.getString("PARENT_CATEGORY_ID"));
				pdetail.setCategoryName(rset.getString("CATEGORY_NAME"));
				pdetail.setSellerContact(rset.getString("CONTACT"));
				pdetail.setSellerAddress(rset.getString("ADDRESS"));
				pdetail.setSellerNo(rset.getString("SELLER_NO"));
				pdetail.setBusinissNo(rset.getString("BUSINESS_NO"));
				pdetail.setThumbnailImg(rset.getString("PRODUCT_THUMBNAIL_IMG"));
				pdetail.setProductDetailImg(rset.getString("PRODUCT_DETAIL_IMG"));
				pdetail.setExposureYn(rset.getString("EXPOSURE_YN"));

				list.add(pdetail);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<ProductDetail> selectProductList(Connection conn, String categoryid) {
		ArrayList<ProductDetail> list = new ArrayList<ProductDetail>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * " + "from ST_PRODUCT " + "join ST_SELLER using(SELLER_NO) "
				+ "join ST_PROD_CATE using(PRODUCT_ID) " + "join ST_CATEGORY using(CATEGORY_ID) "
				+ "where CATEGORY_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, categoryid);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ProductDetail pdetail = new ProductDetail();

				pdetail.setCategoryId(rset.getString("CATEGORY_ID"));
				pdetail.setProductId(rset.getString("PRODUCT_ID"));
				pdetail.setProductName(rset.getString("PRODUCT_NAME"));
				pdetail.setSellerName(rset.getString("DISPLAYED_STORE_NAME"));
				pdetail.setPrice(rset.getInt("PRICE"));
				pdetail.setDeliveryCharge(rset.getInt("DELIVERY_CHARGE"));
				pdetail.setDetailImage(rset.getString("PRODUCT_DETAIL_IMG"));
				pdetail.setProductDescription(rset.getString("PRODUCT_DESCRIPTION"));
				pdetail.setProductRegistDate(rset.getDate("PRODUCT_REGIST_DATE"));
				pdetail.setParentCategoryId(rset.getString("PARENT_CATEGORY_ID"));
				pdetail.setCategoryName(rset.getString("CATEGORY_NAME"));
				pdetail.setSellerContact(rset.getString("CONTACT"));
				pdetail.setSellerAddress(rset.getString("ADDRESS"));
				pdetail.setSellerNo(rset.getString("SELLER_NO"));
				pdetail.setBusinissNo(rset.getString("BUSINESS_NO"));
				pdetail.setThumbnailImg(rset.getString("PRODUCT_THUMBNAIL_IMG"));
				pdetail.setProductDetailImg(rset.getString("PRODUCT_DETAIL_IMG"));
				pdetail.setExposureYn(rset.getString("EXPOSURE_YN"));

				list.add(pdetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int addViewCount(Connection conn, int productViewCount) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update board " + "set board_readcount = board_readcount + 1 " + "where board_num = ?"
				+ "*** 쿼리문 작성부분 수정할것***";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productViewCount);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	/*
	 * public void addReadCount(int ---) { Connection conn = getConnection(); int
	 * result = ---.addReadCount(conn, ---); if(result > 0) commit(conn); else
	 * rollback(conn); close(conn); }
	 */

}
