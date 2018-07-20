
package com.buffalo.gateway.sys.model;

import com.buffalo.gateway.util.ModelUtil;


public class Permission extends ModelUtil {
    private String permission_id;
	private String menu_id; //菜单id
	private String operation; //操作类型（DELETE、PUT、GET、POST）
	private String request_type; //请求类型
	private String api_url; //API请求路径


    public Permission() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(String permission_id) {
        this.permission_id = permission_id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

}
