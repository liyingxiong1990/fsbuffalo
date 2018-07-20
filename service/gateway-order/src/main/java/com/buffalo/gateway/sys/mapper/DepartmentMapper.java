package com.buffalo.gateway.sys.mapper;

import com.buffalo.gateway.sys.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    public List<Department> list();

    public Department getByDeptId(int dept_id);

    public void add(Department department);

    public void update(Department department);

    public void delete(int dept_id);

}
