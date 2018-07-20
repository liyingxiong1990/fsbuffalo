
package com.buffalo.gateway.order.service;


import com.buffalo.gateway.enterprise.model.Product;
import com.buffalo.gateway.enterprise.service.ProductService;
import com.buffalo.gateway.order.mapper.WarehouseOrderMapper;
import com.buffalo.gateway.order.model.*;
import com.buffalo.gateway.util.UUIDUtil;
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
public class WarehouseOrderServiceImpl implements WarehouseOrderService {
	
	@Autowired
	private WarehouseOrderMapper warehouseOrderMapper;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private DelivererOrderService delivererOrderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<WarehouseOrder> list(String keyword) throws Exception {
		WarehouseOrder warehouseOrder = new WarehouseOrder();
		warehouseOrder.setKeyword(keyword);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		warehouseOrder.setBound_time(calendar.getTime());
		return warehouseOrderMapper.list(warehouseOrder);
	}

	@Override
	public List<WarehouseOrder> today(String keyword) throws Exception {
		WarehouseOrder warehouseOrder = new WarehouseOrder();
		warehouseOrder.setKeyword(keyword);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		warehouseOrder.setBound_time(calendar.getTime());
		return warehouseOrderMapper.list(warehouseOrder);
	}

	@Override
	public WarehouseOrder getById(String id) throws Exception {
		WarehouseOrder warehouseOrder =  warehouseOrderMapper.getById(id);
		warehouseOrder.setItemList(warehouseOrderMapper.getItemListByWarehouseOrderId(id));
		return  warehouseOrder;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public WarehouseOrder add(WarehouseOrder warehouseOrder) throws Exception {
		Date orderDate = warehouseOrder.getOrder_date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Inventory inventory = inventoryService.getInventoryByInventoryDate(orderDate);
		if(inventory==null){
			throw new Exception(sdf.format(orderDate)+"库存记录不存在！");
		}
		String inventoryId = inventory.getId();
		String warehouseOrderId =  sdf.format(orderDate)+"-"+ UUIDUtil.getUUID().substring(0,10);
		warehouseOrder.setId(warehouseOrderId);
		warehouseOrderMapper.add(warehouseOrder);
		DelivererOrder delivererOrderQuery = new DelivererOrder();
		if(warehouseOrder.getType().equals("driver")){
			delivererOrderQuery.setOrder_date(warehouseOrder.getOrder_date());
			delivererOrderQuery.setDriver_id(warehouseOrder.getDeliverer_id());
			delivererOrderQuery.setLine_id(warehouseOrder.getLine_id());
			DelivererOrder delivererOrder= delivererOrderService.getByDateDriverLine(delivererOrderQuery);
			if(delivererOrder==null || delivererOrder.getItemList().size()==0){
				throw new Exception("没查到相关的送货单记录！");
			}
			List<WarehouseOrderItem> warehouseOrderItemList = new ArrayList<WarehouseOrderItem>();
			for(DelivererOrderItem delivererOrderItem : delivererOrder.getItemList()){
				WarehouseOrderItem warehouseOrderItem = new WarehouseOrderItem();
				warehouseOrderItem.setProduct_id(delivererOrderItem.getProduct_id());
				warehouseOrderItem.setQuantity(delivererOrderItem.getQuantity());
				warehouseOrderItemList.add(warehouseOrderItem);
			}
			warehouseOrder.setItemList(warehouseOrderItemList);
		}

		for(WarehouseOrderItem warehouseOrderItem :warehouseOrder.getItemList()){
			int quantity = warehouseOrderItem.getQuantity();
			Product product = productService.getById(warehouseOrderItem.getProduct_id());
			int scale = product.getScale();
			int numberOfBoxes = quantity/scale;
			int remainder = quantity%scale;
			warehouseOrderItem.setWarehouse_order_id(warehouseOrderId);
			warehouseOrderItem.setNumber_of_boxes(numberOfBoxes);
			warehouseOrderItem.setRemainder(remainder);
			warehouseOrderMapper.addWarehouseOrderItem(warehouseOrderItem);
			String productId = warehouseOrderItem.getProduct_id();
			inventoryService.decreaseItemQuantity(inventoryId,productId,quantity);

		}
		if(warehouseOrder.getType().equals("driver")) {
			delivererOrderService.setOut(delivererOrderQuery);
		}
		return warehouseOrder;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(WarehouseOrder warehouseOrder) throws Exception {
		warehouseOrderMapper.delete(warehouseOrder);
		warehouseOrderMapper.deleteItemList(warehouseOrder.getId());
	}


}
