package store.order.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ProductOrder implements Serializable {

	private static final long serialVersionUID = 4764640135713951068L;
	
	private String orderId;
	private String productId;
	private String userNo;
	private int quantity;
	private Date orderDate;
	private String buyerName;
	private String buyerContact;
	private String recipient;
	private String recipientContact;
	private int price;
	private int deliveryCharge;
	private int totalPrice;
	private String email;
	private String orderState;
	
	//상품 필드 추가
	private String productName;
	private String thumbnailImg;
	private String sellerNo;
	private String storeName;
	
	//주소 받는 부분
	private String shippingAddressName;
	private String codePostal;
	private String deliveryAddress;
	private String deliveryAddress2;
	private String deliveryNote;
	
	//결제 받는 부분
	private String paymentType;
	private String orderProgress;
	private String paymentStatus;
	
	public ProductOrder() {
		super();
	}

	public ProductOrder(String orderId, String productId, String userNo, int quantity, Date orderDate, String buyerName,
			String buyerContact, String recipient, String recipientContact, int price, int deliveryCharge,
			int totalPrice, String productName, String thumbnailImg, String sellerNo, String storeName,
			String shippingAddressName, String codePostal, String deliveryAddress, String deliveryAddress2,
			String deliveryNote, String paymentType, String orderProgress, String paymentStatus, String email, String orderState) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.userNo = userNo;
		this.quantity = quantity;
		this.orderDate = orderDate;
		this.buyerName = buyerName;
		this.buyerContact = buyerContact;
		this.recipient = recipient;
		this.recipientContact = recipientContact;
		this.price = price;
		this.deliveryCharge = deliveryCharge;
		this.totalPrice = totalPrice;
		this.productName = productName;
		this.thumbnailImg = thumbnailImg;
		this.sellerNo = sellerNo;
		this.storeName = storeName;
		this.shippingAddressName = shippingAddressName;
		this.codePostal = codePostal;
		this.deliveryAddress = deliveryAddress;
		this.deliveryAddress2 = deliveryAddress2;
		this.deliveryNote = deliveryNote;
		this.paymentType = paymentType;
		this.orderProgress = orderProgress;
		this.paymentStatus = paymentStatus;
		this.email = email;
		this.orderState = orderState;
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

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerContact() {
		return buyerContact;
	}

	public void setBuyerContact(String buyerContact) {
		this.buyerContact = buyerContact;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRecipientContact() {
		return recipientContact;
	}

	public void setRecipientContact(String recipientContact) {
		this.recipientContact = recipientContact;
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getShippingAddressName() {
		return shippingAddressName;
	}

	public void setShippingAddressName(String shippingAddressName) {
		this.shippingAddressName = shippingAddressName;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryAddress2() {
		return deliveryAddress2;
	}

	public void setDeliveryAddress2(String deliveryAddress2) {
		this.deliveryAddress2 = deliveryAddress2;
	}

	public String getDeliveryNote() {
		return deliveryNote;
	}

	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getOrderProgress() {
		return orderProgress;
	}

	public void setOrderProgress(String orderProgress) {
		this.orderProgress = orderProgress;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	@Override
	public String toString() {
		return "ProductOrder [orderId=" + orderId + ", productId=" + productId + ", userNo=" + userNo + ", quantity="
				+ quantity + ", orderDate=" + orderDate + ", buyerName=" + buyerName + ", buyerContact=" + buyerContact
				+ ", recipient=" + recipient + ", recipientContact=" + recipientContact + ", price=" + price
				+ ", deliveryCharge=" + deliveryCharge + ", totalPrice=" + totalPrice + ", productName=" + productName
				+ ", thumbnailImg=" + thumbnailImg + ", sellerNo=" + sellerNo + ", storeName=" + storeName
				+ ", shippingAddressName=" + shippingAddressName + ", codePostal=" + codePostal + ", deliveryAddress="
				+ deliveryAddress + ", deliveryAddress2=" + deliveryAddress2 + ", deliveryNote=" + deliveryNote
				+ ", paymentType=" + paymentType + ", orderProgress=" + orderProgress + ", paymentStatus="
				+ paymentStatus + ", email=" + email + ", orderState="+orderState+"]";
	}

}
