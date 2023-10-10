package store.order.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import store.order.model.service.OrderService;
import store.order.model.vo.ProductOrder;
import store.shoppingBasket.model.vo.ShoppingBasket;




/**
 * Servlet implementation class OrderSheetServlet
 */
@WebServlet("/Osheet")
public class OrderSheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderSheetServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ArrayList<ShoppingBasket> olist = (ArrayList<ShoppingBasket>) session.getAttribute("olist");
		 
		System.out.println("session : "+olist.toString());

		ProductOrder productOrder = new ProductOrder();
		
		productOrder.setUserNo(request.getParameter("userno"));
		productOrder.setBuyerName(request.getParameter("buyerName"));
		productOrder.setBuyerContact(request.getParameter("buyerContact"));
		productOrder.setRecipient(request.getParameter("recipient"));
		productOrder.setRecipientContact(request.getParameter("recipient_contact"));
		productOrder.setCodePostal(request.getParameter("postcode"));
		productOrder.setDeliveryAddress(request.getParameter("address"));
		productOrder.setDeliveryAddress2(request.getParameter("detailAddress"));
		productOrder.setShippingAddressName(request.getParameter("extraAddress"));
		productOrder.setProductName(request.getParameter("productName"));
		productOrder.setThumbnailImg(request.getParameter("productThumnail"));
		productOrder.setTotalPrice(Integer.valueOf(request.getParameter("totalprice")));
		productOrder.setEmail(request.getParameter("email"));
		
		if((request.getParameter("delivery_note")) != null)
			productOrder.setDeliveryNote(request.getParameter("delivery_note"));
		
		//날짜시분초 생성
		Date currentDate = new Date();
		SimpleDateFormat sysdate = new SimpleDateFormat("yyyyMMddmm");
		System.out.println(sysdate.format(currentDate));
		
		String orderid = null;
		//암호화전 문자열 합치기
		String combString = productOrder.getUserNo()+productOrder.getTotalPrice()+sysdate.format(currentDate);	
		try { // byte[] 처리 후 암호화
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] oderidValus = combString.getBytes(Charset.forName("UTF-8"));
		md.update(oderidValus);
		orderid = Base64.getEncoder().encodeToString(oderidValus);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("orderid : "+orderid);
		
		productOrder.setOrderId(orderid);

		
		
		ArrayList<ProductOrder> porder = new ArrayList<ProductOrder>();
		
		for(int i = 0; i<olist.size(); i++) {
			ProductOrder po = new ProductOrder();
			ShoppingBasket getOlist = olist.get(i);
			
			po.setOrderId(orderid);
			po.setProductId(getOlist.getProductId());
			po.setQuantity(getOlist.getQuantity());
			
			porder.add(po);
		}
		
		int result = new OrderService().saveOrderSheet(productOrder, porder);

		RequestDispatcher view = null;
		if (result > 0) {
			System.out.println("주문서 저장 성공");
			System.out.println(productOrder.toString());
			request.setAttribute("productOrder", productOrder);
			view = request.getRequestDispatcher("views/store/order/orderPay.jsp");
			 
		} else {
			System.out.println("주문서 저장 실패");
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "주문서 저장 실패ㅜ.ㅜ..ㅅㅂ");
			view.forward(request, response);
		}
		view.forward(request, response);
	}
}
