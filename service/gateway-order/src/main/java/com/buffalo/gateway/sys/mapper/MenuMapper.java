
package com.buffalo.gateway.sys.mapper;
import com.buffalo.gateway.sys.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MenuMapper {

    public List<Menu> list();

    public Menu getById(String id);

    public void add(Menu menu);

    public void update(Menu menu);

    public void delete(String id);

    public Set<Menu> rootMenuList();
    
}
