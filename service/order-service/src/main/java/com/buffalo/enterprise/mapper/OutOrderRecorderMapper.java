
package com.buffalo.enterprise.mapper;

import com.buffalo.enterprise.model.OutOrderRecorder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OutOrderRecorderMapper {

    public List<OutOrderRecorder> list(OutOrderRecorder outOrderRecorder) throws Exception;
    
    public void add(OutOrderRecorder outOrderRecorder) throws Exception;

    public void update(OutOrderRecorder outOrderRecorder) throws Exception;
    
    public void delete(OutOrderRecorder outOrderRecorder) throws Exception;

}
