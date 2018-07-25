
package com.buffalo.gateway.statistic.controller;

import com.buffalo.gateway.statistic.service.StatisticService;
import com.buffalo.gateway.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private StatisticService statisticService;

	/**
	 * 今天销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/todaySales", method = RequestMethod.GET)
	public <T> Map<String, T> todaySales() {
		try {
			List<Map> result = statisticService.todaySales();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询今天销量成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询今天销量失败. " + e.getMessage());
		}
	}

	/**
	 * 本月销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/thisMonthSales", method = RequestMethod.GET)
	public <T> Map<String, T> thisMonthSales() {
		try {
			List<Map> result = statisticService.thisMonthSales();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询本月销量成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询本月销量失败. " + e.getMessage());
		}
	}


	/**
	 * 本年销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/thisYearSales", method = RequestMethod.GET)
	public <T> Map<String, T> thisYearSales() {
		try {
			List<Map> result = statisticService.thisYearSales();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询本年销量成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询本年销量失败. " + e.getMessage());
		}
	}

	/**
	 * 今天进仓量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/todayCheckin", method = RequestMethod.GET)
	public <T> Map<String, T> todayCheckin() {
		try {
			List<Map> result = statisticService.todayCheckin();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询今天进仓量成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询今天进仓量失败. " + e.getMessage());
		}
	}

	/**
	 * 专卖店销量排名
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/storeSales", method = RequestMethod.GET)
    public <T> Map<String, T> storeSales(@RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime) {
		try {
			List<Map> list = statisticService.storeSales(startTime,endTime);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询专卖店销量排名成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询专卖店销量排名失败. " + e.getMessage());
		}
    }

	/**
	 * 当天产品销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/productSalesThisMonth", method = RequestMethod.GET)
	public <T> Map<String, T> productSalesThisMonth() {
		try {
			List<Map> list = statisticService.productSalesThisMonth();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询当天产品销量成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询当天产品销量失败. " + e.getMessage());
		}
	}

	/**
	 * 今年各个月销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/salesEveryMonth", method = RequestMethod.GET)
	public <T> Map<String, T> salesEveryMonth() {
		try {
			List<Map> list = statisticService.salesEveryMonth();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询今年各个月销量成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询今年各个月销量失败. " + e.getMessage());
		}
	}

	/**
	 * 本月各天销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/salesEveryDay", method = RequestMethod.GET)
	public <T> Map<String, T> salesEveryDay() {
		try {
			List<Map> list = statisticService.salesEveryDay();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询本月各天销量成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询本月各天销量失败. " + e.getMessage());
		}
	}



}
