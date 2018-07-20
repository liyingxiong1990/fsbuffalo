
package com.buffalo.gateway.sys.model;

import com.buffalo.gateway.util.ModelUtil;

import java.sql.Timestamp;

public class User extends ModelUtil {
	private String user_id;
	private String login_name;
	private String pwd;
	private Timestamp expired_date; // 失效日期
	private String nickname; // 昵称（显示名称）
	private String mobile;
	private String email;
	private int dept_id; // 所属部门
	private int user_status; // 用户状态 1-正常 2-注销
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Timestamp getExpired_date() {
		return expired_date;
	}
	public void setExpired_date(Timestamp expired_date) {
		this.expired_date = expired_date;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public int getUser_status() {
		return user_status;
	}
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
}
