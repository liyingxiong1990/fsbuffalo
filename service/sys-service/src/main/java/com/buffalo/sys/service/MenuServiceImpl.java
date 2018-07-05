
package com.buffalo.sys.service;

import com.buffalo.sys.mapper.MenuMapper;
import com.buffalo.sys.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private OperateLogService orderLogService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public Menu getById(String id) throws Exception {
		// TODO Auto-generated method stub
		return menuMapper.getById(id);
	}

	@Override
	public void add(Menu menu) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"菜单","添加菜单，菜单："+menu.getName());
		menuMapper.add(menu);
	}

	@Override
	public void update(Menu menu) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"菜单","修改菜单，菜单："+menu.getName());
		menuMapper.update(menu);
	}

	@Override
	public void delete(String id) throws Exception {
		Menu menu = menuMapper.getById(id);
		orderLogService.addSDCLog(request.getHeader("userid"),"菜单","删除菜单，菜单："+menu.getName());
		menuMapper.delete(id);

	}

	@Override
	public List<Menu> list() throws Exception {
		return menuMapper.list();
	}


}
