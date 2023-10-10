package community.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import community.model.service.BoardService;
import community.model.vo.CMHashtag;

/**
 * Servlet implementation class BoardTop3HashtagServlet
 */
@WebServlet("/btophash")
public class CommunityTop5HashtagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommunityTop5HashtagServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<CMHashtag> list = new BoardService().selectTop5Hash();
		JSONArray jarr = new JSONArray();
		
		for(CMHashtag hash : list) {
			JSONObject job = new JSONObject();
			
			job.put("hashno", hash.getHashtagNo());
			job.put("hashContent", URLEncoder.encode(hash.getHashtagContent(),"utf-8"));
			jarr.add(job);
			
		}
		
		JSONObject sendJson = new JSONObject();
		sendJson.put("hlist", jarr);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(sendJson.toJSONString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
