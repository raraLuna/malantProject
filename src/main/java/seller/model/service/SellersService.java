package seller.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.*;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import seller.model.dao.SellersDao;
import seller.model.vo.Sellers;
import store.product.model.vo.ProductDetail;


public class SellersService {
	
	private SellersDao sdao = new SellersDao();
	
	public ArrayList<Sellers> slogin(String sellerid, String sellerpwd) {
		Connection conn = getConnection();
		ArrayList<Sellers> sellers = sdao.slogin(conn, sellerid, sellerpwd);
		close(conn);
		return sellers;
	}
	
	
	public ArrayList<ProductDetail> sellerPlist(String sellerno){
		Connection conn = getConnection();
		ArrayList<ProductDetail> sellers = sdao.sellerPlist(conn, sellerno);
		close(conn);
		return sellers;
	}
	
	public int sellerInsertProduct(ProductDetail splinsert, ArrayList<String> options){
		Connection conn = getConnection();
		int result = sdao.sellerInsertProduct(conn, splinsert, options);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int sellerInsertProduct2(ProductDetail splinsert, ArrayList<String> options){
		Connection conn = getConnection();
		int result = sdao.sellerInsertProduct2(conn, splinsert, options);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}


	public int sellerDeleteProduct(int productId) {
		Connection conn = getConnection();
		int result = sdao.sellerDeleteProduct(conn, productId);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
}
