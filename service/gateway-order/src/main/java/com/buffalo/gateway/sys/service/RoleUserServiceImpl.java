
package com.buffalo.gateway.sys.service;

import com.buffalo.gateway.sys.mapper.RoleUserMapper;
import com.buffalo.gateway.sys.model.RoleUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class RoleUserServiceImpl implements RoleUserService {

	@Autowired
	private RoleUserMapper roleUserMapper;

	@Autowired
	private OperateLogService orderLogService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		roleUserMapper.delete(id);

	}

	@Override
	public List<RoleUser> getByUserId(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return roleUserMapper.getByUserId(user_id);
	}

	@Override
	public List<Map> getByRoleId(String role_id) throws Exception {
		// TODO Auto-generated method stub
		return roleUserMapper.getByRoleId(role_id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void bind(RoleUser roleUser) throws Exception {
		List<String> userIdList = roleUser.getUserIdList();
		if(userIdList.size()<=0){
			throw new Exception("没绑定用户");
		}

		//删除之前绑定的用户
		roleUserMapper.deleteByRoleId(roleUser.getRole_id());

		//绑定新的用户
		for(String userId : userIdList){
			roleUser.setUser_id(userId);
			roleUserMapper.add(roleUser);
		}

		//操作日志
		orderLogService.addSDCLog(request.getHeader("userid"),"角色用户","添加角色用户，id："+roleUser.getRole_user_id());
	}

	@Override
	public void deleteByRoleId(String role_id) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"角色用户","删除角色用户，角色id："+role_id);
		roleUserMapper.deleteByRoleId(role_id);
	}

}
