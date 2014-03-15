package app.model;

public class Inventory {

	private String ItemCode;
	private String ItemName;
	private String ItemBrand;
	private String ItemStyle;
	private String ItemCategory;
	private String Size;
	private int QuantityAvailable;
	private int Price;
	private String ItemColor;
	
	public String getItemStyle() {
		return ItemStyle;
	}
	public void setItemStyle(String itemStyle) {
		ItemStyle = itemStyle;
	}
	public String getItemCategory() {
		return ItemCategory;
	}
	public void setItemCategory(String itemCategory) {
		ItemCategory = itemCategory;
	}
	public String getItemColor() {
		return ItemColor;
	}
	public void setItemColor(String itemColor) {
		ItemColor = itemColor;
	}
	public int getQuantityAvailable() {
		return QuantityAvailable;
	}
	public void setQuantityAvailable(int quantityAvailable) {
		QuantityAvailable = quantityAvailable;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
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
	public String getItemBrand() {
		return ItemBrand;
	}
	public void setItemBrand(Object object) {
		ItemBrand = (String) object;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	
	
	
	
}
