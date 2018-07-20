
package com.buffalo.gateway.enterprise.service;

import com.buffalo.gateway.enterprise.model.OutOrderRecorder;

import java.util.List;

public interface OutOrderRecorderService {

    public List<OutOrderRecorder> list(String keyword) throws Exception;
    
    public OutOrderRecorder add(OutOrderRecorder outOrderRecorder) throws Exception;

    public void update(OutOrderRecorder outOrderRecorder) throws Exception;

    public void delete(OutOrderRecorder outOrderRecorder) throws Exception;

}
