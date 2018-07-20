
package com.buffalo.gateway.enterprise.service;

import com.buffalo.gateway.enterprise.model.Driver;

import java.util.List;

public interface DriverService {

    public List<Driver> list(String keyword) throws Exception;
    
    public Driver add(Driver driver) throws Exception;

    public void update(Driver driver) throws Exception;

    public void delete(Driver driver) throws Exception;

}
