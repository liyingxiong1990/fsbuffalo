package com.buffalo.gateway.sys.model;


import com.buffalo.gateway.util.ModelUtil;

public class Department extends ModelUtil {
	private int dept_id; // 机构代码
	private String dept_name; // 机构名称
	private int pid; // 父节点编号 
	private int dept_status; // 机构状态
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getDept_status() {
		return dept_status;
	}
	public void setDept_status(int dept_status) {
		this.dept_status = dept_status;
	}
}
