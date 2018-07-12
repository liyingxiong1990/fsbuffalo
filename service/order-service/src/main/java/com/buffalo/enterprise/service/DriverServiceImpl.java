
package com.buffalo.enterprise.service;

import com.buffalo.enterprise.mapper.DriverMapper;
import com.buffalo.enterprise.model.Driver;
import com.buffalo.message.OperateLogMessageSender;
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
	private OperateLogMessageSender operateLogMessageSender;

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

		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"专卖店司机","添加专卖店司机："+driver.getName());
		return driver;
	}

	@Override
	public void update(Driver driver) throws Exception {
		driverMapper.update(driver);
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"专卖店司机","修改专卖店司机："+driver.getName());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Driver driver) throws Exception {
		driverMapper.delete(driver);
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"专卖店司机","删除专卖店司机："+driver.getName());
	}


}
