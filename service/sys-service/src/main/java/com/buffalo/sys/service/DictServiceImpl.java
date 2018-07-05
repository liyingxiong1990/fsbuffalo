
package com.buffalo.sys.service;


import com.buffalo.sys.mapper.DictMapper;
import com.buffalo.sys.model.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class DictServiceImpl implements DictService {

	@Autowired
	private DictMapper dictMapper;

	@Autowired
	private OperateLogService orderLogService;

	@Autowired
	private HttpServletRequest request;
	
	@Override
	public List<Dict> getAll() {
		// TODO Auto-generated method stub
		return dictMapper.list();
	}

	@Override
	public List<Dict> getListByDictType(String dict_tye) {
		// TODO Auto-generated method stub
		return dictMapper.getListByDictType(dict_tye);
	}


	@Override
	public void add(Dict dict) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"字典","添加字典，字典值："+dict.getDict_value()+"，编码："+dict.getDict_code());
		dictMapper.add(dict);
	}


	@Override
	public void update(Dict dict) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"字典","修改字典，字典值："+dict.getDict_value()+"，编码："+dict.getDict_code());
		dictMapper.update(dict);
	}
	
	@Override
	public void delete(int dict_id) throws Exception {
		orderLogService.addSDCLog(request.getHeader("userid"),"字典","删除字典，字典id："+dict_id);
		dictMapper.delete(dict_id);
	}

}
