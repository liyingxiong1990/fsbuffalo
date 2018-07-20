
package com.buffalo.gateway.authentication.service;

import com.buffalo.gateway.authentication.mapper.*;
import com.buffalo.gateway.authentication.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AuthUserServiceImpl implements AuthUserService {

	@Autowired
	private AuthUserMapper authUserMapper;

	@Autowired
	private AuthRoleUserMapper authRoleUserMapper;

	@Autowired
	private AuthRolePermissionMapper authRolePermissionMapper;

	@Autowired
	private AuthPermissionMapper authPermissionMapper;

	@Autowired
	private AuthMenuMapper authMenuMapper;

	@Override
	public User getByLoginName(String userName) throws Exception {
		return authUserMapper.getByLoginName(userName);
	}



	@Override
	public Set<Permission> getPermissionsByUserId(String user_id) throws Exception {
		Set<Permission> permissionSet = new HashSet<Permission>();
		String loginName = authUserMapper.getById(user_id).getLogin_name();
		if(loginName.equals("admin")){
			permissionSet.addAll(authPermissionMapper.list());
		}else{
			Set<String> permissionIdSet = new HashSet<String>();
			List<RoleUser> roleList = authRoleUserMapper.getByUserId(user_id);
			if(roleList.size()>0) {
				for(RoleUser role : roleList) {
					List<RolePermission> rolePermissionList =  authRolePermissionMapper.getListByRoleId(role.getRole_id());
					for(RolePermission rolePermission : rolePermissionList) {
						Permission permission = authPermissionMapper.getById(rolePermission.getPermission_id());
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
		String loginName = authUserMapper.getById(user_id).getLogin_name();
		if(loginName.equals("admin")){
			menuSet.addAll(authMenuMapper.list());
		}else{
			Set<Menu> rootMenuSet = authMenuMapper.rootMenuList();
			menuSet.addAll(rootMenuSet);
			Set<String> menuIdSet = new HashSet<String>();
			Set<Permission> permissionSet = this.getPermissionsByUserId(user_id);
			for(Permission permission : permissionSet){
				String menuId = permission.getMenu_id();
				Menu menu = authMenuMapper.getById(menuId);
				if(menuId!=null && menuIdSet.contains(menuId)==false){
					menuIdSet.add(menuId);
					menuSet.add(menu);
				}
			}
		}
		return menuSet;
	}


}
