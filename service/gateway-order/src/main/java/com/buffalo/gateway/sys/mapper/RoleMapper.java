
package com.buffalo.gateway.sys.mapper;
import com.buffalo.gateway.sys.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    public List<Role> list();

    public Role getById(String id);

    public void add(Role role);

    public void update(Role role);

    public void delete(String id);
    
}
