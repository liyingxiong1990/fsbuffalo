
package com.buffalo.sys.service;

import com.buffalo.sys.model.Menu;

import java.util.List;

public interface MenuService {

    public List<Menu> list() throws Exception;

    public Menu getById(String id) throws Exception;

    public void add(Menu menu) throws Exception;

    public void update(Menu menu) throws Exception;

    public void delete(String id) throws Exception;

}
