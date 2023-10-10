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
 * Servlet implementation class LoginMemberInfoServlet
 */
@WebServlet("/loginminfo")
public class LoginMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginMemberInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		
		// 패스워드 암호화 처리 : SHA-512
		String cryptoUserpwd = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] pwdValues = userPwd.getBytes(Charset.forName("UTF-8"));
			
			md.update(pwdValues);
			cryptoUserpwd = Base64.getEncoder().encodeToString(pwdValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 서비스 메소드로 값 전달 실행하고 결과 받기
		Member loginMember = new MemberService().selectLogin(userId, cryptoUserpwd);
		
		// 받은 결과를 가지고 성공/실패 페이지 내보내기
		RequestDispatcher view = null;
		if(loginMember != null) { // 로그인 성공
			view = request.getRequestDispatcher("minfo");
			request.setAttribute("userid", userId);
		} else { // 로그인 실패
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "로그인 실패. 아이디 또는 암호를 확인하세요.");
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
