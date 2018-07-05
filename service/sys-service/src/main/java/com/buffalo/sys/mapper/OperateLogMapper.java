
package com.buffalo.sys.mapper;
import com.buffalo.sys.model.OperateLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperateLogMapper {

    List<OperateLog> getSdcLogList() throws Exception;

    List<OperateLog> getBossLogList() throws Exception;

    public OperateLog getById(String operate_log_id) throws Exception;

    public void addSDCLog(OperateLog operateLog) throws Exception;

    public void addBOSSLog(OperateLog operateLog) throws Exception;

}
