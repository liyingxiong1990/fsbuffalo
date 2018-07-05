
package com.buffalo.enterprise.service;

import com.buffalo.enterprise.model.Deliverer;

import java.util.List;

public interface DelivererService {

    public List<Deliverer> list(String keyword) throws Exception;
    
    public Deliverer add(Deliverer deliverer) throws Exception;

    public void update(Deliverer deliverer) throws Exception;

    public void delete(Deliverer deliverer) throws Exception;

}
