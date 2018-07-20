
package com.buffalo.gateway.order.controller;

import com.buffalo.gateway.order.model.WarehouseOrder;
import com.buffalo.gateway.order.service.WarehouseOrderService;
import com.buffalo.gateway.util.ResponseUtil;
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
@RequestMapping("/order/order/warehouseOrder")
public class WarehouseOrderController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private WarehouseOrderService warehouseOrderService;


	/**
	 * 查询所有出仓单列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/all", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<WarehouseOrder> list = warehouseOrderService.list(keyword);
			PageInfo<WarehouseOrder> pageInfo = new PageInfo<WarehouseOrder>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询所有出仓单列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询所有出仓单列表失败. " + e.getMessage());
		}
    }

	/**
	 * 查询当天出仓单列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
	public <T> Map<String, T> today(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<WarehouseOrder> list = warehouseOrderService.today(keyword);
			PageInfo<WarehouseOrder> pageInfo = new PageInfo<WarehouseOrder>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询当天出仓单列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询当天出仓单列表失败. " + e.getMessage());
		}
	}

	/**
	 * 根据id查询出仓单
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public <T> Map<String, T> getById(@PathVariable String id) {
		try {
			WarehouseOrder warehouseOrder = warehouseOrderService.getById(id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据id查询出仓单成功", warehouseOrder);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据id查询出仓单失败. " + e.getMessage());
		}
	}

	/**
	 * 新增出仓单
	 * @param warehouseOrder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> create(@RequestBody WarehouseOrder warehouseOrder, Model model) {
		try {
			warehouseOrder = warehouseOrderService.add(warehouseOrder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增出仓单成功", warehouseOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增出仓单失败. " + e.getMessage(), warehouseOrder);
		}
	}

	/**
	 * 删除出仓单
	 * @param warehouseOrder
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> delete(@RequestBody WarehouseOrder warehouseOrder, Model model) {
		try {
			warehouseOrderService.delete(warehouseOrder);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除出仓单成功", warehouseOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除出仓单失败. " + e.getMessage(), warehouseOrder);
		}
	}

}
