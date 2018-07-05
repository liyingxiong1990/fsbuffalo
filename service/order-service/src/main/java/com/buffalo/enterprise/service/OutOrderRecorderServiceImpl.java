
package com.buffalo.enterprise.service;

import com.buffalo.enterprise.mapper.OutOrderRecorderMapper;
import com.buffalo.enterprise.model.OutOrderRecorder;
import com.buffalo.message.OperateLogMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OutOrderRecorderServiceImpl implements OutOrderRecorderService {
	
	@Autowired
	private OutOrderRecorderMapper outOrderRecorderMapper;
	
	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<OutOrderRecorder> list(String keyword) throws Exception {
		OutOrderRecorder outOrderRecorder = new OutOrderRecorder();
		outOrderRecorder.setKeyword(keyword);
		return outOrderRecorderMapper.list(outOrderRecorder);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public OutOrderRecorder add(OutOrderRecorder outOrderRecorder) throws Exception {
		outOrderRecorderMapper.add(outOrderRecorder);

		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"开单人","添加开单人："+outOrderRecorder.getName());
		return outOrderRecorder;
	}

	@Override
	public void update(OutOrderRecorder outOrderRecorder) throws Exception {
		outOrderRecorderMapper.update(outOrderRecorder);
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"开单人","修改开单人："+outOrderRecorder.getName());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(OutOrderRecorder outOrderRecorder) throws Exception {
		outOrderRecorderMapper.delete(outOrderRecorder);
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"开单人","删除开单人："+outOrderRecorder.getName());
	}


}
