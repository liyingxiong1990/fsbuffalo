
package com.buffalo.enterprise.model;
import com.buffalo.util.ModelUtil;

import java.util.Date;
import java.util.List;

public class Driver extends ModelUtil {

	private String id;
	private String driver_name;
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

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
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
