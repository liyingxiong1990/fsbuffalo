
package com.buffalo.gateway.sys.mapper;
import com.buffalo.gateway.sys.model.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionMapper {
	
    /**
     * 获取角色拥有的权限
     * @param role_id
     * @return
     * XuYao
     */
    public List<RolePermission> getListByRoleId(String role_id);
    
    /**
     * 获取权限拥有的角色
     * @param permission_id
     * @return
     * XuYao
     */
    public List<RolePermission> getListByPermissionId(String permission_id);

    public void add(RolePermission rolePermission);

    public void update(RolePermission rolePermission);

    public void delete(String role_permission_id);
    
    /**
     * 删除权限下的角色
     * @param permission_id
     * XuYao
     */
    public void deleteByPermissionId(String permission_id);
    
    /**
     * 删除角色下的权限
     * @param role_id
     * XuYao
     */
    public void deleteByRoleId(String role_id);
    
}
