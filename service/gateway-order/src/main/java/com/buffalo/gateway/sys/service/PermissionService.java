
package com.buffalo.gateway.sys.service;

import com.buffalo.gateway.sys.model.Permission;

import java.util.List;

public interface PermissionService {

    public List<Permission> list() throws Exception;

    public Permission getById(String id) throws Exception;

    public void add(Permission permission) throws Exception;

    public void update(Permission permission) throws Exception;

    public void delete(String id) throws Exception;

    public List<Permission> getPermissionByMenuId(String menu_id);
}
