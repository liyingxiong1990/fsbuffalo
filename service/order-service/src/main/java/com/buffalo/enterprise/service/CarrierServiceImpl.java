
package com.buffalo.enterprise.service;

import com.buffalo.enterprise.mapper.CarrierMapper;
import com.buffalo.enterprise.model.Carrier;
import com.buffalo.message.OperateLogMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CarrierServiceImpl implements CarrierService {
	
	@Autowired
	private CarrierMapper carrierMapper;
	
	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<Carrier> list(String keyword) throws Exception {
		Carrier carrier = new Carrier();
		carrier.setKeyword(keyword);
		return carrierMapper.list(carrier);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Carrier add(Carrier carrier) throws Exception {
		carrierMapper.add(carrier);

		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"缴仓","添加缴仓："+carrier.getName());
		return carrier;
	}

	@Override
	public void update(Carrier carrier) throws Exception {
		carrierMapper.update(carrier);
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"缴仓","修改缴仓："+carrier.getName());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Carrier carrier) throws Exception {
		carrierMapper.delete(carrier);
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"缴仓","删除缴仓："+carrier.getName());
	}


}
