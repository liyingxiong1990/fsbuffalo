
package com.buffalo.order.service;

import com.buffalo.order.model.WarehouseOrder;

import java.util.List;

public interface WarehouseOrderService {

    public List<WarehouseOrder> list(String keyword) throws Exception;

    public List<WarehouseOrder> today(String keyword) throws Exception;

    public WarehouseOrder getById(String id) throws Exception;

    public WarehouseOrder add(WarehouseOrder warehouseOrder) throws Exception;

    public void delete(WarehouseOrder warehouseOrder) throws Exception;

}
