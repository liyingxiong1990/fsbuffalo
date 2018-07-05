
package com.buffalo.enterprise.controller;

import com.buffalo.enterprise.model.Product;
import com.buffalo.enterprise.service.ProductService;
import com.buffalo.util.ResponseUtil;
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
@RequestMapping("/order/enterprise/product")
public class ProductController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ProductService productService;

	/**
	 * 查询所有产品
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public <T> Map<String, T> getAllProduct() {
		try {
			List<Product> list = productService.list("");
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询所有产品成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询所有产品失败. " + e.getMessage());
		}
	}

	/**
	 * 查询产品列表
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
    public <T> Map<String, T> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
									 @RequestParam(required = false, defaultValue = "") String keyword) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<Product> list = productService.list(keyword);
			PageInfo<Product> pageInfo = new PageInfo<Product>(list);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询产品列表成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询产品列表失败. " + e.getMessage());
		}
    }

	/**
	 * 新增产品
	 * @param product
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST, consumes="application/json")
	public <T> Map<String, T> create(@RequestBody Product product, Model model) {
		try {
			product = productService.add(product);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "新增产品成功", product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "新增产品失败. " + e.getMessage(), product);
		}
	}

	/**
	 * 修改产品
	 * @param product
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> update(@RequestBody Product product, Model model) {
		try {
			productService.update(product);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "修改产品成功", product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "修改产品失败. " + e.getMessage(), product);
		}
	}

	/**
	 * 删除产品
	 * @param product
	 * @param model
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.PUT, consumes="application/json")
	public <T> Map<String, T> delete(@RequestBody Product product, Model model) {
		try {
			productService.delete(product);
			return (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除产品成功", product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除产品失败. " + e.getMessage(), product);
		}
	}

}
