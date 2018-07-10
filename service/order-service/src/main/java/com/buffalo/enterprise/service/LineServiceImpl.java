
package com.buffalo.enterprise.service;

import com.buffalo.enterprise.mapper.LineMapper;
import com.buffalo.enterprise.model.Line;
import com.buffalo.message.OperateLogMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class LineServiceImpl implements LineService {
	
	@Autowired
	private LineMapper lineMapper;
	
	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<Line> list(String keyword) throws Exception {
		Line line = new Line();
		line.setKeyword(keyword);
		return lineMapper.list(line);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Line add(Line line) throws Exception {
		lineMapper.add(line);

		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"路线","添加路线："+line.getName());
		return line;
	}

	@Override
	public void update(Line line) throws Exception {
		lineMapper.update(line);
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"路线","修改路线："+line.getName());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Line line) throws Exception {
		lineMapper.delete(line);
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"路线","删除路线："+line.getName());
	}


}
