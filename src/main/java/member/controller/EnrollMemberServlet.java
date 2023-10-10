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

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class EnrollMemberServlet
 */
@WebServlet("/menroll")
public class EnrollMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollMemberServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 처리용 컨트롤러
		
		request.setCharacterEncoding("UTF-8");
		
		// 전송온 값 꺼내서 변수 또는 객체에 옮겨 저장
		Member member = new Member();
		
		member.setUserId(request.getParameter("userid"));
//		String userpwd = request.getParameter("userpwd");
		member.setNickname(request.getParameter("nickname"));
		member.setEmail(request.getParameter("email"));
		
		member.setSignType(request.getParameter("signtype").toUpperCase());
		member.setAlarmYn(request.getParameter("alarm") != null ? "Y" : "N");
		member.setNoticeYn(request.getParameter("notice") != null ? "Y" : "N");
		
		// 비밀번호 암호화 : SHA-512(단방향 해싱함수)
		String userpwd = request.getParameter("userpwd");
		String cryptoUserpwd = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] pwdValues = userpwd.getBytes(Charset.forName("UTF-8"));
			md.update(pwdValues);
			cryptoUserpwd = Base64.getEncoder().encodeToString(pwdValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		member.setUserPwd(cryptoUserpwd);
		
		// 모델의 서비스 값 전달 실행하고 결과 받기
		int result = new MemberService().insertMember(member);
		
		// 받은 결과에 따라 성공 또는 실패 페이지 내보내기
		if(result > 0) {
			// 회원가입 성공시 로그인 페이지로 이동
			response.sendRedirect("/malant/views/member/loginPage.jsp");
		} else {
			// 회원가입 실패시 error.jsp 로 에러메세지를 보냄
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "회원가입 실패!");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
