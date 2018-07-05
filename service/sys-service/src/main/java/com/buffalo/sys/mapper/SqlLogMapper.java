
package com.buffalo.sys.mapper;

import com.buffalo.sys.model.SqlLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlLogMapper {

    public List<SqlLog> list();

    public SqlLog getById(String id);

    public void add(SqlLog sqlLog);
}
