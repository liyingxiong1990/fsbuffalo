
package com.buffalo.enterprise.controller;

import com.buffalo.enterprise.model.Driver;
import com.buffalo.enterprise.service.DriverService;
import com.buffalo.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order/enterprise/driver")
public class DriverController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private DriverService driverService;

	@Value("${eureka.instance.preferIpAddress}")
	private String str;
	@RequestMapping(value="/getConfig")
	public String getConfig() {
		return str;
	}

	/**
	 * 查询专卖店司机列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<Driver> list = driverService.list(keyword);
			PageInfo<Driver> pageInfo = new PageInfo<Driver>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询专卖店司机列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询专卖店司机列表失败. " + e.getMessage());
		}
    }

	/**
	 * 新增专卖店司机
	 * @param driver
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> create(@RequestBody Driver driver, Model model) {
		try {
			driver = driverService.add(driver);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增专卖店司机成功", driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增专卖店司机失败. " + e.getMessage(), driver);
		}
	}

	/**
	 * 修改专卖店司机
	 * @param driver
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> update(@RequestBody Driver driver, Model model) {
		try {
			driverService.update(driver);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改专卖店司机成功", driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改专卖店司机失败. " + e.getMessage(), driver);
		}
	}

	/**
	 * 删除专卖店司机
	 * @param driver
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> delete(@RequestBody Driver driver, Model model) {
		try {
			driverService.delete(driver);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除专卖店司机成功", driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除专卖店司机失败. " + e.getMessage(), driver);
		}
	}

}
