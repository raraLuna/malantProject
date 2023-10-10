package myplant.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.FileNameChange;
import myplant.model.service.MyplantService;
import myplant.model.vo.Myplant;

/**
 * Servlet implementation class MyplantUpdateInfomationServlet
 */
@WebServlet("/mpupdate")
public class MyplantUpdateInfomationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyplantUpdateInfomationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 원글 수정 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		
		//1. multipart 방식으로 인코딩되어서 전송왔는지 확인
		//아니면 에러페이지 내보냄
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("view/common/error.jsp");
			request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락됨.");
			view.forward(request, response);
		}
		
		//2. 파일 업로드 할 파일의 용량 제한 설정 : 10메가 바이트로 제한
		int maxSize = 1024 * 1024 * 10;
		
		//3. 업로드되는 파일의 저장폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("/resources/diary/myplant_upimages");
		
		//4. request를 MultipartRequest로 변환해야 함
		//MultipartRequest 클래스는 외부 라이브러리를 사용해야 함 : cos.jar
		//MultipartRequest 객체가 생성되면, 자동으로 폴더에 업로드된 파일이 저장됨
		
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		//5. 데이터베이스 my_plant 테이블에 기록할 값 추출
		//mrequest 사용할 것
		Myplant myplant = new Myplant();
		
		myplant.setMyplantId(mrequest.getParameter("MYPLANT_ID"));
		myplant.setUserNo(mrequest.getParameter("USER_NO"));
		myplant.setMyplantName(mrequest.getParameter("MYPLANT_NAME"));
		myplant.setMyplantVariety(mrequest.getParameter("MYPLANT_VARIETY"));
		myplant.setMyplantStartDate((Date.valueOf(mrequest.getParameter("MYPLANT_START_DATE"))));
		myplant.setMyplantMemo(mrequest.getParameter("MYPLANT_MEMO"));
		int currentPage = Integer.parseInt(mrequest.getParameter("page"));
		
		//6. 업로드된 원본 파일 이름 추출
		String upFileName = mrequest.getFilesystemName("MYPLANT_IMAGE_URL");
		myplant.setMyplantImageURL(upFileName);
		
		//7. 폴더에 저장된 파일의 이름 바꾸기 처리
		//저장 폴더에 같은 이름의 파일이 있을 경우를 대비하기 위함
		//년월일시분초.확장자 형식으로 변경
		if(upFileName != null) {
			//업로드된 파일이 있을때만 파일명 바꾸기 실행 
			
			String newUpFileName = FileNameChange.change(upFileName, savePath, "yyyyMMddHHmmss");
			
			myplant.setMyplantImageURL(newUpFileName);
		}
			
		// 서비스 메소드로 전달하고 결과받기
		int result = new MyplantService().updateMyplant(myplant);

		//받은 결과로 성공/실패 페이지 내보내기
		if (result > 0) {
			//서블릿에서 서블릿 실행
			response.sendRedirect("/malant/mplist?action=user_no&keyword=" + myplant.getUserNo() + "&page=" + currentPage);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", 
					"반려식물 정보 수정 실패!");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
