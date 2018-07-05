
package com.buffalo.gateway.authentication.mapper;

import com.buffalo.gateway.authentication.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {


    public User getById(String id);

    public User getByLoginName(String userName);
}
