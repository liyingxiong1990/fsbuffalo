
package com.buffalo.enterprise.mapper;

import com.buffalo.enterprise.model.Driver;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DriverMapper {

    public List<Driver> list(Driver driver) throws Exception;
    
    public void add(Driver driver) throws Exception;

    public void update(Driver driver) throws Exception;
    
    public void delete(Driver driver) throws Exception;

}
