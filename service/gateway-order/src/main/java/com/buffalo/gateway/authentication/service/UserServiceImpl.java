
package com.buffalo.gateway.authentication.service;

import com.buffalo.gateway.authentication.mapper.*;
import com.buffalo.gateway.authentication.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleUserMapper roleUserMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public User getByLoginName(String userName) throws Exception {
		return userMapper.getByLoginName(userName);
	}



	@Override
	public Set<Permission> getPermissionsByUserId(String user_id) throws Exception {
		Set<Permission> permissionSet = new HashSet<Permission>();
		String loginName = userMapper.getById(user_id).getLogin_name();
		if(loginName.equals("admin")){
			permissionSet.addAll(permissionMapper.list());
		}else{
			Set<String> permissionIdSet = new HashSet<String>();
			List<RoleUser> roleList = roleUserMapper.getByUserId(user_id);
			if(roleList.size()>0) {
				for(RoleUser role : roleList) {
					List<RolePermission> rolePermissionList =  rolePermissionMapper.getListByRoleId(role.getRole_id());
					for(RolePermission rolePermission : rolePermissionList) {
						Permission permission = permissionMapper.getById(rolePermission.getPermission_id());
						if(permission!=null){
							String permissionId = permission.getPermission_id();
							if(permissionId!=null && permissionIdSet.contains(permissionId)==false){
								permissionIdSet.add(permissionId);
								permissionSet.add(permission);
							}
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


}
