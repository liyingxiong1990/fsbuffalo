
package com.buffalo.enterprise.service;

import com.buffalo.enterprise.mapper.DelivererMapper;
import com.buffalo.enterprise.model.Deliverer;
import com.buffalo.message.OperateLogMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class DelivererServiceImpl implements DelivererService {
	
	@Autowired
	private DelivererMapper delivererMapper;
	
	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<Deliverer> list(String keyword) throws Exception {
		Deliverer deliverer = new Deliverer();
		deliverer.setKeyword(keyword);
		return delivererMapper.list(deliverer);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Deliverer add(Deliverer deliverer) throws Exception {
		delivererMapper.add(deliverer);

		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"外县市司机","添加外县市司机："+deliverer.getName());
		return deliverer;
	}

	@Override
	public void update(Deliverer deliverer) throws Exception {
		delivererMapper.update(deliverer);
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"外县市司机","修改外县市司机："+deliverer.getName());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Deliverer deliverer) throws Exception {
		delivererMapper.delete(deliverer);
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"外县市司机","删除外县市司机："+deliverer.getName());
	}


}
