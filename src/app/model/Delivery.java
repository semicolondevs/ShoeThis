package app.model;

public class Delivery {
	Inventory i = new Inventory();
	
	private int deliveryNo;
	private String ItemCode =i.getItemCode();
	private String ItemName =i.getItemName();
	private int Quantity;
	private int totalPrice=i.getPrice();
	private String Address;
	private String DateDelivered;
	private String Receipient;
	
	public String getDateDelivered() {
		return DateDelivered;
	}
	public void setDateDelivered(String dateDelivered) {
		DateDelivered = dateDelivered;
	}
	
	public String getReceipient() {
		return Receipient;
	}
	public void setReceipient(String receipient) {
		Receipient = receipient;
	}
	public int getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(int deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public String getItemCode() {
		return ItemCode;
	}
	public void setItemCode(String itemCode) {
		ItemCode = itemCode;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	

}
