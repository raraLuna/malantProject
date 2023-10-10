package store.order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import store.order.model.service.OrderService;
import store.order.model.vo.ProductOrder;
import store.shoppingBasket.model.service.ShoppingBasketService;

/**
 * Servlet implementation class OrderPayOkServletServlet
 */
@WebServlet("/opok")
public class OrderPayOkServletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPayOkServletServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	HttpSession session = request.getSession();
    	ProductOrder po = (ProductOrder)session.getAttribute("porder");;
    	
    	int result = 0;
    	int deleteResult = 0;
		RequestDispatcher view = null;
		
		JSONParser parser = new JSONParser();
		String orderId = request.getParameter("orderId");
		String paymentKey = request.getParameter("paymentKey");
		 int amount = Integer.parseInt(request.getParameter("amount"));
		
		String dbOrderId = po.getOrderId();
		int dbAmount = po.getTotalPrice();
		
		System.out.println("orderId : "+ orderId);
		System.out.println("dboId : "+ dbOrderId);
		System.out.println(paymentKey);
		System.out.println("amount : "+amount);
		System.out.println("dbAmount : "+dbAmount);
		
		
		if(dbOrderId.equals(orderId) && dbAmount == amount) {
			
			String authorizations = "Basic dGVzdF9za19LTmJkT3ZrNXJrNGFlRGUweXBFOG4wN3hsem1qOg==";
			
			String rurl;
			rurl = "https://api.tosspayments.com/v1/payments/" + paymentKey ;
			
			StringBuffer ress = new StringBuffer(); 
			try {
				URL url = new URL(rurl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestProperty("Authorization", authorizations);
			    connection.setRequestProperty("Content-Type", "application/json");
				connection.setRequestMethod("POST");
				connection.setDoOutput(true);
			    JSONObject obj = new JSONObject();
			    obj.put("orderId", orderId);
			    obj.put("amount", amount);
			    OutputStream outputStream = connection.getOutputStream();
			    outputStream.write(obj.toString().getBytes("UTF-8"));
			    
				int responsecode = connection.getResponseCode();
				BufferedReader br;
				if(responsecode == 200) {
					br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				} else {
					br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				}
				
				String inputLine;
				while((inputLine = br.readLine()) != null) {
					ress.append(inputLine);
				}
				
				br.close();
				JSONObject jsonObject = (JSONObject)parser.parse(ress.toString());
				System.out.println("ress : " + ress.toString());     
				System.out.println("jobj : " + jsonObject.toJSONString());
				String country = (String) jsonObject.get("jsonObject");
				String JorderId = (String) jsonObject.get("orderId");
				String lastTransactionKey = (String) jsonObject.get("lastTransactionKey");
				String secret = (String) jsonObject.get("secret");
				String requestedAt = (String) jsonObject.get("requestedAt");
				String currency = (String) jsonObject.get("currency");
				String status = (String) jsonObject.get("status");
				
				
				if(status.equals("DONE")) {	po.setPaymentStatus("Y");}
				else {	po.setPaymentStatus("N"); }
				
				System.out.println("po "+ po);
				
				result = new OrderService().addOrderList(po);
				String[] ProductId = new String[1];
				ProductId[0] = String.valueOf(po.getProductId());
				deleteResult = new ShoppingBasketService().deleteShoppingBasket(po.getUserNo(), ProductId);
				
			    } catch (Exception e) {
			        e.printStackTrace();
			        response.getWriter().print("API 요청 중 예외가 발생했습니다.");
			    }
        }else {
        	view = request.getRequestDispatcher("views/store/order/payFail.jsp");
        	request.setAttribute("message", "결제정보가 변경되었습니다. 다시 진행해 주세요");
    	}
		
		if(result>0) {
			view = request.getRequestDispatcher("views/store/order/paySuccess.jsp");
			
		}else {
			view = request.getRequestDispatcher("views/store/order/payFail.jsp");
        	request.setAttribute("message", "DB에 저장 실패임 ㅠㅠ");
		}
		view.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
