package store.order.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import store.order.model.dao.OrderDao;
import store.order.model.vo.ProductOrder;

public class OrderService {
	
	private OrderDao odao = new OrderDao();
	
	public ProductOrder selectProductOrder(String productId, int quantity) {
		Connection conn = getConnection();
		ProductOrder list = odao.selectProductOrder(conn, productId, quantity);
		close(conn);
		return list;
	}
	
	public ArrayList<ProductOrder> selectOrderlist(String userno) {
		Connection conn = getConnection();
		ArrayList<ProductOrder> olist = odao.selectOrderlist(conn, userno);
		close(conn);
		return olist;
	}
	
	public int addOrderList(ProductOrder productOrder) {
		Connection conn = getConnection();
		int result = odao.addOrderList(conn, productOrder);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int saveOrderSheet(ProductOrder productOrder, ArrayList<ProductOrder> porder) {
		Connection conn = getConnection();
		int result = odao.saveOrderSheet(conn, productOrder, porder);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);		
		return result;
	}
}
