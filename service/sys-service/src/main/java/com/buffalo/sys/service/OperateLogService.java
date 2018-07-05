
package com.buffalo.sys.service;


import com.buffalo.sys.model.OperateLog;

import java.util.List;

public interface OperateLogService {

    public List<OperateLog> getSdcLogList() throws Exception;

    public List<OperateLog> getBossLogList() throws Exception;

    public OperateLog getById(String operate_log_id) throws Exception;

    public void addSDCLog(String userId, String operateType, String content) throws Exception;

    public void addBOSSLog(String userId, String operateType, String content) throws Exception;


}
