
package com.buffalo.gateway.order.controller;

import com.buffalo.gateway.order.model.Inventory;
import com.buffalo.gateway.order.service.InventoryService;
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
@RequestMapping("/order/order/inventory")
public class InventoryController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private InventoryService inventoryService;


	/**
	 * 查询所有库存列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/all", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<Inventory> list = inventoryService.list(keyword);
			PageInfo<Inventory> pageInfo = new PageInfo<Inventory>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询所有库存列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询所有库存列表失败. " + e.getMessage());
		}
    }

	/**
	 * 查询当天库存列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
	public <T> Map<String, T> today(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<Inventory> list = inventoryService.today(keyword);
			PageInfo<Inventory> pageInfo = new PageInfo<Inventory>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询当天库存列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询当天库存列表失败. " + e.getMessage());
		}
	}

	/**
	 * 根据id查询库存
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public <T> Map<String, T> getById(@PathVariable String id) {
		try {
			Inventory inventory = inventoryService.getById(id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据id查询库存成功", inventory);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据id查询库存失败. " + e.getMessage());
		}
	}

	/**
	 * 根据日期查询库存
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/date", method = RequestMethod.POST)
	public <T> Map<String, T> getByDate(@RequestBody Inventory inventory, Model model) {
		try {
			Inventory inventoryResult = inventoryService.getByDate(inventory.getInventory_date());
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据日期查询库存成功", inventoryResult);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据日期查询库存失败. " + e.getMessage());
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
