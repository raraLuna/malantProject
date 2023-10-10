package seller.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.FileNameChange;
import seller.model.service.SellersService;
import store.product.model.vo.ProductDetail;

/**
 * Servlet implementation class InsertProductServlet
 */
@WebServlet("/sellpinsert")
public class InsertProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertProductServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    		RequestDispatcher view = null;
    		
		if (!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락됨.");
			view.forward(request, response);
		}
		
        int maxSize = 2056 * 1080 * 10;
        
        String savePath = request.getSession().getServletContext().getRealPath("/resources/store/images");
        
        MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
        
        ProductDetail pdtail = new ProductDetail();

        pdtail.setSellerNo(mrequest.getParameter("sellerNo"));
        pdtail.setProductName(mrequest.getParameter("product_name"));
        pdtail.setSellerName(mrequest.getParameter("store_name"));
        pdtail.setPrice(Integer.parseInt(mrequest.getParameter("price")));
        pdtail.setDeliveryCharge(Integer.parseInt(mrequest.getParameter("shipping_cost")));
        pdtail.setProductDescription(mrequest.getParameter("detail_description"));
        
        String thumbnail = mrequest.getFilesystemName("thumbnail_image");
        String detailImage = mrequest.getFilesystemName("detail_images");
        
        if (thumbnail != null) {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        	String rename = sdf.format(new java.sql.Date(System.currentTimeMillis()));
        	rename += '.' + thumbnail.substring(thumbnail.lastIndexOf(".")+1);
        	
        	String renameFileName = FileNameChange.changeth(
        			thumbnail, savePath, "yyyyMMddHHmmss");
        	
        	
        	pdtail.setThumbnailImg("/malant/resources/store/images/"+"th_"+renameFileName);
        }
        
        if (detailImage != null) {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        	String rename = sdf.format(new java.sql.Date(System.currentTimeMillis()));
        	rename += '.' + detailImage.substring(detailImage.lastIndexOf(".")+1);
        	
        	String renameFileName = FileNameChange.changede(
        			detailImage, savePath, "yyyyMMddHHmmss");

        	pdtail.setDetailImage("/malant/resources/store/images/"+"de_"+renameFileName);
        }

        ArrayList<String> options = new ArrayList<String>();

        String option5 = null;
        String option6 = null;

        for (int i = 1; i <= 8; i++) {
            if (mrequest.getParameter("option" + i) != null) {
                if (!(mrequest.getParameter("option" + i).equals("난이도")
                        || mrequest.getParameter("option" + i).equals("일조량")
                        || mrequest.getParameter("option" + i).equals("습도")
                        || mrequest.getParameter("option" + i).equals("정화능력")
                        || mrequest.getParameter("option" + i).equals("가습효과")
                        || mrequest.getParameter("option" + i).equals("크기")
                        || mrequest.getParameter("option" + i).equals("용도")
                        || mrequest.getParameter("option" + i).equals("재질")
                        || mrequest.getParameter("option" + i).equals("종류"))) {
                    if ("유".equals(mrequest.getParameter("option" + i)) && i == 5) {
                        option5 = "purification";
                    } else if ("유".equals(mrequest.getParameter("option" + i)) && i == 6) {
                        option6 = "humidifying";
                    } else {
                        option5 = null;
                        option6 = null;
                    }
                    options.add(mrequest.getParameter("option" + i));
                }
            }
        }

        int result = new SellersService().sellerInsertProduct(pdtail, options);
        
        int result2 = new SellersService().sellerInsertProduct2(pdtail, options);
  
        
        if (result > 0 && result2 >0) {
            System.out.println("성공");
            response.sendRedirect("/malant/sellplist?sellerNo="+pdtail.getSellerNo());
        } else {
            System.out.println("실패");
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
