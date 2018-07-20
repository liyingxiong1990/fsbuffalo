
package com.buffalo.order.model;
import com.buffalo.util.ModelUtil;

import java.util.Date;
import java.util.List;

public class Inventory extends ModelUtil {

	private String id;
	private Date inventory_date;
	private Date last_date;
	private List<InventoryItem> itemList;

	private Date bound_time;

	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getInventory_date() {
		return inventory_date;
	}

	public void setInventory_date(Date inventory_date) {
		this.inventory_date = inventory_date;
	}

	public Date getLast_date() {
		return last_date;
	}

	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}

	public List<InventoryItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<InventoryItem> itemList) {
		this.itemList = itemList;
	}

	public Date getBound_time() {
		return bound_time;
	}

	public void setBound_time(Date bound_time) {
		this.bound_time = bound_time;
	}
}
