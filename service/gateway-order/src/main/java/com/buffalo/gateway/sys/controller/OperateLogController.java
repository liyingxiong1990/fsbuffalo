
package com.buffalo.gateway.sys.controller;

import com.buffalo.gateway.sys.model.OperateLog;
import com.buffalo.gateway.sys.service.OperateLogService;
import com.buffalo.gateway.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 操作日志Resful接口
 */
@RestController
@RequestMapping("/sys/operate/log")
public class OperateLogController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private OperateLogService operateLogService;

	/**
	 * SDC操作日志列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/sdc", method = RequestMethod.GET)
	public <T> Map<String, T> getSdcLogList(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<OperateLog> list = operateLogService.getSdcLogList();
			PageInfo<OperateLog> pageInfo = new PageInfo<OperateLog>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "SDC操作日志列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "SDC操作日志列表失败. Info:" + e.getMessage());
		}
	}

	/**
	 * BOSS操作日志列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/boss", method = RequestMethod.GET)
    public <T> Map<String, T> getBossLogList(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<OperateLog> list = operateLogService.getBossLogList();
			PageInfo<OperateLog> pageInfo = new PageInfo<OperateLog>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "BOSS操作日志列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "BOSS操作日志列表失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 查询操作日志
	 * @param operate_log_id
	 * @param <T>
	 * @return
	 */
    @RequestMapping(value="/{operate_log_id}", method = RequestMethod.GET)
    public <T> Map<String, T> getByLogId(@PathVariable String operate_log_id) {
		try {
			OperateLog log = operateLogService.getById(operate_log_id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询操作日志成功", log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询操作日志失败. Info:" + e.getMessage());
		}
    }


}
