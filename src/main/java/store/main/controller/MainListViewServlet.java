package store.main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import store.main.model.service.MainService;
import store.main.model.vo.*;


/**
 * Servlet implementation class mainBannerServlet
 */
@WebServlet("/smplist")
public class MainListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainListViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	ArrayList<MainContent> mlist = new MainService().selectBanner();
    	ArrayList<MainContent> plist = new MainService().selectMainProductList();
        

        RequestDispatcher view = null;
        if (plist.size() > 0) {
            view = request.getRequestDispatcher("views/store/storeMain.jsp");
            request.setAttribute("plist", plist);
            request.setAttribute("mlist", mlist);
        } else {
            view = request.getRequestDispatcher("views/common/error.jsp");
            request.setAttribute("message", "메인 데이터 조회 실패");

        }
        System.out.println(mlist.toString());
        System.out.println("plist"+plist.toString());
        
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
