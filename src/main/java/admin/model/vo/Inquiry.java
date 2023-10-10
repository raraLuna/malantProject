package admin.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Inquiry implements Serializable {

	private static final long serialVersionUID = 6886596233930319832L;
	
	private int inquiryNo;
	private String inquirerNo;
	private Date inquiryDate;
	private String inquiryTitle;
	private String inquiryContent;
	private String replierNo;
	private String replyContent;
	private String status;
	private Date completionDate;
	
	public Inquiry() {
	}

	public Inquiry(int inquiryNo, String inquirerNo, Date inquiryDate, String inquiryTitle, String inquiryContent,
			String status) {
		this.inquiryNo = inquiryNo;
		this.inquirerNo = inquirerNo;
		this.inquiryDate = inquiryDate;
		this.inquiryTitle = inquiryTitle;
		this.inquiryContent = inquiryContent;
		this.status = status;
	}

	public Inquiry(int inquiryNo, String inquirerNo, Date inquiryDate, String inquiryTitle, String inquiryContent,
			String replierNo, String replyContent, String status, Date completionDate) {
		this.inquiryNo = inquiryNo;
		this.inquirerNo = inquirerNo;
		this.inquiryDate = inquiryDate;
		this.inquiryTitle = inquiryTitle;
		this.inquiryContent = inquiryContent;
		this.replierNo = replierNo;
		this.replyContent = replyContent;
		this.status = status;
		this.completionDate = completionDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getInquiryNo() {
		return inquiryNo;
	}

	public String getInquirerNo() {
		return inquirerNo;
	}

	public Date getInquiryDate() {
		return inquiryDate;
	}

	public String getInquiryTitle() {
		return inquiryTitle;
	}

	public String getInquiryContent() {
		return inquiryContent;
	}

	public String getReplierNo() {
		return replierNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public String getStatus() {
		return status;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setInquiryNo(int inquiryNo) {
		this.inquiryNo = inquiryNo;
	}

	public void setInquirerNo(String inquirerNo) {
		this.inquirerNo = inquirerNo;
	}

	public void setInquiryDate(Date inquiryDate) {
		this.inquiryDate = inquiryDate;
	}

	public void setInquiryTitle(String inquiryTitle) {
		this.inquiryTitle = inquiryTitle;
	}

	public void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
	}

	public void setReplierNo(String replierNo) {
		this.replierNo = replierNo;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	@Override
	public String toString() {
		return "Inquiry [inquiryNo=" + inquiryNo + ", inquirerNo=" + inquirerNo + ", inquiryDate=" + inquiryDate
				+ ", inquiryTitle=" + inquiryTitle + ", inquiryContent=" + inquiryContent + ", replierNo=" + replierNo
				+ ", replyContent=" + replyContent + ", status=" + status + ", completionDate=" + completionDate
				+ "]";
	}

}
