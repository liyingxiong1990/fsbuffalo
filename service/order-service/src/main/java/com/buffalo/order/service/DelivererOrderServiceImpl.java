
package com.buffalo.order.service;

import com.buffalo.enterprise.model.Product;
import com.buffalo.enterprise.service.ProductService;
import com.buffalo.message.OperateLogMessageSender;
import com.buffalo.order.mapper.DelivererOrderMapper;
import com.buffalo.order.model.DelivererOrder;
import com.buffalo.order.model.DelivererOrderItem;
import com.buffalo.order.model.Inventory;
import com.buffalo.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DelivererOrderServiceImpl implements DelivererOrderService {
	
	@Autowired
	private DelivererOrderMapper delivererOrderMapper;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<DelivererOrder> list(String keyword) throws Exception {
		DelivererOrder delivererOrder = new DelivererOrder();
		delivererOrder.setKeyword(keyword);
		return delivererOrderMapper.list(delivererOrder);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public DelivererOrder add(DelivererOrder delivererOrder) throws Exception {
		Date orderDate = delivererOrder.getOrder_date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Inventory inventory = inventoryService.getInventoryByInventoryDate(orderDate);
		if(inventory==null){
			throw new Exception(sdf.format(orderDate)+"库存记录不存在！");
		}
		String inventoryId = inventory.getId();
		String delivererOrderId =  sdf.format(orderDate)+"-"+ UUIDUtil.getUUID().substring(0,10);
		delivererOrder.setId(delivererOrderId);
		delivererOrderMapper.add(delivererOrder);
		for(DelivererOrderItem delivererOrderItem :delivererOrder.getItemList()){
			int quantity = delivererOrderItem.getQuantity();
			if(quantity>0){
				delivererOrderItem.setDeliverer_order_id(delivererOrderId);
				delivererOrderMapper.addDelivererOrderItem(delivererOrderItem);
			}
		}
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"送货单","添加送货单："+delivererOrder.getOrder_date());
		return delivererOrder;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void update(DelivererOrder delivererOrder) throws Exception {
		delivererOrderMapper.update(delivererOrder);
		for(DelivererOrderItem delivererOrderItem :delivererOrder.getItemList()){
			delivererOrderMapper.updateDelivererOrderItem(delivererOrderItem);
		}
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"送货单","修改送货单："+delivererOrder.getOrder_date());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(DelivererOrder delivererOrder) throws Exception {
		delivererOrderMapper.delete(delivererOrder);
		delivererOrderMapper.deleteItemList(delivererOrder.getId());
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"送货单","删除送货单："+delivererOrder.getOrder_date());
	}

	@Override
	public DelivererOrder getByDateDriverLine(DelivererOrder delivererOrder) throws Exception {
		DelivererOrder resultDelivererOrder = new DelivererOrder();
		List<DelivererOrderItem> resultDelivererOrderItemList = new ArrayList<DelivererOrderItem>();
		List<Product> productList = productService.list("");
		List<DelivererOrderItem> itemList = new ArrayList<DelivererOrderItem>();
		for(Product product: productList){
			DelivererOrderItem delivererOrderItem = new DelivererOrderItem();
			delivererOrderItem.setProduct_id(product.getId());
			delivererOrderItem.setProduct_index(product.getIndex());
			delivererOrderItem.setProduct_name(product.getName());
			delivererOrderItem.setProduct_scale(product.getScale());
			delivererOrderItem.setQuantity(0);
			delivererOrderItem.setId(UUIDUtil.getUUID());
			itemList.add(delivererOrderItem);
		}

		List<DelivererOrder> delivererOrderList = delivererOrderMapper.getByDateDriverLine(delivererOrder);
		for(DelivererOrder dOrder :delivererOrderList){
			for(DelivererOrderItem delivererOrderItem:dOrder.getItemList()){
				for(DelivererOrderItem dOI: itemList){
					if(dOI.getProduct_id().equals(delivererOrderItem.getProduct_id())){
						dOI.setQuantity(dOI.getQuantity()+delivererOrderItem.getQuantity());
					}
				}
			}
		}

		for(DelivererOrderItem delivererOrderItem: itemList){
			if(delivererOrderItem.getQuantity()>0){
				resultDelivererOrderItemList.add(delivererOrderItem);
			}
		}

		resultDelivererOrder.setItemList(resultDelivererOrderItemList);
		return resultDelivererOrder;
	}

	@Override
	public void setOut(DelivererOrder delivererOrderQuery) throws Exception {
		List<DelivererOrder> delivererOrderList = delivererOrderMapper.getByDateDriverLine(delivererOrderQuery);
		for(DelivererOrder delivererOrder : delivererOrderList){
			delivererOrder.setIs_out(1);
			delivererOrderMapper.update(delivererOrder);
		}
	}


}
