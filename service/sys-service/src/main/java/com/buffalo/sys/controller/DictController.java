
package com.buffalo.sys.controller;

import java.util.List;
import java.util.Map;

import com.buffalo.sys.model.Dict;
import com.buffalo.sys.service.DictService;
import com.buffalo.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sys/dict")
public class DictController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DictService service;

    /**
     * 查询字典列表
     * @param <T>
     * @return
     */
    @RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll() {
        List<Dict> list = service.getAll();
        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询字典列表成功", list);
    }

    /**
     * 根据dict_type查询字典列表
     * @param dict_type
     * @param <T>
     * @return
     */
    @RequestMapping(value="/{dict_type}", method = RequestMethod.GET)
    public <T> Map<String, T> getListByDictType(@PathVariable String dict_type) {
    	List<Dict> list = service.getListByDictType(dict_type);
        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据dict_type查询字典列表成功", list);
    }

    /**
     * 新增字典
     * @param dict
     * @param model
     * @param <T>
     * @return
     */
    @RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
    public <T> Map<String, T> newDict(@RequestBody Dict dict, Model model) {
        try {
            service.add(dict);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增字典成功", dict);
    }

    /**
     * 删除字典
     * @param dict_id
     * @param <T>
     * @return
     */
    @RequestMapping(value="/{dict_id}", method = RequestMethod.DELETE)
    public <T> Map<String, T> deleteDict(@PathVariable int dict_id) {
        try {
            service.delete(dict_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除字典成功");
    }

    /**
     * 修改字典
     * @param dict
     * @param model
     * @param <T>
     * @return
     */
    @RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
    public <T> Map<String, T> putDict(@RequestBody Dict dict, Model model) {
        try {
            service.update(dict);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改字典成功");
    }

}
