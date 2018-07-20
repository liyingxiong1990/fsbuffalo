
package com.buffalo.gateway.authentication.model;

import com.buffalo.gateway.util.ModelUtil;

import java.util.List;

public class RoleUser extends ModelUtil {
    private String role_user_id;
	private String user_id;
	private String role_id;
	private List<String> userIdList = null;

    public RoleUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getRole_user_id() {
        return role_user_id;
    }

    public void setRole_user_id(String role_user_id) {
        this.role_user_id = role_user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }


}
