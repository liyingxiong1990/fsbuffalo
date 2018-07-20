
package com.buffalo.gateway.enterprise.service;

import com.buffalo.gateway.enterprise.mapper.OutOrderRecorderMapper;
import com.buffalo.gateway.enterprise.model.OutOrderRecorder;
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
		return outOrderRecorder;
	}

	@Override
	public void update(OutOrderRecorder outOrderRecorder) throws Exception {
		outOrderRecorderMapper.update(outOrderRecorder);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(OutOrderRecorder outOrderRecorder) throws Exception {
		outOrderRecorderMapper.delete(outOrderRecorder);
	}


}
