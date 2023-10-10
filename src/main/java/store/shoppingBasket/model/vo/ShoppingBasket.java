package store.shoppingBasket.model.vo;

public class ShoppingBasket {
		private String productId;
		private String userNo;
		private int quantity;
		private int price;
		private int deliveryChage;
		private int totalPrice;
		private String productThumnail;
		private String productName;
		private String storeName;
		
		
		public ShoppingBasket() {
			super();
		}


		public ShoppingBasket(String productId, String userNo, int quantity, int price, int deliveryChage,
				int totalPrice, String productThumnail, String productName, String storeName) {
			super();
			this.productId = productId;
			this.userNo = userNo;
			this.quantity = quantity;
			this.price = price;
			this.deliveryChage = deliveryChage;
			this.totalPrice = totalPrice;
			this.productThumnail = productThumnail;
			this.productName = productName;
			this.storeName = storeName;
		}


		public String getStoreName() {
			return storeName;
		}


		public void setStoreName(String storeName) {
			this.storeName = storeName;
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


		public int getPrice() {
			return price;
		}


		public void setPrice(int price) {
			this.price = price;
		}


		public int getDeliveryChage() {
			return deliveryChage;
		}


		public void setDeliveryChage(int deliveryChage) {
			this.deliveryChage = deliveryChage;
		}


		public int getTotalPrice() {
			return totalPrice;
		}


		public void setTotalPrice(int totalPrice) {
			this.totalPrice = totalPrice;
		}


		public String getProductThumnail() {
			return productThumnail;
		}


		public void setProductThumnail(String productThumnail) {
			this.productThumnail = productThumnail;
		}


		public String getProductName() {
			return productName;
		}


		public void setProductName(String productName) {
			this.productName = productName;
		}

		@Override
		public String toString() {
			return "ShoppingBasket [productId=" + productId + ", userNo=" + userNo + ", quantity=" + quantity
					+ ", price=" + price + ", deliveryChage=" + deliveryChage + ", totalPrice=" + totalPrice
					+ ", productThumnail=" + productThumnail + ", productName=" + productName + ", storeName=" + storeName + "]";
		}
}