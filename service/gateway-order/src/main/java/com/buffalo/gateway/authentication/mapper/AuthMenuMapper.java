
package com.buffalo.gateway.authentication.mapper;
import com.buffalo.gateway.authentication.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthMenuMapper {

    public List<Menu> list();

    public Menu getById(String id);

    public Set<Menu> rootMenuList();
    
}
