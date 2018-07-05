
package com.buffalo.sys.service;

import com.buffalo.sys.mapper.RoleMapper;
import com.buffalo.sys.mapper.RoleUserMapper;
import com.buffalo.sys.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RoleUserMapper roleUserMapper;

	@Autowired
	private OperateLogService orderLogService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public Role getById(String id) throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.getById(id);
	}

	@Override
	public void add(Role role) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"角色","添加角色："+role.getName());
		roleMapper.add(role);
	}

	@Override
	public void update(Role role) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"角色","修改角色："+role.getName());
		roleMapper.update(role);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(String id) throws Exception {
		List<Map> roleUserList = roleUserMapper.getByRoleId(id);
		for(Map roleUserMap : roleUserList){
			roleUserMapper.delete((String)roleUserMap.get("role_user_id"));
		}
 		Role role = roleMapper.getById(id);
		orderLogService.addSDCLog(request.getHeader("userid"),"角色","删除角色："+role.getName());
		roleMapper.delete(id);

	}

	@Override
	public List<Role> list() throws Exception {
		return roleMapper.list();
	}


}
