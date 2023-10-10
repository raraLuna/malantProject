package seller.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Sellers implements Serializable {

	private static final long serialVersionUID = 4764640135713951068L;
	
	private String orderId;
	private String productId;
	private String userNo;
	private int quantity;
	private Date orderDate;
	private int price;
	private int deliveryCharge;
	private int totalPrice;
	private String email;
	private String orderState;
	private String productName;
	private String thumbnailImg;
	private String sellerNo;
    private String businessNo;
    private String sellerId;
    private String sellerPwd;
    private String contact;
    private String storeName;
    private String displayedStoreName;
    private String address;
    private char approvalYn;
	
	public Sellers() {
		super();
	}

	public Sellers(String orderId, String productId, String userNo, int quantity, Date orderDate, int price,
			int deliveryCharge, int totalPrice, String email, String orderState, String productName,
			String thumbnailImg, String sellerNo, String businessNo, String sellerId, String sellerPwd, String contact,
			String storeName, String displayedStoreName, String address, char approvalYn) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.userNo = userNo;
		this.quantity = quantity;
		this.orderDate = orderDate;
		this.price = price;
		this.deliveryCharge = deliveryCharge;
		this.totalPrice = totalPrice;
		this.email = email;
		this.orderState = orderState;
		this.productName = productName;
		this.thumbnailImg = thumbnailImg;
		this.sellerNo = sellerNo;
		this.businessNo = businessNo;
		this.sellerId = sellerId;
		this.sellerPwd = sellerPwd;
		this.contact = contact;
		this.storeName = storeName;
		this.displayedStoreName = displayedStoreName;
		this.address = address;
		this.approvalYn = approvalYn;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(int deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getThumbnailImg() {
		return thumbnailImg;
	}

	public void setThumbnailImg(String thumbnailImg) {
		this.thumbnailImg = thumbnailImg;
	}

	public String getSellerNo() {
		return sellerNo;
	}

	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerPwd() {
		return sellerPwd;
	}

	public void setSellerPwd(String sellerPwd) {
		this.sellerPwd = sellerPwd;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getDisplayedStoreName() {
		return displayedStoreName;
	}

	public void setDisplayedStoreName(String displayedStoreName) {
		this.displayedStoreName = displayedStoreName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public char getApprovalYn() {
		return approvalYn;
	}

	public void setApprovalYn(char approvalYn) {
		this.approvalYn = approvalYn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ProductOrder [orderId=" + orderId + ", productId=" + productId + ", userNo=" + userNo + ", quantity="
				+ quantity + ", orderDate=" + orderDate + ", price=" + price + ", deliveryCharge=" + deliveryCharge
				+ ", totalPrice=" + totalPrice + ", email=" + email + ", orderState=" + orderState + ", productName="
				+ productName + ", thumbnailImg=" + thumbnailImg + ", sellerNo=" + sellerNo + ", businessNo="
				+ businessNo + ", sellerId=" + sellerId + ", sellerPwd=" + sellerPwd + ", contact=" + contact
				+ ", storeName=" + storeName + ", displayedStoreName=" + displayedStoreName + ", address=" + address
				+ ", approvalYn=" + approvalYn + "]";
	}
}
