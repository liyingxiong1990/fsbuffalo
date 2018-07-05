
package com.buffalo.util;

import java.sql.Timestamp;

public class ModelUtil {
	private String creator_id; // 创建者
	private Timestamp created_time; // 创建时间 
	private String modifier_id; // 修改者】
	private Timestamp modified_time; // 修改时间
	private Integer sys_status;
	private String remarks;
	
	public String getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}
	public Timestamp getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Timestamp created_time) {
		this.created_time = created_time;
	}
	public String getModifier_id() {
		return modifier_id;
	}
	public void setModifier_id(String modifier_id) {
		this.modifier_id = modifier_id;
	}
	public Timestamp getModified_time() {
		return modified_time;
	}
	public void setModified_time(Timestamp modified_time) {
		this.modified_time = modified_time;
	}

	public Integer getSys_status() {
		return sys_status;
	}
	public void setSys_status(Integer sys_status) {
		this.sys_status = sys_status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	public ModelUtil(String creator_id, Timestamp created_time, String modifier_id, Timestamp modified_time,
			Integer sys_status, String remarks) {
		super();
		this.creator_id = creator_id;
		this.created_time = created_time;
		this.modifier_id = modifier_id;
		this.modified_time = modified_time;
		this.sys_status = sys_status;
		this.remarks = remarks;
	}
	public ModelUtil() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
