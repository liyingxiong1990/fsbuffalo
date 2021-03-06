
package com.buffalo.gateway.enterprise.service;

import com.buffalo.gateway.enterprise.model.Carrier;

import java.util.List;

public interface CarrierService {

    public List<Carrier> list(String keyword) throws Exception;
    
    public Carrier add(Carrier carrier) throws Exception;

    public void update(Carrier carrier) throws Exception;

    public void delete(Carrier carrier) throws Exception;

}
