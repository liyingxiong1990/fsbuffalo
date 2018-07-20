
package com.buffalo.gateway.authentication.mapper;
import com.buffalo.gateway.authentication.model.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRolePermissionMapper {
	
    /**
     * 获取角色拥有的权限
     * @param role_id
     * @return
     * XuYao
     */
    public List<RolePermission> getListByRoleId(String role_id);
    

    
}
