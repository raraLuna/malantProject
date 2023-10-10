package store.product.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class ProductDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4019176613604179521L;
	
	
	private String productId;
	private String productName;
	private String sellerName;
	private int price;
	private int deliveryCharge;
	private String detailImage;
	private String productDescription;
	private Date productRegistDate;
	private String parentCategoryId;
	private String categoryName;
	private String sellerContact;
	private String sellerAddress;
	private String sellerNo;
	private String businissNo;
	private String categoryId;
	private String thumbnailImg;
	private String productDetailImg;
	private String exposureYn;
	
	public ProductDetail() {
		super();
	}

	public ProductDetail(String productId, String productName, String sellerName, int price, int deliveryCharge,
			String detailImage, String productDescription, Date productRegistDate, String parentCategoryId,
			String categoryName, String sellerContact, String sellerAddress, String sellerNo, String businissNo,
			String categoryId, String thumbnailImg, String productDetailImg, String exposureYn) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.sellerName = sellerName;
		this.price = price;
		this.deliveryCharge = deliveryCharge;
		this.detailImage = detailImage;
		this.productDescription = productDescription;
		this.productRegistDate = productRegistDate;
		this.parentCategoryId = parentCategoryId;
		this.categoryName = categoryName;
		this.sellerContact = sellerContact;
		this.sellerAddress = sellerAddress;
		this.sellerNo = sellerNo;
		this.businissNo = businissNo;
		this.categoryId = categoryId;
		this.thumbnailImg = thumbnailImg;
		this.productDetailImg = productDetailImg;
		this.exposureYn = exposureYn;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
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

	public String getDetailImage() {
		return detailImage;
	}

	public void setDetailImage(String detailImage) {
		this.detailImage = detailImage;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Date getProductRegistDate() {
		return productRegistDate;
	}

	public void setProductRegistDate(Date productRegistDate) {
		this.productRegistDate = productRegistDate;
	}

	public String getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSellerContact() {
		return sellerContact;
	}

	public void setSellerContact(String sellerContact) {
		this.sellerContact = sellerContact;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public String getSellerNo() {
		return sellerNo;
	}

	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}

	public String getBusinissNo() {
		return businissNo;
	}

	public void setBusinissNo(String businissNo) {
		this.businissNo = businissNo;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getThumbnailImg() {
		return thumbnailImg;
	}

	public void setThumbnailImg(String thumbnailImg) {
		this.thumbnailImg = thumbnailImg;
	}

	public String getProductDetailImg() {
		return productDetailImg;
	}

	public void setProductDetailImg(String productDetailImg) {
		this.productDetailImg = productDetailImg;
	}

	public String getExposureYn() {
		return exposureYn;
	}

	public void setExposureYn(String exposureYn) {
		this.exposureYn = exposureYn;
	}

	@Override
	public String toString() {
		return "ProductDetail [productId=" + productId + ", productName=" + productName + ", sellerName=" + sellerName
				+ ", price=" + price + ", deliveryCharge=" + deliveryCharge + ", detailImage=" + detailImage
				+ ", productDescription=" + productDescription + ", productRegistDate=" + productRegistDate
				+ ", parentCategoryId=" + parentCategoryId + ", categoryName=" + categoryName + ", sellerContact="
				+ sellerContact + ", sellerAddress=" + sellerAddress + ", sellerNo=" + sellerNo + ", businissNo="
				+ businissNo + ", categoryId=" + categoryId + ", thumbnailImg=" + thumbnailImg + ", productDetailImg="
				+ productDetailImg + ", exposureYn=" + exposureYn + "]";
	}


}
