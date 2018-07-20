
package com.buffalo.gateway.enterprise.service;

import com.buffalo.gateway.enterprise.mapper.DelivererMapper;
import com.buffalo.gateway.enterprise.model.Deliverer;
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
		return deliverer;
	}

	@Override
	public void update(Deliverer deliverer) throws Exception {
		delivererMapper.update(deliverer);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Deliverer deliverer) throws Exception {
		delivererMapper.delete(deliverer);
	}


}
