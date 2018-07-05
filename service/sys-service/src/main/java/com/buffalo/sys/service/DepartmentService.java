package com.buffalo.sys.service;

import java.util.List;
import com.buffalo.sys.model.Department;

public interface DepartmentService {

    public List<Department> getAll();

    public Department get(int dept_id);

    public void add(Department department) throws Exception;

    public void update(Department department) throws Exception;

    public void delete(int dept_id) throws Exception;

}
