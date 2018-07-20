package com.buffalo.gateway.sys.service;

import com.buffalo.gateway.sys.model.Department;
import java.util.List;

public interface DepartmentService {

    public List<Department> getAll();

    public Department get(int dept_id);

    public void add(Department department) throws Exception;

    public void update(Department department) throws Exception;

    public void delete(int dept_id) throws Exception;

}
