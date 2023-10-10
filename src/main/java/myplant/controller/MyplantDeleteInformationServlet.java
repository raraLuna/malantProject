package myplant.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myplant.model.service.MyplantService;

/**
 * Servlet implementation class MyplantDeleteInformationServlet
 */
@WebServlet("/mpdelete")
public class MyplantDeleteInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyplantDeleteInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//반려식물 삭제 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
	
		String userNo = request.getParameter("userNo");
		String myplantId = request.getParameter("myplantId");
		
		//서비스 메소드로 삭제 실행하고 결과받아서 성공/실패 뷰 내보내기
		if(new MyplantService().deleteMyplant(userNo, myplantId) > 0) {
			//받은 결과가 성공 일때 저장 폴더의 파일도 삭제처리
			String inFileName = request.getParameter("MyplantImageURL");
			if(inFileName != null) {
				String savePath = request.getSession().getServletContext().getRealPath("/resources/diary/myplant_upimages");
				new File(savePath + "\\" + inFileName).delete();
			}

			response.sendRedirect("/malant/mplist?action=user_no&keyword=" + userNo + "&page=1");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "반려식물 삭제 실패");
			view.forward(request, response);
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
