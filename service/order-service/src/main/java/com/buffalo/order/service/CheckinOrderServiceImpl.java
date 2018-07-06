
package com.buffalo.order.service;

import com.buffalo.message.OperateLogMessageSender;
import com.buffalo.order.mapper.CheckinOrderMapper;
import com.buffalo.order.model.CheckinOrder;
import com.buffalo.order.model.CheckinOrderItem;
import com.buffalo.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CheckinOrderServiceImpl implements CheckinOrderService {
	
	@Autowired
	private CheckinOrderMapper checkinOrderMapper;
	
	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<CheckinOrder> list(String keyword) throws Exception {
		CheckinOrder checkinOrder = new CheckinOrder();
		checkinOrder.setKeyword(keyword);
		return checkinOrderMapper.list(checkinOrder);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public CheckinOrder add(CheckinOrder checkinOrder) throws Exception {
		String checkinOrderId = UUIDUtil.getUUID();
		checkinOrder.setId(checkinOrderId);
		checkinOrderMapper.add(checkinOrder);
		for(CheckinOrderItem checkinOrderItem :checkinOrder.getItemList()){
			checkinOrderItem.setCheckin_order_id(checkinOrderId);
			checkinOrderMapper.addCheckinOrderItem(checkinOrderItem);
		}
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"进仓单","添加进仓单："+checkinOrder.getCheckin_date());
		return checkinOrder;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void update(CheckinOrder checkinOrder) throws Exception {
		checkinOrderMapper.update(checkinOrder);
		for(CheckinOrderItem checkinOrderItem :checkinOrder.getItemList()){
			checkinOrderMapper.updateCheckinOrderItem(checkinOrderItem);
		}
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"进仓单","修改进仓单："+checkinOrder.getCheckin_date());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(CheckinOrder checkinOrder) throws Exception {
		checkinOrderMapper.delete(checkinOrder);
		checkinOrderMapper.deleteItemList(checkinOrder.getId());
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"进仓单","删除进仓单："+checkinOrder.getCheckin_date());
	}


}
