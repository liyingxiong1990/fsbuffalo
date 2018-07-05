
package com.buffalo.sys.model;

import com.buffalo.util.ModelUtil;

public class SqlLog extends ModelUtil {
	private String sql_log_id;
	private String bound_sql;
	private String raw_sql;
	private String parameters;
	private String result;


	public SqlLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSql_log_id() {
		return sql_log_id;
	}

	public void setSql_log_id(String sql_log_id) {
		this.sql_log_id = sql_log_id;
	}

	public String getBound_sql() {
		return bound_sql;
	}

	public void setBound_sql(String bound_sql) {
		this.bound_sql = bound_sql;
	}

	public String getRaw_sql() {
		return raw_sql;
	}

	public void setRaw_sql(String raw_sql) {
		this.raw_sql = raw_sql;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
