
package com.buffalo.order.service;

import com.buffalo.order.model.CheckinOrder;

import java.util.Date;
import java.util.List;

public interface CheckinOrderService {

    public List<CheckinOrder> list(String keyword) throws Exception;

    public List<CheckinOrder> today(String keyword) throws Exception;

    public CheckinOrder getById(String id)  throws Exception;

    public CheckinOrder statistic(Date checkin_date) throws Exception;

    public CheckinOrder add(CheckinOrder checkinOrder) throws Exception;
    
    public void update(CheckinOrder checkinOrder) throws Exception;

    public void delete(CheckinOrder checkinOrder) throws Exception;

}
