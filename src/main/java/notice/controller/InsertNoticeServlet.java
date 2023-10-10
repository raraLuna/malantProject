package notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
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
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class InsertNoticeServlet
 */
@WebServlet("/ninsert")
public class InsertNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public InsertNoticeServlet() {
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
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form 의 enctype='multipart/form-data' 속성 누락됨.");
			view.forward(request, response);
		}

		int maxSize = 1024 * 1024 * 10;

		String savePath = request.getSession().getServletContext().getRealPath("/resources/notice/notice_content_img/");

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		Notice notice = new Notice();
		notice.setAdminNo(mrequest.getParameter("adminno"));
		notice.setNoticeType(mrequest.getParameter("noticetype"));
		notice.setTitle(mrequest.getParameter("noticeTitle"));
		notice.setContent(mrequest.getParameter("noticeContent"));

		if (!notice.getNoticeType().equals("NOTICE")) {

			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date eventStartDate = new Date(dateFormat.parse(mrequest.getParameter("startDate")).getTime());
				Date eventEndDate = new Date(dateFormat.parse(mrequest.getParameter("endDate")).getTime());

				notice.setEventStart(eventStartDate);
				notice.setEventEnd(eventEndDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		String upFileName = mrequest.getFilesystemName("input-image");
		notice.setContentImage(upFileName);

		if (upFileName != null) {
			String newFileName = FileNameChange.change(upFileName, savePath, "yyyyMMddHHmmss");
			notice.setContentImage(newFileName);
		}

		int result = new NoticeService().insertNotice(notice);

		if (result > 0) {
			response.sendRedirect("ntitlelist");
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "새 게시 원글 등록 실패!");
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
