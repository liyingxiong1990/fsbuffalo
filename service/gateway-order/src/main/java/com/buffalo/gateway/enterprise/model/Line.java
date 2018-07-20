
package com.buffalo.gateway.enterprise.model;
import com.buffalo.gateway.util.ModelUtil;

public class Line extends ModelUtil {

	private String id;
	private String name;


	public Line() {
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
