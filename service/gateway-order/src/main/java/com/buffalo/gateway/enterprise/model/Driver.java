
package com.buffalo.gateway.enterprise.model;
import com.buffalo.gateway.util.ModelUtil;

public class Driver extends ModelUtil {

	private String id;
	private String name;
	private String delivery_line;
	private String phone;

	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDelivery_line() {
		return delivery_line;
	}

	public void setDelivery_line(String delivery_line) {
		this.delivery_line = delivery_line;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
