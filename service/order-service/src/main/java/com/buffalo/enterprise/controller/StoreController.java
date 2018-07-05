
package com.buffalo.enterprise.controller;

import com.buffalo.enterprise.model.Store;
import com.buffalo.enterprise.service.StoreService;
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
@RequestMapping("/order/enterprise/store")
public class StoreController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private StoreService storeService;


	/**
	 * 查询专卖店列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<Store> list = storeService.list(keyword);
			PageInfo<Store> pageInfo = new PageInfo<Store>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询专卖店列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询专卖店列表失败. " + e.getMessage());
		}
    }

	/**
	 * 新增专卖店
	 * @param store
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> create(@RequestBody Store store, Model model) {
		try {
			store = storeService.add(store);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增专卖店成功", store);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增专卖店失败. " + e.getMessage(), store);
		}
	}

	/**
	 * 修改专卖店
	 * @param store
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> update(@RequestBody Store store, Model model) {
		try {
			storeService.update(store);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改专卖店成功", store);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改专卖店失败. " + e.getMessage(), store);
		}
	}

	/**
	 * 删除专卖店
	 * @param store
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> delete(@RequestBody Store store, Model model) {
		try {
			storeService.delete(store);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除专卖店成功", store);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除专卖店失败. " + e.getMessage(), store);
		}
	}

}
