package com.buffalo.gateway.sys.service;

import com.buffalo.gateway.sys.mapper.OperateLogMapper;
import com.buffalo.gateway.sys.model.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("operateLogService")
public class OperateLogServiceImpl implements OperateLogService {
	
	@Autowired
	private OperateLogMapper operateLogMapper;
	
	@Override
	public List<OperateLog> getSdcLogList() throws Exception {
		return operateLogMapper.getSdcLogList();
	}

	@Override
	public List<OperateLog> getBossLogList() throws Exception {
		return operateLogMapper.getBossLogList();
	}

	@Override
	public OperateLog getById(String order_log_id) throws Exception {
		return operateLogMapper.getById(order_log_id);
	}

	@Override
	public void addSDCLog(String userId, String operateType, String content) throws Exception {
		OperateLog operateLog = new OperateLog();
		operateLog.setOperator_user_id(userId);
		operateLog.setOperate_type(operateType);
		operateLog.setOperate_content(content);
		operateLogMapper.addSDCLog(operateLog);
	}

	@Override
	public void addBOSSLog(String userId, String operateType, String content) throws Exception {
		OperateLog operateLog = new OperateLog();
		operateLog.setOperator_user_id(userId);
		operateLog.setOperate_type(operateType);
		operateLog.setOperate_content(content);
		operateLogMapper.addBOSSLog(operateLog);
	}


}
