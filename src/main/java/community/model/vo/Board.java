package community.model.vo;

import java.sql.Date;

public class Board implements java.io.Serializable {

	private static final long serialVersionUID = -5219066150385199445L;

	private int boardNo;
	private String userNo;
	private String nickname;
	private String boardTitle;
	private String boardContent;
	private int boardLike;
	private Date boardDate;
	private int viewcount;
	private int reportCount;
	private String thumbnail;

	public Board() {
		super();
	}

	public Board(int boardNo, String userNo, String nickname, String boardTitle, String boardContent, int boardLike,
			Date boardDate, int viewcount, int reportCount, String thumbnail) {
		super();
		this.boardNo = boardNo;
		this.userNo = userNo;
		this.nickname = nickname;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardLike = boardLike;
		this.boardDate = boardDate;
		this.viewcount = viewcount;
		this.reportCount = reportCount;
		this.thumbnail = thumbnail;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getBoardLike() {
		return boardLike;
	}

	public void setBoardLike(int boardLike) {
		this.boardLike = boardLike;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", userNo=" + userNo + ", nickname=" + nickname + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", boardLike=" + boardLike + ", boardDate="
				+ boardDate + ", viewcount=" + viewcount + ", reportCount=" + reportCount + ", thumbnail=" + thumbnail
				+ "]";
	}

}
