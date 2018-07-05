
package com.buffalo.fastdfs.service;
import com.buffalo.fastdfs.model.Attachment;

import java.util.Map;

public interface AttachmentService {

    public Map getAttachment(String order_id, String order_type) throws Exception;

    public void add(Attachment attachment) throws Exception;

    public void update(Attachment attachment) throws Exception;

    public Boolean deleteAttachment(String order_id, String order_type) throws Exception;


}
