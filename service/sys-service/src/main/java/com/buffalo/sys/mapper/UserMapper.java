
package com.buffalo.sys.mapper;

import java.util.List;
import com.buffalo.sys.model.User;
import org.springframework.stereotype.Repository;

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
