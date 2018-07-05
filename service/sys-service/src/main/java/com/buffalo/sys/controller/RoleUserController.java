
package com.buffalo.sys.controller;


import com.buffalo.sys.model.RoleUser;
import com.buffalo.sys.service.RoleUserService;
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
@RequestMapping("/sys/role/user")
public class RoleUserController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleUserService roleService;

	/**
	 * 根据角色id查询角色用户
	 * @param role_id
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/rid/{role_id}", method = RequestMethod.GET)
	public <T> Map<String, T> getByRoleId(@PathVariable String role_id) {
		try {
			List<Map> roleUser = roleService.getByRoleId(role_id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据角色id查询角色用户成功", roleUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据角色id查询角色用户失败. Info:" + e.getMessage());
		}
	}

	/**
	 * 根据用户id查询用户角色列表
	 * @param user_id
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/uid/{user_id}", method = RequestMethod.GET)
	public <T> Map<String, T> getByUserId(@PathVariable String user_id) {
		try {
			List<RoleUser> list = roleService.getByUserId(user_id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据用户id查询用户角色列表成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据用户id查询用户角色列表失败. Info:" + e.getMessage());
		}
	}


	/**
	 * 绑定用户角色
	 * @param roleUser
	 * @param model
	 * @param <T>
	 * @return
	 */
    @RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
    public <T> Map<String, T> bind(@RequestBody RoleUser roleUser, Model model) {
    	try {
			roleService.bind(roleUser);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "绑定用户角色成功", roleUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "绑定用户角色失败. Info:" + e.getMessage(), roleUser);
		}
    }

	/**
	 * 删除用户角色
	 * @param role_user_id
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/{role_user_id}", method = RequestMethod.DELETE)
    public <T> Map<String, T> deleteById(@PathVariable String role_user_id) {
    	try {
			roleService.delete(role_user_id);
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除用户角色成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除用户角色失败. Info:" + e.getMessage());
		}
    }
}
