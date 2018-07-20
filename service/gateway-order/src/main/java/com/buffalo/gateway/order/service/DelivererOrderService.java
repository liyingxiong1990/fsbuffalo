
package com.buffalo.gateway.order.service;

import com.buffalo.gateway.order.model.DelivererOrder;

import java.util.List;

public interface DelivererOrderService {

    public List<DelivererOrder> list(String keyword) throws Exception;

    public List<DelivererOrder> today(String keyword) throws Exception;

    public DelivererOrder getById(String id) throws Exception;

    public DelivererOrder add(DelivererOrder delivererOrder) throws Exception;
    
    public void update(DelivererOrder delivererOrder) throws Exception;

    public void delete(DelivererOrder delivererOrder) throws Exception;

    public DelivererOrder getByDateDriverLine(DelivererOrder delivererOrder) throws Exception;

    public void setOut(DelivererOrder delivererOrderQuery) throws Exception;

}
