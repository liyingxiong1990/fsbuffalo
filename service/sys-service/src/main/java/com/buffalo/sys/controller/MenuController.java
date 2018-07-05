
package com.buffalo.sys.controller;


import com.buffalo.sys.model.Menu;
import com.buffalo.sys.service.MenuService;
import com.buffalo.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/menu")
public class MenuController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuService menuService;

	/**
	 * 查询菜单列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll() {
		try {
			List<Menu> list = menuService.list();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询菜单列表成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询菜单列表失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 查询菜单
	 * @param menu_id
	 * @param <T>
	 * @return
	 */
    @RequestMapping(value="/{menu_id}", method = RequestMethod.GET)
    public <T> Map<String, T> getById(@PathVariable String menu_id) {
		try {
	        Menu menu = menuService.getById(menu_id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询菜单成功", menu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询菜单失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 新增菜单
	 * @param menu
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
    public <T> Map<String, T> create(@RequestBody Menu menu, Model model) {
    	try {
			menuService.add(menu);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增菜单成功", menu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增菜单失败. Info:" + e.getMessage(), menu);
		}
    }

	/**
	 * 删除菜单
	 * @param menu_id
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/{menu_id}", method = RequestMethod.DELETE)
    public <T> Map<String, T> deleteById(@PathVariable String menu_id) {
    	try {
			menuService.delete(menu_id);
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除菜单成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除菜单失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 修改菜单
	 * @param menu
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
    public <T> Map<String, T> update(@RequestBody Menu menu, Model model) {
    	try {
			menuService.update(menu);
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改菜单成功", menu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改菜单失败. Info:" + e.getMessage(), menu);
		}
    }
    

    
}
