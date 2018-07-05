
package com.buffalo.enterprise.controller;

import com.buffalo.enterprise.model.InOrderRecorder;
import com.buffalo.enterprise.service.InOrderRecorderService;
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
@RequestMapping("/order/enterprise/inOrderRecorder")
public class InOrderRecorderController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private InOrderRecorderService outOrderRecorderService;


	/**
	 * 查询仓管列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<InOrderRecorder> list = outOrderRecorderService.list(keyword);
			PageInfo<InOrderRecorder> pageInfo = new PageInfo<InOrderRecorder>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询仓管列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询仓管列表失败. " + e.getMessage());
		}
    }

	/**
	 * 新增仓管
	 * @param outOrderRecorder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> create(@RequestBody InOrderRecorder outOrderRecorder, Model model) {
		try {
			outOrderRecorder = outOrderRecorderService.add(outOrderRecorder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增仓管成功", outOrderRecorder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增仓管失败. " + e.getMessage(), outOrderRecorder);
		}
	}

	/**
	 * 修改仓管
	 * @param outOrderRecorder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> update(@RequestBody InOrderRecorder outOrderRecorder, Model model) {
		try {
			outOrderRecorderService.update(outOrderRecorder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改仓管成功", outOrderRecorder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改仓管失败. " + e.getMessage(), outOrderRecorder);
		}
	}

	/**
	 * 删除仓管
	 * @param outOrderRecorder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> delete(@RequestBody InOrderRecorder outOrderRecorder, Model model) {
		try {
			outOrderRecorderService.delete(outOrderRecorder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除仓管成功", outOrderRecorder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除仓管失败. " + e.getMessage(), outOrderRecorder);
		}
	}

}
