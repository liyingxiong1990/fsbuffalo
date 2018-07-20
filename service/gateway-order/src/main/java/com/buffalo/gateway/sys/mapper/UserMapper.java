
package com.buffalo.gateway.sys.mapper;

import com.buffalo.gateway.sys.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public List<User> list();

    public User getById(String id);

    public void add(User user);

    public void update(User user);

    public void delete(String id);
    
    public void updatePwd(User user);

    public User getByLoginName(String userName);
}
