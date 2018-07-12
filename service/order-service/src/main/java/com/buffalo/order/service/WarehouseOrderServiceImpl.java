
package com.buffalo.order.service;

import com.buffalo.enterprise.service.ProductService;
import com.buffalo.message.OperateLogMessageSender;
import com.buffalo.order.mapper.WarehouseOrderMapper;
import com.buffalo.order.model.WarehouseOrder;
import com.buffalo.order.model.WarehouseOrderItem;
import com.buffalo.order.model.Inventory;
import com.buffalo.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class WarehouseOrderServiceImpl implements WarehouseOrderService {
	
	@Autowired
	private WarehouseOrderMapper warehouseOrderMapper;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<WarehouseOrder> list(String keyword) throws Exception {
		WarehouseOrder warehouseOrder = new WarehouseOrder();
		warehouseOrder.setKeyword(keyword);
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
		for(WarehouseOrderItem warehouseOrderItem :warehouseOrder.getItemList()){
			int quantity = warehouseOrderItem.getQuantity();
			if(quantity>0){
				warehouseOrderItem.setWarehouse_order_id(warehouseOrderId);
				warehouseOrderMapper.addWarehouseOrderItem(warehouseOrderItem);
				String productId = warehouseOrderItem.getProduct_id();
				inventoryService.decreaseItemQuantity(inventoryId,productId,quantity);
			}
		}
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"出仓单","添加出仓单："+warehouseOrder.getOrder_date());
		return warehouseOrder;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(WarehouseOrder warehouseOrder) throws Exception {
		warehouseOrderMapper.delete(warehouseOrder);
		warehouseOrderMapper.deleteItemList(warehouseOrder.getId());
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"出仓单","删除出仓单："+warehouseOrder.getOrder_date());
	}


}
