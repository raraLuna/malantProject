package community.model.vo;

import java.sql.Date;

public class Comment implements java.io.Serializable {

	private static final long serialVersionUID = 5471428830133210366L;

	private int commentNo;
	private int boardNo;
	private String userNo;
	private String nickname;
	private String commentContent;
	private Date commentDate;

	public Comment() {
		super();
	}

	public Comment(int commentNo, int boardNo, String userNo, String nickname, String commentContent,
			Date commentDate) {
		super();
		this.commentNo = commentNo;
		this.boardNo = boardNo;
		this.userNo = userNo;
		this.nickname = nickname;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", boardNo=" + boardNo + ", userNo=" + userNo + ", nickname="
				+ nickname + ", commentContent=" + commentContent + ", commentDate=" + commentDate + "]";
	}

}