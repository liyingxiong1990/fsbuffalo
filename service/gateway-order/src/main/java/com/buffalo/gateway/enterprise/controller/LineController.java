
package com.buffalo.gateway.enterprise.controller;


import com.buffalo.gateway.enterprise.model.Line;
import com.buffalo.gateway.enterprise.service.LineService;
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
@RequestMapping("/order/enterprise/line")
public class LineController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private LineService lineService;

	/**
	 * 查询所有路线
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public <T> Map<String, T> getAllLine() {
		try {
			List<Line> list = lineService.list("");
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询所有路线成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询所有路线失败. " + e.getMessage());
		}
	}

	/**
	 * 查询路线列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<Line> list = lineService.list(keyword);
			PageInfo<Line> pageInfo = new PageInfo<Line>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询路线列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询路线列表失败. " + e.getMessage());
		}
    }

	/**
	 * 新增路线
	 * @param line
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> create(@RequestBody Line line, Model model) {
		try {
			line = lineService.add(line);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增路线成功", line);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增路线失败. " + e.getMessage(), line);
		}
	}

	/**
	 * 修改路线
	 * @param line
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> update(@RequestBody Line line, Model model) {
		try {
			lineService.update(line);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改路线成功", line);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改路线失败. " + e.getMessage(), line);
		}
	}

	/**
	 * 删除路线
	 * @param line
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> delete(@RequestBody Line line, Model model) {
		try {
			lineService.delete(line);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除路线成功", line);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除路线失败. " + e.getMessage(), line);
		}
	}

}
