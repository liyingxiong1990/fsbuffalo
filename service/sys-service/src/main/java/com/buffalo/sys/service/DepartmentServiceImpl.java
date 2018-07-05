package com.buffalo.sys.service;


import com.buffalo.sys.mapper.DepartmentMapper;
import com.buffalo.sys.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentMapper departmentMapper;

	@Autowired
	private OperateLogService orderLogService;

	@Autowired
	private HttpServletRequest request;
	
	@Override
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return departmentMapper.list();
	}

	@Override
	public Department get(int dept_id) {
		// TODO Auto-generated method stub
		return departmentMapper.getByDeptId(dept_id);
	}

	@Override
	public void add(Department department) throws Exception {

		orderLogService.addSDCLog(request.getHeader("userid"),"部门","添加部门，部门："+department.getDept_name());
		departmentMapper.add(department);
	}

	@Override
	public void update(Department department) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"部门","修改部门，部门："+department.getDept_name());
		departmentMapper.update(department);
	}

	@Override
	public void delete(int dept_id) throws Exception {
		Department department = departmentMapper.getByDeptId(dept_id);
		orderLogService.addSDCLog(request.getHeader("userid"),"部门","删除部门，部门："+department.getDept_name());
		// TODO Auto-generated method stub
		departmentMapper.delete(dept_id);
	}

}
