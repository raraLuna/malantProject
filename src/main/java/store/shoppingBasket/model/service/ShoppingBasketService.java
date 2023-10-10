package store.shoppingBasket.model.service;

import static common.JDBCTemplate.*;

import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import store.shoppingBasket.model.dao.ShoppingBasketDao;
import store.shoppingBasket.model.vo.ShoppingBasket;

public class ShoppingBasketService {
	ShoppingBasketDao sdao = new ShoppingBasketDao();

	public ArrayList<ShoppingBasket> selectSblist(String userNo) {
		Connection conn = getConnection();
		ArrayList<ShoppingBasket> list = sdao.selectSblist(conn, userNo);
		close(conn);
		return list;
	}

	public int deleteShoppingBasket(String userNo, String[] productIdArray) {
		Connection conn = getConnection();
		int result = sdao.deleteShoppingBasket(conn, userNo, productIdArray);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int addBasket(String userNo, int productId, int quantity) {
		Connection conn = getConnection();
		
		int sbadd = sdao.addBasket(conn, userNo, productId, quantity);
		
		if(sbadd > 0)
			commit(conn);
		else
			rollback(conn);
			
		close(conn);
		return sbadd;
	}

}