package diary.model.vo;

import java.sql.Date;

public class Diary implements java.io.Serializable {
	
	private static final long serialVersionUID = 389267166750680966L;
	
	private String userNo;
	private int diaryId;
	private Date diaryWritingDate;
	private String diaryContent;

	
	public Diary() {
		super();
	
	}


	public Diary(String userNo, int diaryId, Date diaryWritingDate, String diaryContent) {
		super();
		this.userNo = userNo;
		this.diaryId = diaryId;
		this.diaryWritingDate = diaryWritingDate;
		this.diaryContent = diaryContent;
	}


	@Override
	public String toString() {
		return "Diary [userNo=" + userNo + ", diaryId=" + diaryId + ", diaryWritingDate=" + diaryWritingDate
				+ ", diaryContent=" + diaryContent + "]";
	}


	public String getUserNo() {
		return userNo;
	}


	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}


	public int getDiaryId() {
		return diaryId;
	}


	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}


	public Date getDiaryWritingDate() {
		return diaryWritingDate;
	}


	public void setDiaryWritingDate(Date diaryWritingDate) {
		this.diaryWritingDate = diaryWritingDate;
	}


	public String getDiaryContent() {
		return diaryContent;
	}


	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}