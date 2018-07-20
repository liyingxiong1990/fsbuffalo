
package com.buffalo.gateway.enterprise.controller;


import com.buffalo.gateway.enterprise.model.Deliverer;
import com.buffalo.gateway.enterprise.service.DelivererService;
import com.buffalo.gateway.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order/enterprise/deliverer")
public class DelivererController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private DelivererService delivererService;

	/**
	 * 查询全部外县市司机列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public <T> Map<String, T> getAllDeliverer() {
		try {
			List<Deliverer> list = delivererService.list("");
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询全部外县市司机列表成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询全部外县市司机列表失败. " + e.getMessage());
		}
	}

	/**
	 * 查询外县市司机列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<Deliverer> list = delivererService.list(keyword);
			PageInfo<Deliverer> pageInfo = new PageInfo<Deliverer>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询外县市司机列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询外县市司机列表失败. " + e.getMessage());
		}
    }

	/**
	 * 新增外县市司机
	 * @param deliverer
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> create(@RequestBody Deliverer deliverer, Model model) {
		try {
			deliverer = delivererService.add(deliverer);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增外县市司机成功", deliverer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增外县市司机失败. " + e.getMessage(), deliverer);
		}
	}

	/**
	 * 修改外县市司机
	 * @param deliverer
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> update(@RequestBody Deliverer deliverer, Model model) {
		try {
			delivererService.update(deliverer);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改外县市司机成功", deliverer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改外县市司机失败. " + e.getMessage(), deliverer);
		}
	}

	/**
	 * 删除外县市司机
	 * @param deliverer
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> delete(@RequestBody Deliverer deliverer, Model model) {
		try {
			delivererService.delete(deliverer);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除外县市司机成功", deliverer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除外县市司机失败. " + e.getMessage(), deliverer);
		}
	}

}
