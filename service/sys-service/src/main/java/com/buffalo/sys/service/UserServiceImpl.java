
package com.buffalo.sys.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.buffalo.sys.mapper.MenuMapper;
import com.buffalo.sys.mapper.PermissionMapper;
import com.buffalo.sys.mapper.RolePermissionMapper;
import com.buffalo.sys.mapper.UserMapper;
import com.buffalo.sys.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleUserService roleUserService;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private OperateLogService orderLogService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public User getById(String id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getById(id);
	}

	@Override
	public void add(User user) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"用户","添加用户，登录名："+user.getLogin_name());
		userMapper.add(user);
	}

	@Override
	public void update(User user) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"用户","修改用户，登录名："+user.getLogin_name());
		userMapper.update(user);
	}

	@Override
	public void delete(String id) throws Exception {
		User user = userMapper.getById(id);
		orderLogService.addSDCLog(request.getHeader("userid"),"用户","删除用户，登录名："+user.getLogin_name());
		userMapper.delete(id);

	}

	@Override
	public User getByLoginName(String userName) throws Exception {
		return userMapper.getByLoginName(userName);
	}

	@Override
	public List<User> list() throws Exception {
		return userMapper.list();
	}

	@Override
	public void updatePwd(User user) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"用户","修改用户密码，登录名："+user.getLogin_name());
		userMapper.updatePwd(user);
	}

	@Override
	public Set<Permission> getPermissionsByUserId(String user_id) throws Exception {
		Set<Permission> permissionSet = new HashSet<Permission>();
		String loginName = userMapper.getById(user_id).getLogin_name();
		if(loginName.equals("admin")){
			permissionSet.addAll(permissionMapper.list());
		}else{
			Set<String> permissionIdSet = new HashSet<String>();
			List<RoleUser> roleList = roleUserService.getByUserId(user_id);
			if(roleList.size()>0) {
				for(RoleUser role : roleList) {
					List<RolePermission> rolePermissionList =  rolePermissionMapper.getListByRoleId(role.getRole_id());
					for(RolePermission rolePermission : rolePermissionList) {
						Permission permission = permissionMapper.getById(rolePermission.getPermission_id());
						if(permissionIdSet.contains(permission.getPermission_id())==false){
							permissionIdSet.add(permission.getPermission_id());
							permissionSet.add(permission);
						}
					}
				}
			}
		}
		return permissionSet;
	}

	@Override
	public Set<Menu> getMenusByUserId(String user_id) throws Exception {
		Set<Menu> menuSet = new HashSet<Menu>();
		String loginName = userMapper.getById(user_id).getLogin_name();
		if(loginName.equals("admin")){
			menuSet.addAll(menuMapper.list());
		}else{
			Set<Menu> rootMenuSet = menuMapper.rootMenuList();
			menuSet.addAll(rootMenuSet);
			Set<String> menuIdSet = new HashSet<String>();
			Set<Permission> permissionSet = this.getPermissionsByUserId(user_id);
			for(Permission permission : permissionSet){
				String menuId = permission.getMenu_id();
				Menu menu = menuMapper.getById(menuId);
				if(menuId!=null && menuIdSet.contains(menuId)==false){
					menuIdSet.add(menuId);
					menuSet.add(menu);
				}
			}
		}
		return menuSet;
	}

	@Override
	public String getUserIdByLoginName(String login_name) {
		User user = userMapper.getByLoginName(login_name);
		return user.getUser_id();
	}
}
