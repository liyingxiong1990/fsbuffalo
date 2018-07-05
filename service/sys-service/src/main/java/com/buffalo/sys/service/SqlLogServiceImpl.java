
package com.buffalo.sys.service;

import com.buffalo.sys.mapper.SqlLogMapper;
import com.buffalo.sys.model.SqlLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("sqlLogService")
public class SqlLogServiceImpl implements SqlLogService {

	@Autowired
	private SqlLogMapper sqlLogMapper;

	@Autowired
	private HttpServletRequest request;

	@Override
	public SqlLog getById(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlLogMapper.getById(id);
	}

	@Override
	public void add(SqlLog sqlLog) throws Exception {
		// TODO Auto-generated method stub
		sqlLogMapper.add(sqlLog);
	}

	@Override
	public List<SqlLog> list() throws Exception {
		return sqlLogMapper.list();
	}


}
