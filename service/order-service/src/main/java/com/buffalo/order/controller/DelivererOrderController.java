
package com.buffalo.order.controller;

import com.buffalo.order.model.DelivererOrder;
import com.buffalo.order.service.DelivererOrderService;
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
@RequestMapping("/order/order/delivererOrder")
public class DelivererOrderController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private DelivererOrderService delivererOrderService;


	/**
	 * 查询送货单列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<DelivererOrder> list = delivererOrderService.list(keyword);
			PageInfo<DelivererOrder> pageInfo = new PageInfo<DelivererOrder>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询送货单列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询送货单列表失败. " + e.getMessage());
		}
    }

	/**
	 * 新增送货单
	 * @param delivererOrder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> create(@RequestBody DelivererOrder delivererOrder, Model model) {
		try {
			delivererOrder = delivererOrderService.add(delivererOrder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增送货单成功", delivererOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增送货单失败. " + e.getMessage(), delivererOrder);
		}
	}
	

	/**
	 * 修改送货单
	 * @param delivererOrder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> update(@RequestBody DelivererOrder delivererOrder, Model model) {
		try {
			delivererOrderService.update(delivererOrder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改送货单成功", delivererOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改送货单失败. " + e.getMessage(), delivererOrder);
		}
	}

	/**
	 * 删除送货单
	 * @param delivererOrder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> delete(@RequestBody DelivererOrder delivererOrder, Model model) {
		try {
			delivererOrderService.delete(delivererOrder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除送货单成功", delivererOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除送货单失败. " + e.getMessage(), delivererOrder);
		}
	}

}