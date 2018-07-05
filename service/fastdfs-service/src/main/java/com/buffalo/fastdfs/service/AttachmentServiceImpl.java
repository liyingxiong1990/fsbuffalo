package com.buffalo.fastdfs.service;
import com.buffalo.fastdfs.mapper.AttachmentMapper;
import com.buffalo.fastdfs.model.Attachment;
import com.buffalo.fastdfs.wrapper.FastDFSClientWrapper;
import com.buffalo.message.OperateLogMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired
	private AttachmentMapper attachmentMapper;

	@Autowired
	private FastDFSClientWrapper fastDFSClientWrapper;

	@Autowired
	private OperateLogMessageSender operateLogMessageSender;

	@Autowired
	private HttpServletRequest request;


	@Override
	public Map getAttachment(String order_id, String order_type) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();
		Attachment tempAttachment = new Attachment();
		tempAttachment.setOrder_id(order_id);
		tempAttachment.setOrder_type(order_type);
		Attachment attachment = attachmentMapper.getAttachment(tempAttachment);
		resultMap.put("attaInfo",attachment);
		String keyStr = attachment.getKey_array();
		if(keyStr!=null){
			String[] keyArray =  keyStr.split(",");
			for(int i=0;i<keyArray.length;i++){
				fileList.add(fastDFSClientWrapper.getFileData(keyArray[i]));
			}
			resultMap.put("fileList",fileList);
		}else{
			resultMap.put("fileList","");
		}
		return  resultMap;
	}

	@Override
	public void add(Attachment attachment) throws Exception {
		attachmentMapper.add(attachment);
	}

	@Override
	public void update(Attachment attachment) throws Exception {
		attachmentMapper.update(attachment);
	}

	@Override
	public Boolean deleteAttachment(String order_id, String order_type) throws Exception {
		Attachment tempAttachment = new Attachment();
		tempAttachment.setOrder_id(order_id);
		tempAttachment.setOrder_type(order_type);
		attachmentMapper.deleteAttachment(tempAttachment);
		return true;
	}
}
