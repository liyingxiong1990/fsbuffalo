
package com.buffalo.enterprise.service;

import com.buffalo.enterprise.model.Store;
import com.buffalo.order.model.DelivererOrder;

import java.util.List;

public interface StoreService {

    public List<Store> list(String keyword) throws Exception;

    public Store getById(String id) throws Exception;

    public List<Store> getStoresByLine(String line_id) throws Exception;

    public Store add(Store store) throws Exception;

    public void update(Store store) throws Exception;

    public void delete(Store store) throws Exception;

}
