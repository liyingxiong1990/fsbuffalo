
package com.buffalo.fastdfs.controller;

import com.buffalo.fastdfs.model.Attachment;
import com.buffalo.fastdfs.service.AttachmentService;
import com.buffalo.util.JsonUtil;
import com.buffalo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/fastdfs/attachment")
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentService;
	
	/**
	 * 获取附件
	 * @param order_id
	 * @param order_type
	 * @return
	 */
	@RequestMapping(value="/get/{order_id}/{order_type}", method = RequestMethod.GET)
	public <T> Map<String, T> get(@PathVariable String order_id, @PathVariable String order_type) {
		try {
			Map attachmentMap = attachmentService.getAttachment(order_id,order_type);
			String attachmentMapStr = JsonUtil.object2Json(attachmentMap);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "获取附件成功", attachmentMapStr);
		} catch (Exception  e) {
			e.printStackTrace();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "获取附件失败. :" + e.getMessage());
		}
	}

	/**
	 * 新增附件
	 * @param attachment
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> add(@RequestBody Attachment attachment, Model model) {
		try {
			attachmentService.add(attachment);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增附件成功", attachment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增附件失败. " + e.getMessage(), attachment);
		}
	}

	/**
	 * 修改附件
	 * @param attachment
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> update(@RequestBody Attachment attachment, Model model) {
		try {
			attachmentService.update(attachment);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改附件成功", attachment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改附件失败. " + e.getMessage(), attachment);
		}
	}

	/**
	 * 删除附件
	 *  @param order_id
	 * 	 @param order_type
	 * @return
	 */
	@RequestMapping(value="/delete/{order_id}/{order_type}", method = RequestMethod.DELETE, consumes="application/json")
	public <T> Map<String, T>  deleteFile(@PathVariable String order_id, @PathVariable String order_type) {
		try {
			if(attachmentService.deleteAttachment(order_id,order_type)){
				return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除附件成功");
			}else{
				return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除附件失败");
			}

		} catch (Exception e) {
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除附件失败.:" + e.getMessage());
		}
	}
}