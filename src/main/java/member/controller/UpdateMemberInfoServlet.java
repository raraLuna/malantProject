package member.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;

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
 * Servlet implementation class UpdateMemberInfoServlet
 */
@WebServlet("/mupdate")
public class UpdateMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 전송 온 값에 한글이 있다면 인코딩 처리함
		request.setCharacterEncoding("UTF-8"); // 값을 보낸 meta 태그의 인코딩 문자셋으로 설정
		
		// 2. 전송 온 값 꺼내서 변수 또는 객체에 저장하기
		Member member = new Member();
		
		member.setUserId(request.getParameter("userid"));
		member.setNickname(request.getParameter("nickname"));
		member.setEmail(request.getParameter("email"));
		String newPwd = request.getParameter("newpwd");
		member.setAlarmYn(request.getParameter("check_alarm") != null ? "Y" : "N");
		member.setNoticeYn(request.getParameter("check_notice") != null ? "Y" : "N");
		
		if(newPwd != null) {
			String cryptoUserpwd = null;
			member.setPwdUpdateDate(new Date(System.currentTimeMillis()));
			
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				byte[] pwdValues = newPwd.getBytes(Charset.forName("UTF-8"));
				md.update(pwdValues);
				cryptoUserpwd = Base64.getEncoder().encodeToString(pwdValues);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			member.setUserPwd(cryptoUserpwd);
		}
		
		// 회원 정보 업데이트
		int result = new MemberService().updateMember(member);
		Member updateMember = new MemberService().selectMember(member.getUserId());
		
		if(result > 0) { // 업데이트 성공시 세션에 회원 정보 업데이트
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", updateMember);
			response.sendRedirect("/malant"); // a 태그처럼 쓰면 됨
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp"); // 값을 담아서 보내야할 때 사용함.
			request.setAttribute("message", member.getUserId() + " 회원 정보 수정 실패!");
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
