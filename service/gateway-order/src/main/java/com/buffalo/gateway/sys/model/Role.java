
package com.buffalo.gateway.sys.model;

import com.buffalo.gateway.util.ModelUtil;


public class Role extends ModelUtil {
    private String role_id;
	private String name;

    public Role() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
