package diary.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

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
import diary.model.service.DiaryService;
import diary.model.vo.Diary;
import diary.model.vo.MyDiaryPhotoes;

/**
 * Servlet implementation class DiaryUpdateServlet
 */
@WebServlet("/dupdate")
public class DiaryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DiaryUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher view = null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("view/common/error.jsp");
			request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락됨.");
			view.forward(request, response);
		}

		int maxSize = 1024 * 1024 * 10;
		String savePath = request.getSession().getServletContext().getRealPath("/resources/diary/diary_upimages");

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		Diary diary = new Diary();

		diary.setUserNo(mrequest.getParameter("userNo"));
		diary.setDiaryId(Integer.parseInt(mrequest.getParameter("diaryId")));
		diary.setDiaryContent(mrequest.getParameter("diary_content"));

		// 출력할 페이지 지정
		int currentPage = 1;

		// 전송 온 페이지 값이 있다면 추출함
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		DiaryService dservice = new DiaryService();

		// 이전 첨부파일에 대한 삭제여부 값 추출
		String deleteFlag = mrequest.getParameter("deleteFlag");

		if (deleteFlag != null && deleteFlag.equals("yes")) {
			ArrayList<MyDiaryPhotoes> list = dservice.selectDiaryPhotoes(diary.getDiaryId());
			if (list.size() > 0) {
				for (MyDiaryPhotoes p : list) {
					new File(savePath + "\\" + p.getFileName()).delete();
				}
			}
			// 원래 첨부파일이 있었는데 파일삭제가 선택된 경우
			dservice.deleteDiaryPhotoes(diary.getDiaryId());
		}

		// 새로 첨부
		String[] fileNames = mrequest.getParameterValues("filenames");
		if(fileNames != null) {
		
		for (String fname : fileNames) {
			System.out.println(fname);
			int seqId = dservice.selectPhotoLastSeq() + 1;
			String renameFileName = "diary_" + diary.getUserNo() + "_" + seqId + "."
					+ fname.substring(fname.lastIndexOf(".") + 1);

			File originFile = new File(savePath + "\\" + fname);
			File renameFile = new File(savePath + "\\" + renameFileName);

			if (!originFile.renameTo(renameFile)) {
				// renameTo() 메소드가 실패한 경우 (false), 직접 수동으로 바꾸기함
				// 원본 파일의 내용을 읽어서 리네임파일에 복사해 넣고, 끝나면 원본 파일을 삭제함

				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);

				int data = -1;
				while ((data = fin.read()) != -1) {
					fout.write(data);
				}

				fin.close();
				fout.close();
				originFile.delete(); // 원본 파일 삭제함
			}

			dservice.insertMyDiaryPhoto(diary.getDiaryId(), renameFileName);

		}
		}
		// 6. 서비스 메소드로 전달하고 결과받기
		int result = dservice.updateDiary(diary);

		// 7. 받은 결과로 성공/실패 페이지 내보내기
		if (result > 0) {
			// 서블릿에서 서블릿 실행
			response.sendRedirect("/malant/dlist?action=user_no&keyword=" + diary.getUserNo() + "&page=" + currentPage);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "일기 수정 실패!");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
