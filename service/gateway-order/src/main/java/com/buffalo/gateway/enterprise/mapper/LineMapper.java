
package com.buffalo.gateway.enterprise.mapper;

import com.buffalo.gateway.enterprise.model.Line;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LineMapper {

    public List<Line> list(Line line) throws Exception;
    
    public void add(Line line) throws Exception;

    public void update(Line line) throws Exception;
    
    public void delete(Line line) throws Exception;

}
