
package com.buffalo.gateway.enterprise.model;
import com.buffalo.gateway.util.ModelUtil;

public class Deliverer extends ModelUtil {

	private String id;
	private String name;
	private String delivery_lines;
	private String phone;


	public Deliverer() {
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

	public String getDelivery_lines() {
		return delivery_lines;
	}

	public void setDelivery_lines(String delivery_lines) {
		this.delivery_lines = delivery_lines;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
