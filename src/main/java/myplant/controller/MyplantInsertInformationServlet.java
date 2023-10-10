package myplant.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.FileNameChange;
import myplant.model.service.MyplantService;
import myplant.model.vo.Myplant;

/**
 * Servlet implementation class MyplantInsertInformationServlet
 */
@WebServlet("/mpnew")
public class MyplantInsertInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyplantInsertInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//반려식물 등록 처리용 컨트롤러 : 사진 업로드 있음 
		
		//1. multipart 방식으로 인코딩 되어서 전송 왔는지 확인 
		//아니면 에러 페이지를 내보냄 
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("view/common/error.jsp");
			request.setAttribute("message", "enctype='multipart/form-data' 속성 누락됨.");
			view.forward(request, response);
		}
		
		//2. 업로드할 파일의 용량 제한 설정 : 10메가바이트
		int maxSize = 1024 * 1024 * 10;
		
		//3. 업로드 파일의 저장 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("/resources/diary/myplant_upimages");
		
		//4. request를 MultipartRequest로 변환
		//MultipartRequest 클래스는 외부 라이브러리 사용 cos.jar
		//MultipartRequest 객체가 생성되면, 자동으로 지정 폴더에 업로드된 파일이 저장됨
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		//5. 데이터베이스  myplant 테이블에 기록할 값 추출
		//mrequest 사용해야함 
		Myplant myplant = new Myplant();

		myplant.setUserNo(mrequest.getParameter("USER_NO"));
		myplant.setMyplantName(mrequest.getParameter("MYPLANT_NAME"));
		myplant.setMyplantVariety(mrequest.getParameter("MYPLANT_VARIETY"));
		myplant.setMyplantMemo(mrequest.getParameter("MYPLANT_MEMO"));
		myplant.setMyplantStartDate((Date.valueOf(mrequest.getParameter("MYPLANT_START_DATE"))));

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
		}//업로드 된 파일이 있다면 
		
		
		//모델 서비스 메소드로 전달하고 결과 받기 
		int result = new MyplantService().insertMyplantInformation(myplant);
		
		//받은 결과로 성공/실패 페이지 내보내기
		if(result > 0) {
			//서블릿에서 서블릿 실행함
			response.sendRedirect("/malant/mplist?action=user_no&keyword=" + myplant.getUserNo() + "&page=1");
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "반려식물 등록 실패");
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
