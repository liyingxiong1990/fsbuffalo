
package com.buffalo.gateway.sys.service;

import com.buffalo.gateway.sys.model.Role;

import java.util.List;

public interface RoleService {
    
    public List<Role> list() throws Exception;

    public Role getById(String id) throws Exception;

    public void add(Role role) throws Exception;

    public void update(Role role) throws Exception;

    public void delete(String id) throws Exception;

}
