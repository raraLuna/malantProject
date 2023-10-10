package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class CheckPasswordServlet
 */
@WebServlet("/login/checkpwd")
public class CheckPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPasswordServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트의 요청을 받는 메소드
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		
		// 패스워드 암호화 처리
		String cryptoUserpwd = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			byte[] pwdValues = userpwd.getBytes(Charset.forName("UTF-8"));
			md.update(pwdValues);
			cryptoUserpwd = Base64.getEncoder().encodeToString(pwdValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 서비스 메소드로 값 전달하고 실행결과 받음
		Member member = new MemberService().selectLogin(userid, cryptoUserpwd);
		
		String checkResult = null;
		// 받은 결과를 가지고 성공/실패 페이지 내보내기
		if(member != null) {  //로그인 성공
			checkResult = "success";
		} else {  //로그인 실패
			checkResult = "fail";
			
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(checkResult);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
