
package com.buffalo.sys.controller;


import com.buffalo.sys.model.Role;
import com.buffalo.sys.model.RolePermission;
import com.buffalo.sys.service.RolePermissionService;
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
@RequestMapping("/sys/role/permission")
public class RolePermissionController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RolePermissionService rolePermissionService;
	@Autowired
	private RoleService roleService;

	/**
	 * 根据role_id查询角色权限列表
	 * @param role_id
	 * @param <T>
	 * @return
	 */
    @RequestMapping(value="/rid/{role_id}", method = RequestMethod.GET)
    public <T> Map<String, T> getListByRoleId(@PathVariable String role_id) {
		try {
			List<RolePermission> list = rolePermissionService.getListByRoleId(role_id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据role_id查询角色权限列表成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据role_id查询角色权限列表失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 查询角色权限
	 * @param role_permission_id
	 * @param <T>
	 * @return
	 */
    @RequestMapping(value="/pid/{role_permission_id}", method = RequestMethod.GET)
    public <T> Map<String, T> getListByPermissionId(@PathVariable String role_permission_id) {
		try {
	        List<RolePermission> list = rolePermissionService.getListByPermissionId(role_permission_id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询角色权限成功", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询角色权限失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 修改角色权限
	 * @param rolePermission
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
    public <T> Map<String, T> update(@RequestBody RolePermission rolePermission, Model model) {
    	try {
    		String roleId = rolePermission.getRole_id();
    		String[] rolePermissions= rolePermission.getRole_permissions();
    		
    		if (roleId == null) {
    			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "角色不存在", rolePermission);
    		}
    		
    		Role role = roleService.getById(roleId);
    		if (role == null) { // 判断角色是否存在
    			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "角色不存在", rolePermission);
    		}

    		// 删除角色下拥有的权限
    		rolePermissionService.deleteByRoleId(rolePermission.getRole_id());
    		
    		// 更新权限
    		if (rolePermissions.length > 0) {
    			RolePermission permission = new RolePermission();
    			permission.setRole_id(roleId);
    			for (String permission_id : rolePermissions) {
					permission.setPermission_id(permission_id);
					rolePermissionService.add(permission);
				}
    		}
    		
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改角色权限成功", rolePermission);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改角色权限失败. Info:" + e.getMessage(), rolePermission);
		}
    }

}
