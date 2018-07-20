
package com.buffalo.gateway.authentication.service;

import com.buffalo.gateway.authentication.model.Menu;
import com.buffalo.gateway.authentication.model.Permission;
import com.buffalo.gateway.authentication.model.User;

import java.util.Set;

public interface AuthUserService {

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     * @throws Exception
     */
    public User getByLoginName(String userName) throws Exception;

    public Set<Permission> getPermissionsByUserId(String user_id) throws Exception;

    public Set<Menu> getMenusByUserId(String user_id) throws Exception;
}
