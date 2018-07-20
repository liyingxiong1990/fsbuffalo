
package com.buffalo.gateway.sys.service;


import com.buffalo.gateway.sys.model.RolePermission;

import java.util.List;


public interface RolePermissionService {

    public List<RolePermission> getListByRoleId(String role_id) throws Exception;
    
    public List<RolePermission> getListByPermissionId(String permission_id) throws Exception;

    public void add(RolePermission rolePermission) throws Exception;

    public void update(RolePermission rolePermission) throws Exception;

    public void delete(String id) throws Exception;
    
    public void deleteByPermissionId(String permission_id) throws Exception;
    
    public void deleteByRoleId(String role_id) throws Exception;

}
