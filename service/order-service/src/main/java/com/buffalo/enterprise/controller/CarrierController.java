
package com.buffalo.enterprise.controller;

import com.buffalo.enterprise.model.Carrier;
import com.buffalo.enterprise.service.CarrierService;
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
@RequestMapping("/order/enterprise/carrier")
public class CarrierController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CarrierService carrierService;


	/**
	 * 查询缴仓列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<Carrier> list = carrierService.list(keyword);
			PageInfo<Carrier> pageInfo = new PageInfo<Carrier>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询缴仓列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询缴仓列表失败. " + e.getMessage());
		}
    }

	/**
	 * 新增缴仓
	 * @param carrier
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> create(@RequestBody Carrier carrier, Model model) {
		try {
			carrier = carrierService.add(carrier);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增缴仓成功", carrier);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增缴仓失败. " + e.getMessage(), carrier);
		}
	}

	/**
	 * 修改缴仓
	 * @param carrier
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> update(@RequestBody Carrier carrier, Model model) {
		try {
			carrierService.update(carrier);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改缴仓成功", carrier);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改缴仓失败. " + e.getMessage(), carrier);
		}
	}

	/**
	 * 删除缴仓
	 * @param carrier
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> delete(@RequestBody Carrier carrier, Model model) {
		try {
			carrierService.delete(carrier);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除缴仓成功", carrier);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除缴仓失败. " + e.getMessage(), carrier);
		}
	}

}
