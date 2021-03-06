
package com.buffalo.gateway.order.service;

import com.buffalo.gateway.order.model.WarehouseOrder;

import java.util.Date;
import java.util.List;

public interface WarehouseOrderService {

    public List<WarehouseOrder> list(String keyword) throws Exception;

    public List<WarehouseOrder> get3Day(String keyword) throws Exception;

    public List<WarehouseOrder> today(String keyword) throws Exception;

    public WarehouseOrder getById(String id) throws Exception;

    public WarehouseOrder statistic(Date order_date) throws Exception;

    public WarehouseOrder add(WarehouseOrder warehouseOrder) throws Exception;

    public void delete(WarehouseOrder warehouseOrder) throws Exception;
}
