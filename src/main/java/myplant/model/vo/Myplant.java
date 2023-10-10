package myplant.model.vo;

import java.sql.Date;

public class Myplant implements java.io.Serializable{

	private static final long serialVersionUID = -3469451666664641172L;
	
	private String userNo;
	private String myplantId;
	private String myplantName;
	private String myplantVariety;
	private String myplantImageURL;
	private String myplantMemo;
	private Date myplantStartDate;
	private Date createdDate;

	
	
	public Myplant() {
		super();
		
	}



	public Myplant(String userNo, String myplantId, String myplantName, String myplantVariety, String myplantImageURL,
			String myplantMemo, Date myplantStartDate, Date createdDate) {
		super();
		this.userNo = userNo;
		this.myplantId = myplantId;
		this.myplantName = myplantName;
		this.myplantVariety = myplantVariety;
		this.myplantImageURL = myplantImageURL;
		this.myplantMemo = myplantMemo;
		this.myplantStartDate = myplantStartDate;
		this.createdDate = createdDate;
	}



	@Override
	public String toString() {
		return "Myplant [userNo=" + userNo + ", myplantId=" + myplantId + ", myplantName=" + myplantName
				+ ", myplantVariety=" + myplantVariety + ", myplantImageURL=" + myplantImageURL + ", myplantMemo="
				+ myplantMemo + ", myplantStartDate=" + myplantStartDate + ", createdDate=" + createdDate + "]";
	}



	public String getUserNo() {
		return userNo;
	}



	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}



	public String getMyplantId() {
		return myplantId;
	}



	public void setMyplantId(String myplantId) {
		this.myplantId = myplantId;
	}



	public String getMyplantName() {
		return myplantName;
	}



	public void setMyplantName(String myplantName) {
		this.myplantName = myplantName;
	}



	public String getMyplantVariety() {
		return myplantVariety;
	}



	public void setMyplantVariety(String myplantVariety) {
		this.myplantVariety = myplantVariety;
	}



	public String getMyplantImageURL() {
		return myplantImageURL;
	}



	public void setMyplantImageURL(String myplantImageURL) {
		this.myplantImageURL = myplantImageURL;
	}



	public String getMyplantMemo() {
		return myplantMemo;
	}



	public void setMyplantMemo(String myplantMemo) {
		this.myplantMemo = myplantMemo;
	}



	public Date getMyplantStartDate() {
		return myplantStartDate;
	}



	public void setMyplantStartDate(Date myplantStartDate) {
		this.myplantStartDate = myplantStartDate;
	}



	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}