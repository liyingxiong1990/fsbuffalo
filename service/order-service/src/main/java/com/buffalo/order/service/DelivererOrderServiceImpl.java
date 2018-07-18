
package com.buffalo.order.service;

import com.buffalo.enterprise.model.Product;
import com.buffalo.enterprise.model.Store;
import com.buffalo.enterprise.model.StorePrice;
import com.buffalo.enterprise.service.ProductService;
import com.buffalo.enterprise.service.StoreService;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DelivererOrderServiceImpl implements DelivererOrderService {
	
	@Autowired
	private DelivererOrderMapper delivererOrderMapper;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private StoreService storeService;

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
	public DelivererOrder getById(String id) throws Exception {
		DelivererOrder delivererOrder = delivererOrderMapper.getById(id) ;
		Store store = storeService.getById(delivererOrder.getStore_id());
		Map<String,String> priceMap = new HashMap<String,String>();
		for(StorePrice storePrice:store.getPriceList()){
			priceMap.put(storePrice.getProduct_id(),storePrice.getUnit_price());
		}
		BigDecimal totalPrice = new BigDecimal("0");
		for(DelivererOrderItem delivererOrderItem : delivererOrder.getItemList()){
			String productId = delivererOrderItem.getProduct_id();
			if(priceMap.get(productId)!=null && priceMap.get(productId).equals("")==false){
				Product product = productService.getById(delivererOrderItem.getProduct_id());
				Integer quantity = delivererOrderItem.getQuantity();
				BigDecimal unitPrice = new BigDecimal(priceMap.get(productId));
				BigDecimal quantityDemcimal = new BigDecimal(quantity);
				delivererOrderItem.setUnit_price(priceMap.get(productId));
				delivererOrderItem.setTotal_price(unitPrice.multiply(quantityDemcimal).toString());
				totalPrice = totalPrice.add(unitPrice.multiply(quantityDemcimal));
				int numberOfBoxes = quantity/product.getScale();
				int remainder = quantity%product.getScale();
				delivererOrderItem.setNumber_of_boxes(numberOfBoxes);
				delivererOrderItem.setRemainder(remainder);
			}
		}
		delivererOrder.setTotal_price(totalPrice.toString());
		return delivererOrder ;

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
			delivererOrderItem.setDeliverer_order_id(delivererOrderId);
			delivererOrderMapper.addDelivererOrderItem(delivererOrderItem);

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
			delivererOrderItem.setProduct_index(product.getProduct_index());
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
			resultDelivererOrderItemList.add(delivererOrderItem);
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
