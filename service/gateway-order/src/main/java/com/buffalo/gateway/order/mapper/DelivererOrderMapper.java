
package com.buffalo.gateway.order.mapper;

import com.buffalo.gateway.order.model.DelivererOrder;
import com.buffalo.gateway.order.model.DelivererOrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DelivererOrderMapper {

    public List<DelivererOrder> list(DelivererOrder delivererOrder) throws Exception;

    public DelivererOrder getById(String id) throws Exception;

    public void add(DelivererOrder delivererOrder) throws Exception;

    public void update(DelivererOrder delivererOrder) throws Exception;
    
    public void delete(DelivererOrder delivererOrder) throws Exception;

    public void addDelivererOrderItem(DelivererOrderItem delivererOrderItem) throws Exception;

    public void updateDelivererOrderItem(DelivererOrderItem delivererOrderItem) throws Exception;

    public void deleteItemList(String deliverer_order_id) throws Exception;

    public List<DelivererOrder> getByDateDriverLine(DelivererOrder delivererOrder) throws Exception;

}
