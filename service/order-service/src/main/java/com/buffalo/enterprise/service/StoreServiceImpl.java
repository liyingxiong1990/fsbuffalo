
package com.buffalo.enterprise.service;

import com.buffalo.enterprise.mapper.StoreMapper;
import com.buffalo.enterprise.model.Store;
import com.buffalo.enterprise.model.StorePrice;
import com.buffalo.message.OperateLogMessageSender;
import com.buffalo.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreMapper storeMapper;
	
	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;

	@Override
	public List<Store> list(String keyword) throws Exception {
		Store store = new Store();
		store.setKeyword(keyword);
		return storeMapper.list(store);
	}

	@Override
	public Store getById(String id) throws Exception {
		return storeMapper.getById(id);
	}

	@Override
	public List<Store> getStoresByLine(String line_id) throws Exception {
		return storeMapper.getStoresByLine(line_id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Store add(Store store) throws Exception {
		String storeId = UUIDUtil.getUUID();
		store.setId(storeId);
		storeMapper.add(store);
		for(StorePrice storePrice : store.getPriceList()){
			storePrice.setStore_id(storeId);
			storeMapper.addStorePrice(storePrice);
		}
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"专卖店","添加专卖店："+store.getStore_name());
		return store;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void update(Store store) throws Exception {
		storeMapper.update(store);
		for(StorePrice storePrice : store.getPriceList()){
			storeMapper.updateStorePrice(storePrice);
		}
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"专卖店","修改专卖店："+store.getStore_name());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Store store) throws Exception {
		storeMapper.delete(store);
		storeMapper.deleteStorePrice(store.getId());
		//操作记录
		operateLogMessageSender.send(request.getHeader("userid"),"专卖店","删除专卖店："+store.getStore_name());
	}


}
