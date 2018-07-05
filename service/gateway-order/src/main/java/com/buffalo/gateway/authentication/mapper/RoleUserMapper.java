
package com.buffalo.gateway.authentication.mapper;

import com.buffalo.gateway.authentication.model.RoleUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleUserMapper {

    /**
     * 获取用户拥有的角色
     * @param user_id
     * @return
     * XuYao
     */
    public List<RoleUser> getByUserId(String user_id);


    
}
