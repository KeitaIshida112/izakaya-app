package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu {

	private String menuId;
	private String menuName;
	private String menuCategory;
	private int price;
	private int stock;
	private String orderTime;
	private int count;
	private String tableNo;
	
	public Menu() {}
	
	public Menu(String menuId,String menuName,String menuCategory,int price,int stock) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuCategory = menuCategory;
		this.price = price;
		this.stock = stock;
		this.count = 0;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH/mm/ss");
		this.orderTime = sdf.format(date);
	}
	
	public int getCount() {
		return count;
	}
	
	

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCategory() {
		return menuCategory;
	}

	public void setMenuCategory(String menuCategory) {
		this.menuCategory = menuCategory;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	
	
}


