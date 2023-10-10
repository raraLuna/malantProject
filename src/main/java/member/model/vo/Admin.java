package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Admin implements Serializable {

	private static final long serialVersionUID = -6021644396652521410L;
	
	private String adminNo;
	private String adminId;
	private String adminPwd;
	private String name;
	private String adminType;
	private Date createdDate;
	
	public Admin() {
	}

	public Admin(String adminNo, String adminId, String adminPwd, String name, String adminType, Date createdDate) {
		this.adminNo = adminNo;
		this.adminId = adminId;
		this.adminPwd = adminPwd;
		this.name = name;
		this.adminType = adminType;
		this.createdDate = createdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAdminNo() {
		return adminNo;
	}

	public String getAdminId() {
		return adminId;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public String getName() {
		return name;
	}

	public String getAdminType() {
		return adminType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Admin [adminNo=" + adminNo + ", adminId=" + adminId + ", adminPwd=" + adminPwd + ", name=" + name
				+ ", adminType=" + adminType + ", createdDate=" + createdDate + "]";
	}
	
}
