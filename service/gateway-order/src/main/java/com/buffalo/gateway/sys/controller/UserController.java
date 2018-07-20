
package com.buffalo.gateway.sys.controller;

import com.buffalo.gateway.sys.model.Menu;
import com.buffalo.gateway.sys.model.Permission;
import com.buffalo.gateway.sys.model.User;
import com.buffalo.gateway.sys.service.UserService;
import com.buffalo.gateway.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/sys/user")
public class UserController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

	/**
	 * 根据用户名查询用户信息
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/info/{login_name}", method = RequestMethod.GET)
	public <T> Map<String, T> getInfoByLoginName(@PathVariable String login_name) {
		try {
			Map<String, Object> result = new HashMap<>();
			User user = userService.getByLoginName(login_name);
			user.setPwd("");
			Set<Permission> permissionList = userService.getPermissionsByUserId(user.getUser_id());
			Set<Menu> menuList = userService.getMenusByUserId(user.getUser_id());
			result.put("user",user);
			result.put("permissionList",permissionList);
			result.put("menuList",menuList);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据用户名查询用户信息成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据用户名查询用户信息失败. Info:" + e.getMessage());
		}
	}

	/**
	 * 根据用户id查询权限列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/permissions/{user_id}", method = RequestMethod.GET)
	public <T> Map<String, T> getPermissionsByUserId(@PathVariable String user_id) {
		try {
			Set<Permission> permissionList = userService.getPermissionsByUserId(user_id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据用户id查询权限列表成功", permissionList);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据用户id查询权限列表失败. Info:" + e.getMessage());
		}
	}

	/**
	 * 根据用户id查询菜单列表
	 * @param <T>
	 * @returndd
	 */
	@RequestMapping(value="/menus/{user_id}", method = RequestMethod.GET)
	public <T> Map<String, T> getMenusByUserId(@PathVariable String user_id) {
		try {
			Set<Menu> menuList = userService.getMenusByUserId(user_id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据用户id查询菜单列表成功", menuList);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据用户id查询菜单列表失败. Info:" + e.getMessage());
		}
	}

	/**
	 * 查询用户列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll() {
		try {
			List<User> list = userService.list();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询用户列表成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询用户列表失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 查询用户
	 * @param user_id
	 * @param <T>
	 * @return
	 */
    @RequestMapping(value="/{user_id}", method = RequestMethod.GET)
    public <T> Map<String, T> getById(@PathVariable String user_id) {
		try {
	        User user = userService.getById(user_id);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询用户成功", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询用户失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 新增用户
	 * @param user
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
    public <T> Map<String, T> create(@RequestBody User user, Model model) {
    	try {
			userService.add(user);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增用户成功", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增用户失败. Info:" + e.getMessage(), user);
		}
    }

	/**
	 * 删除用户
	 * @param user_id
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/{user_id}", method = RequestMethod.DELETE)
    public <T> Map<String, T> deleteById(@PathVariable String user_id) {
    	try {
			userService.delete(user_id);
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除用户成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除用户失败. Info:" + e.getMessage());
		}
    }

	/**
	 * 修改用户
	 * @param user
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
    public <T> Map<String, T> update(@RequestBody User user, Model model) {
    	try {
			userService.update(user);
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改用户成功", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改用户失败. Info:" + e.getMessage(), user);
		}
    }

	/**
	 * 修改用户密码
	 * @param user
	 * @param model
	 * @param <T>
	 * @return
	 */
    @RequestMapping(value="/pwd", method = RequestMethod.PUT, consumes="application/json")
    public <T> Map<String, T> updatePwd(@RequestBody User user, Model model) {
    	try {
			userService.updatePwd(user);
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改用户密码成功", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改用户密码失败. Info:" + e.getMessage(), user);
		}
    }
}
