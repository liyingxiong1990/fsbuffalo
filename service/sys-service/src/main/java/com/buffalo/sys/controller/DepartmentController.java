package com.buffalo.sys.controller;

import java.util.List;
import java.util.Map;

import com.buffalo.sys.model.Department;
import com.buffalo.sys.service.DepartmentService;
import com.buffalo.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sys/department")
public class DepartmentController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private DepartmentService service;

    /**
     * 部门列表
     * @param <T>
     * @return
     */
    @RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll() {
        List<Department> list = service.getAll();
        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "获取部门列表成功", list);
    }

    /**
     * 查询部门
     * @param dept_id
     * @param <T>
     * @return
     */
    @RequestMapping(value="/{dept_id}", method = RequestMethod.GET)
    public <T> Map<String, T> getDepartment(@PathVariable int dept_id) {
    	Department department = service.get(dept_id);
        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询部门成功", department);
    }

    /**
     * 新增部门
     * @param department
     * @param model
     * @param <T>
     * @return
     */
    @RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
    public <T> Map<String, T> newDepartment(@RequestBody Department department, Model model) {
        try {
            service.add(department);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增部门成功", department);
    }

    /**
     * 删除部门
     * @param dept_id
     * @param <T>
     * @return
     */
    @RequestMapping(value="/{dept_id}", method = RequestMethod.DELETE)
    public <T> Map<String, T> deleteDepartment(@PathVariable int dept_id) {
        try {
            service.delete(dept_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除部门成功");
    }

    /**
     * 修改部门
     * @param department
     * @param model
     * @param <T>
     * @return
     */
    @RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
    public <T> Map<String, T> putDepartment(@RequestBody Department department, Model model) {
        try {
            service.update(department);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改部门成功");
    }


}
