
package com.buffalo.enterprise.model;
import com.buffalo.util.ModelUtil;

import java.util.List;

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
