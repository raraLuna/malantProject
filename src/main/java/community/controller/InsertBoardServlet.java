package community.controller;

import java.io.File;
import java.io.IOException;

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
import community.model.service.BoardService;
import community.model.vo.Board;
import community.model.vo.CMBaordHashtag;
import community.model.vo.CMBoardPhoto;
import community.model.vo.CMHashtag;
import net.coobird.thumbnailator.Thumbnails;

/**
 * Servlet implementation class InsertBoardServlet
 */
@WebServlet("/binsert")
public class InsertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public InsertBoardServlet() {
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
			request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락됨.");
			view.forward(request, response);
		}
		int maxSize = 1024 * 1024 * 10;
		String savePath = request.getSession().getServletContext().getRealPath("/resources/board/images");

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		Board board = new Board();

		board.setUserNo(mrequest.getParameter("userno"));
		board.setNickname(mrequest.getParameter("writer"));
		board.setBoardTitle(mrequest.getParameter("title"));
		board.setBoardContent(mrequest.getParameter("content"));

		String[] hashtags = mrequest.getParameterValues("hashtag");

		String[] fileNames = mrequest.getParameterValues("filenames");
		File[] imageFiles = new File[fileNames.length];
		for(int i = 0; i < imageFiles.length; i++) {
			imageFiles[i] = new File(savePath + "\\" + fileNames[i]);;
		}
		
		String[] renameFileNames = new String[fileNames.length];
		for(int i = 0; i < renameFileNames.length; i++) {
			renameFileNames[i] = FileNameChange.change(imageFiles[i].getName(), savePath, "yyyyMMddHHmmss");
		}
				
		String thumbFileName = "thumb_" + renameFileNames[0];
		File thumbFile = new File(savePath + "\\" + thumbFileName);

		for(int i = 0; i < renameFileNames.length; i++) {
			imageFiles[i] = new File(savePath + "\\" + renameFileNames[i]);
		}

		Thumbnails.of(imageFiles[0]).size(50, 50).toFile(thumbFile);

		board.setThumbnail(thumbFileName);
		
		int result = new BoardService().insertBoard(board);
	
		if (result > 0) {
			int boardNo = new BoardService().selectRecentBoardNo();
			
			for (int i = 0; i < renameFileNames.length; i++) {
				CMBoardPhoto photo = new CMBoardPhoto();
				photo.setBoardNo(boardNo);
				photo.setFilename(renameFileNames[i]);
				int result2 = new BoardService().insertBoardPhoto(photo);

				if (result2 > 0) {
					System.out.println(renameFileNames[i] + " 사진 삽입 성공");
				} else {
					view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", "새글 사진 등록 실패!");
					view.forward(request, response);
				}
			}

			for (int i = 0; i < hashtags.length; i++) {
				CMHashtag hashtag = new CMHashtag();
				hashtag.setHashtagContent(hashtags[i]);

				CMHashtag hashtagOk = new BoardService().selectHashtag(hashtags[i]);
				if(hashtagOk == null) {
					int result3 = new BoardService().insertHashtag(hashtags[i]);
					
					if(result3 > 0) {
						System.out.println(hashtags[i] + " 해시태그 삽입 성공");
					} else {
						System.out.println(hashtags[i] + " 해시태그 존재함");
					}
				}
				
				int hashtagNo = new BoardService().selectHashtagNo(hashtags[i]);
				
				CMBaordHashtag boardHashTag = new CMBaordHashtag();
				boardHashTag.setBoardNo(boardNo);
				boardHashTag.setHashtagNo(hashtagNo);
				
				int result4 = new BoardService().insertBoardHashtag(boardHashTag);
				if (result4 > 0) {
					System.out.println(boardNo + "번 게시글에 " + hashtags[i] + "해쉬태그 삽입 성공");
				} else {
					view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", boardNo + "번 게시글에 " + hashtags[i] + "해쉬태그 삽입 실패");
					view.forward(request, response);
				}
			}

			response.sendRedirect("bdlist");

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
