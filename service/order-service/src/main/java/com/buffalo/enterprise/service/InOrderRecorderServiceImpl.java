
package com.buffalo.enterprise.service;

import com.buffalo.enterprise.mapper.InOrderRecorderMapper;
import com.buffalo.enterprise.model.InOrderRecorder;
import com.buffalo.message.OperateLogMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class InOrderRecorderServiceImpl implements InOrderRecorderService {
	
	@Autowired
	private InOrderRecorderMapper inOrderRecorderMapper;
	
	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<InOrderRecorder> list(String keyword) throws Exception {
		InOrderRecorder inOrderRecorder = new InOrderRecorder();
		inOrderRecorder.setKeyword(keyword);
		return inOrderRecorderMapper.list(inOrderRecorder);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public InOrderRecorder add(InOrderRecorder inOrderRecorder) throws Exception {
		inOrderRecorderMapper.add(inOrderRecorder);
		return inOrderRecorder;
	}

	@Override
	public void update(InOrderRecorder inOrderRecorder) throws Exception {
		inOrderRecorderMapper.update(inOrderRecorder);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(InOrderRecorder inOrderRecorder) throws Exception {
		inOrderRecorderMapper.delete(inOrderRecorder);
	}


}
