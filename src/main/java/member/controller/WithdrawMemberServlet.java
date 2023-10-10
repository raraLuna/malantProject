package member.controller;

import java.io.IOException;

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
 * Servlet implementation class WithdrawMemberServlet
 */
@WebServlet("/mleave")
public class WithdrawMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawMemberServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원탈퇴 처리 서블릿
		String userNo = request.getParameter("userno");
		
		// 탈퇴회원 테이블 추가 처리
		int insertResult = new MemberService().insertWithdrawal(userNo);
		
		// 결과에 따라 회원 탈퇴상태 업데이트
		RequestDispatcher view = null;
		if(insertResult > 0) { // 탈퇴회원 테이블 추가 성공 시
			int updateResult = new MemberService().updateWithdrawal(userNo);
			
			if(updateResult > 0) { // 탈퇴상태 업데이트 성공 시
				view = request.getRequestDispatcher("views/member/withdrawalSuccess.jsp");
				
				// 로그아웃 처리
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.invalidate();
				}
			} else { // 탈퇴상태 업데이트 실패 시
				view = request.getRequestDispatcher("views/common/error.jsp");
				request.setAttribute("message", "회원 탈퇴상태 업데이트 실패");
			}
		} else { // 탈퇴회원 테이블 추가 실패 시
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "탈퇴회원 테이블 추가 실패");
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
