
package com.buffalo.sys.mapper;
import com.buffalo.sys.model.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {

    public List<Permission> list();

    public Permission getById(String id);

    public void add(Permission permission);

    public void update(Permission permission);

    public void delete(String id);

    public List<Permission> getPermissionByMenuId(String menu_id);
}
