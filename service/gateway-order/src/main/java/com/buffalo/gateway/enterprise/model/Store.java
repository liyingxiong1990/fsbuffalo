
package com.buffalo.gateway.enterprise.model;
import com.buffalo.gateway.util.ModelUtil;

import java.util.List;

public class Store extends ModelUtil {

	private String id;
	private String store_name;
	private String store_holder;
	private String address;
	private String phone;
	private String line_id;
	private List<StorePrice> priceList;

	private String line;

	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStore_holder() {
		return store_holder;
	}

	public void setStore_holder(String store_holder) {
		this.store_holder = store_holder;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLine_id() {
		return line_id;
	}

	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}

	public List<StorePrice> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<StorePrice> priceList) {
		this.priceList = priceList;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
}
