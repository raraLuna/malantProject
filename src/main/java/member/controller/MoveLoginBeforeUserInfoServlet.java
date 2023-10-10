package member.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MoveLoginBeforeUserInfoServlet
 */
@WebServlet("/moveminfo")
public class MoveLoginBeforeUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoveLoginBeforeUserInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원정보 수정페이지로 이동 처리용 컨트롤러
		
		String userid = request.getParameter("userid");
		Member member = new MemberService().selectMember(userid);
		
		RequestDispatcher view = null;
		if(member != null) {
			view = request.getRequestDispatcher("views/member/confirmMemberBeforeInfo.jsp");
			request.setAttribute("member", member);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", userid + " 회원정보 조회 실패!");
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
