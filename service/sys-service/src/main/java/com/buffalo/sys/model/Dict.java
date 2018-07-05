
package com.buffalo.sys.model;


import com.buffalo.util.ModelUtil;

public class Dict extends ModelUtil {
	private int dict_id;
	private String dict_type; // 字典类型
	private String dict_type_desc; // 字典类型说明
	private String dict_code; // 字典代码
	private String dict_value;  // 字典描述
	private int sort; // 排序
	
	public Dict() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDict_id() {
		return dict_id;
	}

	public void setDict_id(int dict_id) {
		this.dict_id = dict_id;
	}

	public String getDict_type() {
		return dict_type;
	}
	public void setDict_type(String dict_type) {
		this.dict_type = dict_type;
	}
	public String getDict_type_desc() {
		return dict_type_desc;
	}
	public void setDict_type_desc(String dict_type_desc) {
		this.dict_type_desc = dict_type_desc;
	}
	public String getDict_code() {
		return dict_code;
	}
	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}
	public String getDict_value() {
		return dict_value;
	}
	public void setDict_value(String dict_value) {
		this.dict_value = dict_value;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
}
