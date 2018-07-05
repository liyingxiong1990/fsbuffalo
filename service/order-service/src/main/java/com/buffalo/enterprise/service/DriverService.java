
package com.buffalo.enterprise.service;

import com.buffalo.enterprise.model.Driver;

import java.util.List;

public interface DriverService {

    public List<Driver> list(String keyword) throws Exception;
    
    public Driver add(Driver driver) throws Exception;

    public void update(Driver driver) throws Exception;

    public void delete(Driver driver) throws Exception;

}
