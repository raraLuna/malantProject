package myplant.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myplant.model.service.MyplantService;
import myplant.model.vo.Myplant;

/**
 * Servlet implementation class MyplantInformationServlet
 */
@WebServlet("/mpinfo")
public class MyplantDetailInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyplantDetailInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8"); 
		
		String userNo = request.getParameter("userNo");
		String myplantId = request.getParameter("myplantId");

		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		MyplantService mpservice = new MyplantService();

		Myplant myplant = mpservice.selectMyplantInfo(userNo, myplantId);
		
		
		
		RequestDispatcher view = null;
		if(myplant != null) {
			view = request.getRequestDispatcher("views/diary/myplantDetail.jsp");
			
			request.setAttribute("myplant",	 myplant);
			request.setAttribute("currentPage", currentPage);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			
			request.setAttribute("message", "반려식물 정보 보기 실패");
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
