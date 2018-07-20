
package com.buffalo.gateway.order.mapper;

import com.buffalo.gateway.order.model.CheckinOrder;
import com.buffalo.gateway.order.model.CheckinOrderItem;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface CheckinOrderMapper {

    public List<CheckinOrder> list(CheckinOrder checkinOrder) throws Exception;
    
    public void add(CheckinOrder checkinOrder) throws Exception;

    public void update(CheckinOrder checkinOrder) throws Exception;
    
    public void delete(CheckinOrder checkinOrder) throws Exception;

    public void addCheckinOrderItem(CheckinOrderItem checkinOrderItem) throws Exception;

    public void updateCheckinOrderItem(CheckinOrderItem checkinOrderItem) throws Exception;

    public void deleteItemList(String checkin_order_id) throws Exception;

    public List<CheckinOrder> getListByCheckinDate(Date checkin_date) throws Exception;

    public CheckinOrder getById(String id) throws Exception;
}
