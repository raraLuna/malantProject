package seller.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.model.service.SellersService;
import store.product.model.vo.ProductDetail;

/**
 * Servlet implementation class SellerProductListServlet
 */
@WebServlet("/sellplist")
public class SellerProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = null;
		
		String sellerNo = request.getParameter("sellerNo");
		System.out.println("sellerNo " + sellerNo);

        ArrayList<ProductDetail> sellplist = new SellersService().sellerPlist(sellerNo);
        
        System.out.println(sellplist.toString()); //확인코드

        if (sellplist != null) {
            view = request.getRequestDispatcher("views/seller/sellerProductList.jsp");
            request.setAttribute("sellplist", sellplist);
        } else {
            view = request.getRequestDispatcher("views/common/error.jsp");
            request.setAttribute("message", "조회된 상품이 없습니다.");
        }
        view.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
