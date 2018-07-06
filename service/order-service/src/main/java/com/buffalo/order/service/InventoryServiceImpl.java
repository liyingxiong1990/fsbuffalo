
package com.buffalo.order.service;

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
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryMapper inventoryMapper;
	
	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<Inventory> list(String keyword) throws Exception {
		Inventory inventory = new Inventory();
		inventory.setKeyword(keyword);
		return inventoryMapper.list(inventory);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Inventory add(Inventory inventory) throws Exception {
		String inventoryId = UUIDUtil.getUUID();
		inventory.setId(inventoryId);
		inventoryMapper.add(inventory);
		Inventory lastInventory = inventoryMapper.getInventoryByInventoryDate(inventory.getLast_date());
		for(InventoryItem inventoryItem :lastInventory.getItemList()){
			inventoryItem.setInventory_id(inventoryId);
			inventoryMapper.addInventoryItem(inventoryItem);
		}
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"库存","添加库存："+inventory.getInventory_date());
		return inventory;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Inventory addBlank(Inventory inventory) throws Exception {
		String inventoryId = UUIDUtil.getUUID();
		inventory.setId(inventoryId);
		inventoryMapper.add(inventory);
		for(InventoryItem inventoryItem :inventory.getItemList()){
			inventoryItem.setInventory_id(inventoryId);
			inventoryMapper.addInventoryItem(inventoryItem);
		}
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"库存","添加空白库存："+inventory.getInventory_date());
		return inventory;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void update(Inventory inventory) throws Exception {
		inventoryMapper.update(inventory);
		for(InventoryItem inventoryItem :inventory.getItemList()){
			inventoryMapper.updateInventoryItem(inventoryItem);
		}
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"库存","修改库存："+inventory.getInventory_date());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Inventory inventory) throws Exception {
		inventoryMapper.delete(inventory);
		inventoryMapper.deleteItemList(inventory.getId());
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"库存","删除库存："+inventory.getInventory_date());
	}


}
