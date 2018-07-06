
package com.buffalo.order.controller;

import com.buffalo.order.model.Inventory;
import com.buffalo.order.service.InventoryService;
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
@RequestMapping("/order/order/inventory")
public class InventoryController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private InventoryService inventoryService;


	/**
	 * 查询库存列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<Inventory> list = inventoryService.list(keyword);
			PageInfo<Inventory> pageInfo = new PageInfo<Inventory>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询库存列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询库存列表失败. " + e.getMessage());
		}
    }

	/**
	 * 新增库存
	 * @param inventory
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> create(@RequestBody Inventory inventory, Model model) {
		try {
			inventory = inventoryService.add(inventory);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增库存成功", inventory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增库存失败. " + e.getMessage(), inventory);
		}
	}

	/**
	 * 新增空白库存
	 * @param inventory
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/blank", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> createBlank(@RequestBody Inventory inventory, Model model) {
		try {
			inventory = inventoryService.addBlank(inventory);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增空白库存成功", inventory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增空白库存失败. " + e.getMessage(), inventory);
		}
	}

	/**
	 * 修改库存
	 * @param inventory
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> update(@RequestBody Inventory inventory, Model model) {
		try {
			inventoryService.update(inventory);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改库存成功", inventory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改库存失败. " + e.getMessage(), inventory);
		}
	}

	/**
	 * 删除库存
	 * @param inventory
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> delete(@RequestBody Inventory inventory, Model model) {
		try {
			inventoryService.delete(inventory);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除库存成功", inventory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除库存失败. " + e.getMessage(), inventory);
		}
	}

}
