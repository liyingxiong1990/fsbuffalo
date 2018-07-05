
package com.buffalo.enterprise.model;
import com.buffalo.util.ModelUtil;

public class Carrier extends ModelUtil {

	private String id;
	private String name;

	public Carrier() {
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
}
