
package com.buffalo.gateway.sys.service;


import com.buffalo.gateway.sys.mapper.RolePermissionMapper;
import com.buffalo.gateway.sys.model.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private OperateLogService orderLogService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void add(RolePermission rolePermission) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"角色权限","添加角色权限，id："+rolePermission.getRole_permission_id());
		rolePermissionMapper.add(rolePermission);
	}

	@Override
	public void update(RolePermission rolePermission) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"角色权限","修改角色权限，id："+rolePermission.getRole_permission_id());
		rolePermissionMapper.update(rolePermission);
	}

	@Override
	public void delete(String id) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"角色权限","删除角色权限，id："+id);
		rolePermissionMapper.delete(id);

	}

	@Override
	public List<RolePermission> getListByRoleId(String role_id) throws Exception {
		// TODO Auto-generated method stub
		return rolePermissionMapper.getListByRoleId(role_id);
	}

	@Override
	public List<RolePermission> getListByPermissionId(String permission_id) throws Exception {
		// TODO Auto-generated method stub
		return rolePermissionMapper.getListByPermissionId(permission_id);
	}

	@Override
	public void deleteByPermissionId(String permission_id) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"角色权限","删除角色权限，权限id："+permission_id);
		rolePermissionMapper.deleteByPermissionId(permission_id);
	}

	@Override
	public void deleteByRoleId(String role_id) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"角色权限","删除角色权限，角色id："+role_id);
		rolePermissionMapper.deleteByRoleId(role_id);
	}


}
