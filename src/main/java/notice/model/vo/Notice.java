package notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable {

	private static final long serialVersionUID = 5875954839993211121L;

	private int noticeNo;
	private String adminNo;
	private String noticeType;
	private String title;
	private String content;
	private String thumbnail;
	private String contentImage;
	private Date postDate;
	private Date eventStart;
	private Date eventEnd;
	private int noticeCount;
	private String bannerNo;
	private int priority;
	private String bannerTitle;
	private String bannerImage;
	private String linkUrl;
	private Date bannerStart;
	private Date bannerEnd;
	private int viewcount;

	public Notice() {
		super();
	}

	public Notice(int noticeNo, String adminNo, String noticeType, String title, String content, String thumbnail,
			String contentImage, Date postDate, Date eventStart, Date eventEnd, int noticeCount, String bannerNo,
			int priority, String bannerTitle, String bannerImage, String linkUrl, Date bannerStart, Date bannerEnd,
			int viewcount) {
		super();
		this.noticeNo = noticeNo;
		this.adminNo = adminNo;
		this.noticeType = noticeType;
		this.title = title;
		this.content = content;
		this.thumbnail = thumbnail;
		this.contentImage = contentImage;
		this.postDate = postDate;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.noticeCount = noticeCount;
		this.bannerNo = bannerNo;
		this.priority = priority;
		this.bannerTitle = bannerTitle;
		this.bannerImage = bannerImage;
		this.linkUrl = linkUrl;
		this.bannerStart = bannerStart;
		this.bannerEnd = bannerEnd;
		this.viewcount = viewcount;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getContentImage() {
		return contentImage;
	}

	public void setContentImage(String contentImage) {
		this.contentImage = contentImage;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Date getEventStart() {
		return eventStart;
	}

	public void setEventStart(Date eventStart) {
		this.eventStart = eventStart;
	}

	public Date getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(Date eventEnd) {
		this.eventEnd = eventEnd;
	}

	public int getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}

	public String getBannerNo() {
		return bannerNo;
	}

	public void setBannerNo(String bannerNo) {
		this.bannerNo = bannerNo;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getBannerTitle() {
		return bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Date getBannerStart() {
		return bannerStart;
	}

	public void setBannerStart(Date bannerStart) {
		this.bannerStart = bannerStart;
	}

	public Date getBannerEnd() {
		return bannerEnd;
	}

	public void setBannerEnd(Date bannerEnd) {
		this.bannerEnd = bannerEnd;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", adminNo=" + adminNo + ", noticeType=" + noticeType + ", title="
				+ title + ", content=" + content + ", thumbnail=" + thumbnail + ", contentImage=" + contentImage
				+ ", postDate=" + postDate + ", eventStart=" + eventStart + ", eventEnd=" + eventEnd + ", noticeCount="
				+ noticeCount + ", bannerNo=" + bannerNo + ", priority=" + priority + ", bannerTitle=" + bannerTitle
				+ ", bannerImage=" + bannerImage + ", linkUrl=" + linkUrl + ", bannerStart=" + bannerStart
				+ ", bannerEnd=" + bannerEnd + ", viewcount=" + viewcount + "]";
	}

}
