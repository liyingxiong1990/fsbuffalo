
package com.buffalo.fastdfs.mapper;

import com.buffalo.fastdfs.model.Attachment;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentMapper {

    public Attachment getAttachment(Attachment attachment) throws Exception;

    public void add(Attachment attachment) throws Exception;

    public void update(Attachment attachment) throws Exception;

    public void deleteAttachment(Attachment attachment) throws Exception;

}
