
package com.buffalo.enterprise.service;

import com.buffalo.enterprise.model.InOrderRecorder;

import java.util.List;

public interface InOrderRecorderService {

    public List<InOrderRecorder> list(String keyword) throws Exception;
    
    public InOrderRecorder add(InOrderRecorder inOrderRecorder) throws Exception;

    public void update(InOrderRecorder inOrderRecorder) throws Exception;

    public void delete(InOrderRecorder inOrderRecorder) throws Exception;

}
