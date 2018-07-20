
package com.buffalo.gateway.sys.controller;


import com.buffalo.gateway.sys.model.Permission;
import com.buffalo.gateway.sys.service.PermissionService;
import com.buffalo.gateway.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/permission")
public class PermissionController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PermissionService permissionService;

	/**
	 * 查询权限列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll() {
		try {
			List<Permission> list = permissionService.list();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询权限列表成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询权限列表失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 查询权限
	 * @param permission_id
	 * @param <T>
	 * @return
	 */
    @RequestMapping(value="/{permission_id}", method = RequestMethod.GET)
    public <T> Map<String, T> getById(@PathVariable String permission_id) {
		try {
	        Permission permission = permissionService.getById(permission_id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询权限成功", permission);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询权限失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 新增权限
	 * @param permission
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
    public <T> Map<String, T> create(@RequestBody Permission permission, Model model) {
    	try {
			permissionService.add(permission);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增权限成功", permission);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增权限失败. Info:" + e.getMessage(), permission);
		}
    }

	/**
	 * 删除权限
	 * @param permission_id
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/{permission_id}", method = RequestMethod.DELETE)
    public <T> Map<String, T> deleteById(@PathVariable String permission_id) {
    	try {
			permissionService.delete(permission_id);
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除权限成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除权限失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 修改权限
	 * @param permission
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
    public <T> Map<String, T> update(@RequestBody Permission permission, Model model) {
    	try {
			permissionService.update(permission);
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改权限成功", permission);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改权限失败. Info:" + e.getMessage(), permission);
		}
    }


	/**
	 * 根据菜单id查询权限列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/menu/{menu_id}", method = RequestMethod.GET)
	public <T> Map<String, T> getPermissionByMenuId(@PathVariable String menu_id) {
		try {
			List<Permission> list = permissionService.getPermissionByMenuId(menu_id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据菜单id查询权限列表成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据菜单id查询权限列表失败. Info:" + e.getMessage());
		}
	}

    
}
