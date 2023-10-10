package community.model.vo;

import java.sql.Date;

public class CMBoardLike implements java.io.Serializable {

	private static final long serialVersionUID = -2763093486512734182L;

	private int boardNo;
	private Date likeDate;
	private String userNo;

	public CMBoardLike() {
		super();
	}

	public CMBoardLike(int boardNo, Date likeDate, String userNo) {
		super();
		this.boardNo = boardNo;
		this.likeDate = likeDate;
		this.userNo = userNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CMBoardLike [boardNo=" + boardNo + ", likeDate=" + likeDate + ", userNo=" + userNo + "]";
	}

}
