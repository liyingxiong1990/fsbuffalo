
package com.buffalo.enterprise.mapper;

import com.buffalo.enterprise.model.Carrier;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarrierMapper {

    public List<Carrier> list(Carrier carrier) throws Exception;
    
    public void add(Carrier carrier) throws Exception;

    public void update(Carrier carrier) throws Exception;
    
    public void delete(Carrier carrier) throws Exception;

}
