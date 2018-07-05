
package com.buffalo.sys.controller;


import com.buffalo.sys.model.Role;
import com.buffalo.sys.service.RoleService;
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
@RequestMapping("/sys/role")
public class RoleController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleService roleService;

	/**
	 * 获取角色列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll() {
		try {
			List<Role> list = roleService.list();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "获取角色列表成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "获取角色列表失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 根据角色id查询角色
	 * @param role_id
	 * @param <T>
	 * @return
	 */
    @RequestMapping(value="/{role_id}", method = RequestMethod.GET)
    public <T> Map<String, T> getById(@PathVariable String role_id) {
		try {
	        Role role = roleService.getById(role_id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据角色id查询角色成功", role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据角色id查询角色失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 新增角色
	 * @param role
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
    public <T> Map<String, T> create(@RequestBody Role role, Model model) {
    	try {
			roleService.add(role);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增角色成功", role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增角色失败. Info:" + e.getMessage(), role);
		}
    }

	/**
	 * 删除角色
	 * @param role_id
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/{role_id}", method = RequestMethod.DELETE)
    public <T> Map<String, T> deleteById(@PathVariable String role_id) {
    	try {
			roleService.delete(role_id);
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除角色成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除角色失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 修改角色
	 * @param role
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
    public <T> Map<String, T> update(@RequestBody Role role, Model model) {
    	try {
			roleService.update(role);
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改角色成功", role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改角色失败. Info:" + e.getMessage(), role);
		}
    }
    
}
