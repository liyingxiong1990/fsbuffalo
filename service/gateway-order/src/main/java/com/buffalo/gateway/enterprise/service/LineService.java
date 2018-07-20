
package com.buffalo.gateway.enterprise.service;

import com.buffalo.gateway.enterprise.model.Line;

import java.util.List;

public interface LineService {

    public List<Line> list(String keyword) throws Exception;
    
    public Line add(Line line) throws Exception;

    public void update(Line line) throws Exception;

    public void delete(Line line) throws Exception;

}
