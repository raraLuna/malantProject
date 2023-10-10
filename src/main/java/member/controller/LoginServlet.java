package member.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Admin;
import member.model.vo.Member;
import member.model.vo.Seller;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/logincheck")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		
		String loc = request.getParameter("loc");
		System.out.println("loc : " + loc);
		
		Member member = null;
		Admin admin = null;
		Seller seller = null;
		
		String referer = request.getParameter("preReferer");
		
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
		
		// 로그인 경로 확인
		if("admin".equals(loc)) { // 
			admin = new MemberService().selectAdminLogin(userId, cryptoUserpwd);
		} else if ("seller".equals(loc)) {
			seller = new MemberService().selectSellerLogin(userId, cryptoUserpwd);
		} else {
			member = new MemberService().selectLogin(userId, cryptoUserpwd);
		}
		
		// 받은 결과를 가지고 성공/실패 페이지 내보내기
		RequestDispatcher view = null;
		if(admin != null) { // 관리자로 로그인 성공 했을 시
			member = new Member();
			
			member.setUserNo(admin.getAdminNo());
			member.setUserId(admin.getAdminId());
			member.setUserPwd(admin.getAdminPwd());
			member.setNickname(admin.getName());
			member.setSignType(admin.getAdminType());
			member.setCreatedDate(admin.getCreatedDate());
			
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
			session.setAttribute("isAdmin", true);
			if(referer != null && !referer.isEmpty()) {
				response.sendRedirect(referer);
			} else {
				response.sendRedirect("index.jsp");
			}
		} else if(seller != null) {
			member = new Member();
			
			member.setUserNo(seller.getSellerNo());
			member.setUserId(seller.getSellerId());
			member.setUserPwd(seller.getSellerPwd());
			member.setNickname(seller.getStoreName());
			member.setCreatedDate(seller.getCreatedDate());
			member.setSellerYn("Y");
			
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
			if(referer != null && !referer.isEmpty()) {
				response.sendRedirect(referer);
			} else {
				response.sendRedirect("/malant/views/seller/sellerProductList.jsp");
			}
		} else if(member != null && member.getWithdrawalYn().equals("Y")) { // 탈퇴한 회원인 경우
			Date withdrawalDate = new MemberService().selectWithdrawalDate(member.getUserNo());
			
			view = request.getRequestDispatcher("views/member/withdrawalRestore.jsp");
			request.setAttribute("userNo", member.getUserNo());
			request.setAttribute("withdrawalDate", withdrawalDate);
			
			view.forward(request, response);
		} else if(member != null && member.getBlockedYn().equals("N")) { // 로그인 성공
			// 마지막 접속일 업데이트
			member.setLastLoginDate(new Date(System.currentTimeMillis()));
			int result = new MemberService().updateMember(member);
			if(result > 0) {
				System.out.println(member.getUserNo() + "멤버 정보 업데이트 성공");
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
			
			if(member.getSellerYn().equals("Y")) {
				response.sendRedirect("/malant/views/seller/sellerProductList.jsp");
			} else if(referer != null && !referer.isEmpty()) {
				response.sendRedirect(referer);
			} else {
				response.sendRedirect("index.jsp");
			}
		} else { // 로그인 실패
			view = request.getRequestDispatcher("views/common/error.jsp");
			
			if(member != null && member.getBlockedYn().equals("Y")) { // 제한된 회원이라면
				request.setAttribute("message", "로그인이 제한된 회원입니다. 관리자에게 문의하세요.");
			} else if(member == null) { // 회원 정보가 없을 경우
				request.setAttribute("message", "로그인 실패. 아이디 또는 암호를 확인하세요.");
			}
			
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
