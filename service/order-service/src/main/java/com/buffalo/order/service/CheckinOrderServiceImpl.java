
package com.buffalo.order.service;

import com.buffalo.enterprise.model.Product;
import com.buffalo.enterprise.service.ProductService;
import com.buffalo.message.OperateLogMessageSender;
import com.buffalo.order.mapper.CheckinOrderMapper;
import com.buffalo.order.mapper.InventoryMapper;
import com.buffalo.order.model.CheckinOrder;
import com.buffalo.order.model.CheckinOrderItem;
import com.buffalo.order.model.Inventory;
import com.buffalo.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CheckinOrderServiceImpl implements CheckinOrderService {
	
	@Autowired
	private CheckinOrderMapper checkinOrderMapper;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<CheckinOrder> list(String keyword) throws Exception {
		CheckinOrder checkinOrder = new CheckinOrder();
		checkinOrder.setKeyword(keyword);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		checkinOrder.setBound_time(calendar.getTime());
		return checkinOrderMapper.list(checkinOrder);
	}

	@Override
	public CheckinOrder getById(String id) throws Exception {
		return checkinOrderMapper.getById(id);
	}

	@Override
	public CheckinOrder statistic(Date checkin_date) throws Exception {
		CheckinOrder result = new CheckinOrder();
		List<Product> productList = productService.list("");
		List<CheckinOrderItem> itemList = new ArrayList<CheckinOrderItem>();
		List<CheckinOrderItem> resultItemList = new ArrayList<CheckinOrderItem>();
		for(Product product: productList){
			CheckinOrderItem checkinOrderItem = new CheckinOrderItem();
			checkinOrderItem.setProduct_id(product.getId());
			checkinOrderItem.setProduct_index(product.getProduct_index());
			checkinOrderItem.setProduct_name(product.getName());
			checkinOrderItem.setProduct_scale(product.getScale());
			checkinOrderItem.setQuantity(0);
			checkinOrderItem.setId(UUIDUtil.getUUID());
			itemList.add(checkinOrderItem);
		}

		List<CheckinOrder> list = checkinOrderMapper.getListByCheckinDate(checkin_date);
		for(CheckinOrder checkinOrder : list){
			for(CheckinOrderItem checkinOrderItem : checkinOrder.getItemList()){
				for(CheckinOrderItem checkinOrderItemResult : itemList){
					if(checkinOrderItemResult.getProduct_id().equals(checkinOrderItem.getProduct_id()) ){
						checkinOrderItemResult.setQuantity(checkinOrderItemResult.getQuantity()+checkinOrderItem.getQuantity());
					}
				}
			}
		}

		for(CheckinOrderItem checkinOrderItem: itemList){
			if(checkinOrderItem.getQuantity()>0){
				resultItemList.add(checkinOrderItem);
			}
		}
		result.setCheckin_date(checkin_date);
		result.setItemList(resultItemList);
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public CheckinOrder add(CheckinOrder checkinOrder) throws Exception {
		Date checkinDate = checkinOrder.getCheckin_date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Inventory inventory = inventoryService.getInventoryByInventoryDate(checkinDate);
		if(inventory==null){
			throw new Exception(sdf.format(checkinDate)+"库存记录不存在！");
		}
		String inventoryId = inventory.getId();
		String checkinOrderId =  sdf.format(checkinDate)+"-"+ UUIDUtil.getUUID().substring(0,10);
		checkinOrder.setId(checkinOrderId);
		checkinOrderMapper.add(checkinOrder);
		for(CheckinOrderItem checkinOrderItem :checkinOrder.getItemList()){
			int quantity = checkinOrderItem.getQuantity();
			if(quantity>0){
				checkinOrderItem.setCheckin_order_id(checkinOrderId);
				checkinOrderMapper.addCheckinOrderItem(checkinOrderItem);
				String productId = checkinOrderItem.getProduct_id();
				inventoryService.increaseItemQuantity(inventoryId,productId,quantity);
			}
		}
		return checkinOrder;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void update(CheckinOrder checkinOrder) throws Exception {
		checkinOrderMapper.update(checkinOrder);
		for(CheckinOrderItem checkinOrderItem :checkinOrder.getItemList()){
			checkinOrderMapper.updateCheckinOrderItem(checkinOrderItem);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(CheckinOrder checkinOrder) throws Exception {
		checkinOrderMapper.delete(checkinOrder);
		checkinOrderMapper.deleteItemList(checkinOrder.getId());
	}


}
