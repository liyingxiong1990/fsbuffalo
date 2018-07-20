
package com.buffalo.gateway.sys.service;

import com.buffalo.gateway.sys.model.SqlLog;

import java.util.List;

public interface SqlLogService {


    public List<SqlLog> list() throws Exception;
    
    public SqlLog getById(String id) throws Exception;

    public void add(SqlLog sqlLog) throws Exception;

    
}
