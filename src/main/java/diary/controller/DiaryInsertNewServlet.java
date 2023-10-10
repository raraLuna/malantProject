package diary.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import diary.model.service.DiaryService;
import diary.model.vo.Diary;

/**
 * Servlet implementation class DiaryInsertNewServlet
 */
@WebServlet("/dnew")
public class DiaryInsertNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiaryInsertNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("view/common/error.jsp");
			request.setAttribute("message", "enctype='multipart/form-data' 속성 누락됨.");
			view.forward(request, response);
		}
		
		int maxSize = 1024 * 1024 * 10;
		
		String savePath = request.getSession().getServletContext().getRealPath("/resources/diary/diary_upimages");
		
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		

		Diary diary = new Diary();

		diary.setUserNo(mrequest.getParameter("user_no"));
		diary.setDiaryContent(mrequest.getParameter("diary_content"));

		DiaryService dservice = new DiaryService();
		
		int result = dservice.insertNewDiray(diary);
		int diaryId = dservice.selectDiaryId(diary.getUserNo());

		String[] fileNames = mrequest.getParameterValues("filenames");
		if(fileNames != null) {
	
		for(String fname : fileNames) {
			System.out.println(fname);
			int seqId = dservice.selectPhotoLastSeq() + 1;
			String renameFileName = "diary_" + diary.getUserNo() + "_" + seqId + "." + fname.substring(fname.lastIndexOf(".") + 1);
			
			File originFile = new File(savePath + "\\" + fname);
			File renameFile = new File(savePath + "\\" + renameFileName);
			
			if(!originFile.renameTo(renameFile)) {
				//renameTo() 메소드가 실패한 경우 (false), 직접 수동으로 바꾸기함
				//원본 파일의 내용을 읽어서 리네임파일에 복사해 넣고, 끝나면 원본 파일을 삭제함
				
				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);
				
				int data = -1;
				while((data = fin.read()) != -1) {
					fout.write(data);
				}
				
				fin.close();
				fout.close();
				originFile.delete();  //원본 파일 삭제함
			}
			
			dservice.insertMyDiaryPhoto(diaryId, renameFileName);
		
		}
		}

	
		//받은 결과로 성공/실패 페이지 내보내기
		if(result > 0) {
			//서블릿에서 서블릿 실행함
			response.sendRedirect("/malant/dlist?action=user_no&keyword=" + diary.getUserNo() + "&page=1");
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "일기 등록 실패");
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
