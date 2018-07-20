
package com.buffalo.gateway.sys.model;

import com.buffalo.gateway.util.ModelUtil;


public class RolePermission extends ModelUtil {
    private String role_permission_id;
    private String role_id;
	private String permission_id;
	private String[] role_permissions;

    public RolePermission() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getRole_permission_id() {
        return role_permission_id;
    }

    public void setRole_permission_id(String role_permission_id) {
        this.role_permission_id = role_permission_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(String permission_id) {
        this.permission_id = permission_id;
    }

	public String[] getRole_permissions() {
		return role_permissions;
	}

	public void setRole_permissions(String[] role_permissions) {
		this.role_permissions = role_permissions;
	}


    
    

}
