package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {

	private static final long serialVersionUID = -4952932019676617041L;
	
	private String userNo;
	private String userId;
	private String userPwd;
	private Date pwdUpdateDate;
	private String email;
	private String nickname;
	private String profileImg;
	private String signType;
	private String alarmYn;
	private String noticeYn;
	private String blockedYn;
	private Date createdDate;
	private Date lastLoginDate;
	private String dormantYn;
	private String withdrawalYn;
	private String sellerYn;
	
	public Member() {}

	public Member(String userNo, String userId, Date pwdUpdateDate, String email, String profileImg, String noticeYn,
			String blockedYn, Date createdDate, Date lastLoginDate, String dormantYn, String withdrawalYn,
			String sellerYn) {
		this.userNo = userNo;
		this.userId = userId;
		this.pwdUpdateDate = pwdUpdateDate;
		this.email = email;
		this.profileImg = profileImg;
		this.noticeYn = noticeYn;
		this.blockedYn = blockedYn;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
		this.dormantYn = dormantYn;
		this.withdrawalYn = withdrawalYn;
		this.sellerYn = sellerYn;
	}

	public Member(String userNo, String userId, String userPwd, Date pwdUpdateDate, String email, String nickname,
			String profileImg, String signType, String alarmYn, String noticeYn, String blockedYn, Date createdDate,
			Date lastLoginDate, String dormantYn, String withdrawalYn, String sellerYn) {
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.pwdUpdateDate = pwdUpdateDate;
		this.email = email;
		this.nickname = nickname;
		this.profileImg = profileImg;
		this.signType = signType;
		this.alarmYn = alarmYn;
		this.noticeYn = noticeYn;
		this.blockedYn = blockedYn;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
		this.dormantYn = dormantYn;
		this.withdrawalYn = withdrawalYn;
		this.sellerYn = sellerYn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserNo() {
		return userNo;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public Date getPwdUpdateDate() {
		return pwdUpdateDate;
	}

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public String getSignType() {
		return signType;
	}

	public String getAlarmYn() {
		return alarmYn;
	}

	public String getNoticeYn() {
		return noticeYn;
	}

	public String getBlockedYn() {
		return blockedYn;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public String getDormantYn() {
		return dormantYn;
	}

	public String getWithdrawalYn() {
		return withdrawalYn;
	}

	public String getSellerYn() {
		return sellerYn;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public void setPwdUpdateDate(Date pwdUpdateDate) {
		this.pwdUpdateDate = pwdUpdateDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public void setAlarmYn(String alarmYn) {
		this.alarmYn = alarmYn;
	}

	public void setNoticeYn(String noticeYn) {
		this.noticeYn = noticeYn;
	}

	public void setBlockedYn(String blockedYn) {
		this.blockedYn = blockedYn;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public void setDormantYn(String dormantYn) {
		this.dormantYn = dormantYn;
	}

	public void setWithdrawalYn(String withdrawalYn) {
		this.withdrawalYn = withdrawalYn;
	}

	public void setSellerYn(String sellerYn) {
		this.sellerYn = sellerYn;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", pwdUpdateDate="
				+ pwdUpdateDate + ", email=" + email + ", nickname=" + nickname + ", profileImg=" + profileImg
				+ ", signType=" + signType + ", alarmYn=" + alarmYn + ", noticeYn=" + noticeYn + ", blockedYn="
				+ blockedYn + ", createdDate=" + createdDate + ", lastLoginDate=" + lastLoginDate + ", dormantYn="
				+ dormantYn + ", withdrawalYn=" + withdrawalYn + ", sellerYn=" + sellerYn + "]";
	}
	
}