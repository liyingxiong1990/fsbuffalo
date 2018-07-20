
package com.buffalo.gateway.enterprise.service;

import com.buffalo.gateway.enterprise.mapper.DriverMapper;
import com.buffalo.gateway.enterprise.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
	
	@Autowired
	private DriverMapper driverMapper;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<Driver> list(String keyword) throws Exception {
		Driver driver = new Driver();
		driver.setKeyword(keyword);
		return driverMapper.list(driver);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Driver add(Driver driver) throws Exception {
		driverMapper.add(driver);
		return driver;
	}

	@Override
	public void update(Driver driver) throws Exception {
		driverMapper.update(driver);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Driver driver) throws Exception {
		driverMapper.delete(driver);
	}


}
