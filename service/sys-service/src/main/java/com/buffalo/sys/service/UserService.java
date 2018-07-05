
package com.buffalo.sys.service;

import java.util.List;
import java.util.Set;

import com.buffalo.sys.model.Menu;
import com.buffalo.sys.model.Permission;
import com.buffalo.sys.model.User;

public interface UserService {

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     * @throws Exception
     */
    public User getByLoginName(String userName) throws Exception;

    /**
     * 查询所有用户
     * @return 所有用户
     * XuYao
     */
    public List<User> list() throws Exception;

    /**
     * 根据用户编号查询用户
     * @param id
     * @return 用户
     * XuYao
     */
    public User getById(String id) throws Exception;

    public void add(User user) throws Exception;

    public void update(User user) throws Exception;

    public void delete(String id) throws Exception;
    
    /**
     * 根据用户ID修改密码
     * @param user
     * @throws Exception
     * XuYao
     */
    public void updatePwd(User user) throws Exception;


    public Set<Permission> getPermissionsByUserId(String user_id) throws Exception;

    public Set<Menu> getMenusByUserId(String user_id) throws Exception;

    public String getUserIdByLoginName(String login_name);
}
