
package com.buffalo.gateway.sys.model;


import com.buffalo.gateway.util.ModelUtil;

public class OperateLog extends ModelUtil {
	private String operate_log_id; // log id
	private String operate_type; // 操作类型
	private String operator_user_id; // 操作人id
	private String operator_user_name; // 操作人
	private String operate_content;  //操作内容



	public OperateLog() {
		super();
	}

	public String getOperate_log_id() {
		return operate_log_id;
	}

	public void setOperate_log_id(String operate_log_id) {
		this.operate_log_id = operate_log_id;
	}

	public String getOperate_type() {
		return operate_type;
	}

	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}

	public String getOperator_user_id() {
		return operator_user_id;
	}

	public void setOperator_user_id(String operator_user_id) {
		this.operator_user_id = operator_user_id;
	}

	public String getOperator_user_name() {
		return operator_user_name;
	}

	public void setOperator_user_name(String operator_user_name) {
		this.operator_user_name = operator_user_name;
	}

	public String getOperate_content() {
		return operate_content;
	}

	public void setOperate_content(String operate_content) {
		this.operate_content = operate_content;
	}
}
