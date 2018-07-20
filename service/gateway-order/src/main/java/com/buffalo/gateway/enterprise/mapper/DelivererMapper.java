
package com.buffalo.gateway.enterprise.mapper;

import com.buffalo.gateway.enterprise.model.Deliverer;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DelivererMapper {

    public List<Deliverer> list(Deliverer deliverer) throws Exception;
    
    public void add(Deliverer deliverer) throws Exception;

    public void update(Deliverer deliverer) throws Exception;
    
    public void delete(Deliverer deliverer) throws Exception;

}
