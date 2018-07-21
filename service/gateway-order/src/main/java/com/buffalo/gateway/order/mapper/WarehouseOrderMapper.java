
package com.buffalo.gateway.order.mapper;

import com.buffalo.gateway.order.model.WarehouseOrder;
import com.buffalo.gateway.order.model.WarehouseOrderItem;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface WarehouseOrderMapper {

    public List<WarehouseOrder> list(WarehouseOrder warehouseOrder) throws Exception;

    public WarehouseOrder getById(String id) throws Exception;

    public void add(WarehouseOrder warehouseOrder) throws Exception;

    public void delete(WarehouseOrder warehouseOrder) throws Exception;

    public List<WarehouseOrderItem> getItemListByWarehouseOrderId(String id) throws Exception;

    public void addWarehouseOrderItem(WarehouseOrderItem warehouseOrderItem) throws Exception;

    public void deleteItemList(String deliverer_order_id) throws Exception;

    public List<WarehouseOrder> getListByOrderDate(Date order_date) throws Exception;
}
