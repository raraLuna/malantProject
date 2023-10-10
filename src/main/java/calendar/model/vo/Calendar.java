package calendar.model.vo;

import java.sql.Date;

public class Calendar implements java.io.Serializable {

	private static final long serialVersionUID = -6383203623916677862L;
	
	private String userNo;
	private Date calendarDate;
	private String diaryId;
	private String calendarMemo;

	
	
	public Calendar() {
		super();
	
	}


	public Calendar(String userNo, Date calendarDate, String diaryId, String calendarMemo) {
		super();
		this.userNo = userNo;
		this.calendarDate = calendarDate;
		this.diaryId = diaryId;
		this.calendarMemo = calendarMemo;
	}



	@Override
	public String toString() {
		return "Calendar [userNo=" + userNo + ", calendarDate=" + calendarDate + ", diaryId=" + diaryId
				+ ", calendarMemo=" + calendarMemo + "]";
	}



	public String getUserNo() {
		return userNo;
	}



	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}



	public Date getCalendarDate() {
		return calendarDate;
	}



	public void setCalendarDate(Date calendarDate) {
		this.calendarDate = calendarDate;
	}



	public String getDiaryId() {
		return diaryId;
	}



	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}



	public String getCalendarMemo() {
		return calendarMemo;
	}



	public void setCalendarMemo(String calendarMemo) {
		this.calendarMemo = calendarMemo;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}