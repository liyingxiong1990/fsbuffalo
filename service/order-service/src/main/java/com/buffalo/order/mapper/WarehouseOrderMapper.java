
package com.buffalo.order.mapper;

import com.buffalo.order.model.WarehouseOrder;
import com.buffalo.order.model.WarehouseOrderItem;
import org.springframework.stereotype.Repository;

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

}
