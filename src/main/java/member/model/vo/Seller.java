package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Seller implements Serializable {

	private static final long serialVersionUID = -951810464657238823L;
	
	private String sellerNo;
	private String businessNo;
	private String sellerId;
	private String sellerPwd;
	private String contact;
	private String email;
	private String storeName;
	private String displayedStoreName;
	private String address;
	private String approvalYn;
	private Date approvalDate;
	private Date createdDate;
	
	public Seller() {}

	public Seller(String sellerNo, String businessNo, String sellerId, String sellerPwd, String contact, String email,
			String storeName, String address, String approvalYn, Date createdDate) {
		this.sellerNo = sellerNo;
		this.businessNo = businessNo;
		this.sellerId = sellerId;
		this.sellerPwd = sellerPwd;
		this.contact = contact;
		this.email = email;
		this.storeName = storeName;
		this.address = address;
		this.approvalYn = approvalYn;
		this.createdDate = createdDate;
	}

	public Seller(String sellerNo, String businessNo, String sellerId, String sellerPwd, String contact, String email,
			String storeName, String displayedStoreName, String address, String approvalYn, Date approvalDate,
			Date createdDate) {
		this.sellerNo = sellerNo;
		this.businessNo = businessNo;
		this.sellerId = sellerId;
		this.sellerPwd = sellerPwd;
		this.contact = contact;
		this.email = email;
		this.storeName = storeName;
		this.displayedStoreName = displayedStoreName;
		this.address = address;
		this.approvalYn = approvalYn;
		this.approvalDate = approvalDate;
		this.createdDate = createdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSellerNo() {
		return sellerNo;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public String getSellerId() {
		return sellerId;
	}

	public String getSellerPwd() {
		return sellerPwd;
	}

	public String getContact() {
		return contact;
	}

	public String getEmail() {
		return email;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getDisplayedStoreName() {
		return displayedStoreName;
	}

	public String getAddress() {
		return address;
	}

	public String getApprovalYn() {
		return approvalYn;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public void setSellerPwd(String sellerPwd) {
		this.sellerPwd = sellerPwd;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setDisplayedStoreName(String displayedStoreName) {
		this.displayedStoreName = displayedStoreName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setApprovalYn(String approvalYn) {
		this.approvalYn = approvalYn;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Seller [sellerNo=" + sellerNo + ", businessNo=" + businessNo + ", sellerId=" + sellerId + ", sellerPwd="
				+ sellerPwd + ", contact=" + contact + ", email=" + email + ", storeName=" + storeName
				+ ", displayedStoreName=" + displayedStoreName + ", address=" + address + ", approvalYn=" + approvalYn
				+ ", approvalDate=" + approvalDate + ", createdDate=" + createdDate + "]";
	}

	

}
