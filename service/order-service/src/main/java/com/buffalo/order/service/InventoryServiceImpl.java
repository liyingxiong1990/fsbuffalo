
package com.buffalo.order.service;

import com.buffalo.enterprise.model.Product;
import com.buffalo.enterprise.service.ProductService;
import com.buffalo.message.OperateLogMessageSender;
import com.buffalo.order.mapper.InventoryMapper;
import com.buffalo.order.model.Inventory;
import com.buffalo.order.model.InventoryItem;
import com.buffalo.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryMapper inventoryMapper;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<Inventory> list(String keyword) throws Exception {
		Inventory inventory = new Inventory();
		inventory.setKeyword(keyword);
		List<Inventory> inventoryList = inventoryMapper.list(inventory);
		List<Inventory> resultList = new ArrayList<Inventory>();
		for(int i=0;i<inventoryList.size() && i<2;i++){
			resultList.add(inventoryList.get(i));
		}
		return resultList;
	}

	@Override
	public Inventory getById(String id) throws Exception {
		return inventoryMapper.getById(id);
	}

	@Override
	public Inventory getByDate(Date inventory_date) throws Exception {
		return inventoryMapper.getInventoryByInventoryDate(inventory_date);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Inventory add(Inventory inventory) throws Exception {
		Inventory inventoryIfExists = inventoryMapper.getInventoryByInventoryDate(inventory.getInventory_date());
		if(inventoryIfExists!=null){
			throw new Exception("该日期库存已存在");
		}
		String inventoryId = UUIDUtil.getUUID();
		inventory.setId(inventoryId);
		inventoryMapper.add(inventory);
		Inventory lastInventory = inventoryMapper.getInventoryByInventoryDate(inventory.getLast_date());
		if(lastInventory==null){
			throw new Exception("上一日期库存不存在");
		}
		for(InventoryItem inventoryItem :lastInventory.getItemList()){
			inventoryItem.setInventory_id(inventoryId);
			inventoryMapper.addInventoryItem(inventoryItem);
		}
		return inventory;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Inventory addBlank(Inventory inventory) throws Exception {
		Inventory inventoryIfExists = inventoryMapper.getInventoryByInventoryDate(inventory.getInventory_date());
		if(inventoryIfExists!=null){
			throw new Exception("该日期库存已存在");
		}
		String inventoryId = UUIDUtil.getUUID();
		inventory.setId(inventoryId);
		inventoryMapper.add(inventory);
		for(InventoryItem inventoryItem :inventory.getItemList()){
			inventoryItem.setInventory_id(inventoryId);
			inventoryMapper.addInventoryItem(inventoryItem);
		}
		return inventory;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void update(Inventory inventory) throws Exception {
		inventoryMapper.update(inventory);
		for(InventoryItem inventoryItem :inventory.getItemList()){
			inventoryMapper.updateInventoryItem(inventoryItem);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Inventory inventory) throws Exception {
		inventoryMapper.delete(inventory);
		inventoryMapper.deleteItemList(inventory.getId());
	}

	@Override
	public Inventory getInventoryByInventoryDate(Date checkin_date) throws Exception {
		return inventoryMapper.getInventoryByInventoryDate(checkin_date);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void increaseItemQuantity(String inventoryId, String productId, int quantity) throws Exception {
		InventoryItem inventoryItemQuery = new InventoryItem();
		inventoryItemQuery.setInventory_id(inventoryId);
		inventoryItemQuery.setProduct_id(productId);
		InventoryItem inventoryItem = inventoryMapper.getByInventoryIdAndProductId(inventoryItemQuery);
		if(inventoryItem==null){
			throw new Exception("库存记录中无该产品！");
		}
		inventoryItem.setQuantity(inventoryItem.getQuantity()+quantity);
		inventoryMapper.updateInventoryItem(inventoryItem);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void decreaseItemQuantity(String inventoryId, String productId, int quantity) throws Exception {
		InventoryItem inventoryItemQuery = new InventoryItem();
		inventoryItemQuery.setInventory_id(inventoryId);
		inventoryItemQuery.setProduct_id(productId);
		InventoryItem inventoryItem = inventoryMapper.getByInventoryIdAndProductId(inventoryItemQuery);
		if(inventoryItem==null){
			throw new Exception("库存记录中无该产品！");
		}
		if(inventoryItem.getQuantity()-quantity<0){
			Product product = productService.getById(inventoryItem.getProduct_id());
			throw new Exception(product.getName()+"库存不足！");
		}
		inventoryItem.setQuantity(inventoryItem.getQuantity()-quantity);
		inventoryMapper.updateInventoryItem(inventoryItem);
	}


}
