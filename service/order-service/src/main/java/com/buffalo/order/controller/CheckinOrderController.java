
package com.buffalo.order.controller;

import com.buffalo.order.model.CheckinOrder;
import com.buffalo.order.service.CheckinOrderService;
import com.buffalo.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order/order/checkinOrder")
public class CheckinOrderController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CheckinOrderService checkinOrderService;


	/**
	 * 查询进仓单列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<CheckinOrder> list = checkinOrderService.list(keyword);
			PageInfo<CheckinOrder> pageInfo = new PageInfo<CheckinOrder>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询进仓单列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询进仓单列表失败. " + e.getMessage());
		}
    }

	/**
	 * 根据id查询进仓单
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public <T> Map<String, T> getById(@PathVariable String id) {
		try {
			CheckinOrder checkinOrder = checkinOrderService.getById(id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据id查询进仓单成功", checkinOrder);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据id查询进仓单失败. " + e.getMessage());
		}
	}

	/**
	 * 统计某一天进仓单
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/statistic", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> statistic(@RequestBody CheckinOrder checkinOrder, Model model) {
		try {
			CheckinOrder result = checkinOrderService.statistic(checkinOrder.getCheckin_date());
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "统计某一天进仓单成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "统计某一天进仓单失败. " + e.getMessage());
		}
	}

	/**
	 * 新增进仓单
	 * @param checkinOrder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> create(@RequestBody CheckinOrder checkinOrder, Model model) {
		try {
			checkinOrder = checkinOrderService.add(checkinOrder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增进仓单成功", checkinOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增进仓单失败. " + e.getMessage(), checkinOrder);
		}
	}
	

	/**
	 * 修改进仓单
	 * @param checkinOrder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> update(@RequestBody CheckinOrder checkinOrder, Model model) {
		try {
			checkinOrderService.update(checkinOrder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改进仓单成功", checkinOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改进仓单失败. " + e.getMessage(), checkinOrder);
		}
	}

	/**
	 * 删除进仓单
	 * @param checkinOrder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> delete(@RequestBody CheckinOrder checkinOrder, Model model) {
		try {
			checkinOrderService.delete(checkinOrder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除进仓单成功", checkinOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除进仓单失败. " + e.getMessage(), checkinOrder);
		}
	}

}
