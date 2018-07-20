
package com.buffalo.gateway.enterprise.service;

import com.buffalo.gateway.enterprise.mapper.CarrierMapper;
import com.buffalo.gateway.enterprise.model.Carrier;
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
		return carrier;
	}

	@Override
	public void update(Carrier carrier) throws Exception {
		carrierMapper.update(carrier);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Carrier carrier) throws Exception {
		carrierMapper.delete(carrier);
	}


}
