package map.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import map.model.service.ArboretumService;

import map.model.vo.Arboretum;

/**
 * Servlet implementation class Ar_DetailSelectServlet
 */
@WebServlet("/ardetailinfo")
public class ArboretumDetailSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArboretumDetailSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String arboretumId = request.getParameter("arid");
		
		ArboretumService aservice = new ArboretumService();
		
		Arboretum arboretum = aservice.selectDetailInformation(arboretumId);
		
		RequestDispatcher view = null;
		if(arboretum != null) {
			view = request.getRequestDispatcher("views/map/ArboretumDetailInfoPage.jsp");
			
			request.setAttribute("arboretum", arboretum);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			
			request.setAttribute("message", "해당 수목원의 정보를 불러올 수 없음!!");
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
