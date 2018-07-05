package com.buffalo.sys.mapper;

import java.util.List;

import com.buffalo.sys.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper {
    public List<Department> list();

    public Department getByDeptId(int dept_id);

    public void add(Department department);

    public void update(Department department);

    public void delete(int dept_id);

}
