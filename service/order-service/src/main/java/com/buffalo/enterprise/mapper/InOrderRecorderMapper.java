
package com.buffalo.enterprise.mapper;

import com.buffalo.enterprise.model.InOrderRecorder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InOrderRecorderMapper {

    public List<InOrderRecorder> list(InOrderRecorder inOrderRecorder) throws Exception;
    
    public void add(InOrderRecorder inOrderRecorder) throws Exception;

    public void update(InOrderRecorder inOrderRecorder) throws Exception;
    
    public void delete(InOrderRecorder inOrderRecorder) throws Exception;

}
