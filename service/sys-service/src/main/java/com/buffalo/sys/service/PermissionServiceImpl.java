
package com.buffalo.sys.service;


import com.buffalo.sys.mapper.PermissionMapper;
import com.buffalo.sys.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private OperateLogService orderLogService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public Permission getById(String id) throws Exception {
		return permissionMapper.getById(id);
	}

	@Override
	public void add(Permission permission) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"权限","添加权限，权限id："+permission.getPermission_id());
		permissionMapper.add(permission);
	}

	@Override
	public void update(Permission permission) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"权限","修改权限，权限id："+permission.getPermission_id());
		permissionMapper.update(permission);
	}

	@Override
	public void delete(String id) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"权限","删除权限，权限id："+id);
		permissionMapper.delete(id);

	}

	@Override
	public List<Permission> getPermissionByMenuId(String menu_id) {
		return permissionMapper.getPermissionByMenuId(menu_id);
	}

	@Override
	public List<Permission> list() throws Exception {
		return permissionMapper.list();
	}


}
