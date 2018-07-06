
package com.buffalo.enterprise.controller;

import com.buffalo.enterprise.model.OutOrderRecorder;
import com.buffalo.enterprise.service.OutOrderRecorderService;
import com.buffalo.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order/enterprise/outOrderRecorder")
public class OutOrderRecorderController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private OutOrderRecorderService outOrderRecorderService;

	/**
	 * 查询所有开单人列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public <T> Map<String, T> getAllOutOrderRecorder() {
		try {
			List<OutOrderRecorder> list = outOrderRecorderService.list("");
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询所有开单人列表成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询所有开单人列表失败. " + e.getMessage());
		}
	}

	/**
	 * 查询开单人列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<OutOrderRecorder> list = outOrderRecorderService.list(keyword);
			PageInfo<OutOrderRecorder> pageInfo = new PageInfo<OutOrderRecorder>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询开单人列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询开单人列表失败. " + e.getMessage());
		}
    }

	/**
	 * 新增开单人
	 * @param outOrderRecorder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> create(@RequestBody OutOrderRecorder outOrderRecorder, Model model) {
		try {
			outOrderRecorder = outOrderRecorderService.add(outOrderRecorder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增开单人成功", outOrderRecorder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增开单人失败. " + e.getMessage(), outOrderRecorder);
		}
	}

	/**
	 * 修改开单人
	 * @param outOrderRecorder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> update(@RequestBody OutOrderRecorder outOrderRecorder, Model model) {
		try {
			outOrderRecorderService.update(outOrderRecorder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改开单人成功", outOrderRecorder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改开单人失败. " + e.getMessage(), outOrderRecorder);
		}
	}

	/**
	 * 删除开单人
	 * @param outOrderRecorder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> delete(@RequestBody OutOrderRecorder outOrderRecorder, Model model) {
		try {
			outOrderRecorderService.delete(outOrderRecorder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除开单人成功", outOrderRecorder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除开单人失败. " + e.getMessage(), outOrderRecorder);
		}
	}

}
