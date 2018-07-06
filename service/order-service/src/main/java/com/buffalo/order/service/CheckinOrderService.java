
package com.buffalo.order.service;

import com.buffalo.order.model.CheckinOrder;

import java.util.List;

public interface CheckinOrderService {

    public List<CheckinOrder> list(String keyword) throws Exception;
    
    public CheckinOrder add(CheckinOrder checkinOrder) throws Exception;
    
    public void update(CheckinOrder checkinOrder) throws Exception;

    public void delete(CheckinOrder checkinOrder) throws Exception;


}
