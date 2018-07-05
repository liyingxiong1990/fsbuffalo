
package com.buffalo.sys.mapper;

import com.buffalo.sys.model.RoleUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleUserMapper {

    /**
     * 获取用户拥有的角色
     * @param user_id
     * @return
     * XuYao
     */
    public List<RoleUser> getByUserId(String user_id);

    /**
     * 获取角色拥有的用户
     * @param role_id
     * @return
     * XuYao
     */
    public List<Map> getByRoleId(String role_id);

    public void add(RoleUser roleUser);

    public void delete(String id);
    
    /**
     * 删除角色拥有的用户
     * @param role_id
     * XuYao
     */
    public void deleteByRoleId(String role_id);
    
}
