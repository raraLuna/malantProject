package store.main.model.vo;

import java.sql.Date;

public class MainContent implements java.io.Serializable {

	private static final long serialVersionUID = -3419475723523027584L;
	
	// 배너관련
	private String bannerImage;
	private int bannerPrority;
	private String bannerLink;
	private Date bannerStart;
	private Date bannerEnd;
	private int viewcount;
	
	//상품관련
	private String productThumbnail;
	private int price;
	private String productName;
	private int productId;
	private String exposureYn;
	private String sellerNo;
	
	
	public MainContent() {
		super();
	}


	public MainContent(String bannerImage, int bannerPrority, String bannerLink, Date bannerStart, Date bannerEnd,
			int viewcount, String productThumbnail, int price, String productName, int productId, String exposureYn, String sellerNo) {
		super();
		this.bannerImage = bannerImage;
		this.bannerPrority = bannerPrority;
		this.bannerLink = bannerLink;
		this.bannerStart = bannerStart;
		this.bannerEnd = bannerEnd;
		this.viewcount = viewcount;
		this.productThumbnail = productThumbnail;
		this.price = price;
		this.productName = productName;
		this.productId = productId;
		this.exposureYn = exposureYn;
		this.sellerNo = sellerNo;
	}


	public String getBannerImage() {
		return bannerImage;
	}


	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}


	public int getBannerPrority() {
		return bannerPrority;
	}


	public void setBannerPrority(int bannerPrority) {
		this.bannerPrority = bannerPrority;
	}


	public String getBannerLink() {
		return bannerLink;
	}


	public void setBannerLink(String bannerLink) {
		this.bannerLink = bannerLink;
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


	public String getProductThumbnail() {
		return productThumbnail;
	}


	public void setProductThumbnail(String productThumbnail) {
		this.productThumbnail = productThumbnail;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getExposureYn() {
		return exposureYn;
	}


	public void setExposureYn(String exposureYn) {
		this.exposureYn = exposureYn;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

	public String getSellerNo() {
		return sellerNo;
	}


	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}


	@Override
	public String toString() {
		return "MainContent [bannerImage=" + bannerImage + ", bannerPrority=" + bannerPrority + ", bannerLink="
				+ bannerLink + ", bannerStart=" + bannerStart + ", bannerEnd=" + bannerEnd + ", viewcount=" + viewcount
				+ ", productThumbnail=" + productThumbnail + ", price=" + price + ", productName=" + productName
				+ ", productId=" + productId + ", exposureYn=" + exposureYn + "]" + "sellerNo = " + sellerNo;
	}
	
}
