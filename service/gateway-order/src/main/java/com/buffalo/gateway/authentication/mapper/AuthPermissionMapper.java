
package com.buffalo.gateway.authentication.mapper;
import com.buffalo.gateway.authentication.model.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthPermissionMapper {

    public List<Permission> list();

    public Permission getById(String id);
}
