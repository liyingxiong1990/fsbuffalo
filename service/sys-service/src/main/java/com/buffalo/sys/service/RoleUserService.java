
package com.buffalo.sys.service;

import com.buffalo.sys.model.RoleUser;

import java.util.List;
import java.util.Map;

public interface RoleUserService {


    public List<RoleUser> getByUserId(String user_id) throws Exception;

    public List<Map> getByRoleId(String role_id) throws Exception;

    public void bind(RoleUser roleUser) throws Exception;

    public void delete(String id) throws Exception;
    
    public void deleteByRoleId(String role_id) throws Exception;

}
