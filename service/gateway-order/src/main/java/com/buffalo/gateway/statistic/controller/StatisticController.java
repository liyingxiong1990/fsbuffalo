
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
	 * 本月产品销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/productSalesThisMonth", method = RequestMethod.GET)
	public <T> Map<String, T> productSalesThisMonth() {
		try {
			List<Map> list = statisticService.productSalesThisMonth();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询本月产品销量成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询本月产品销量失败. " + e.getMessage());
		}
	}

	/**
	 * 本月产品销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/productSalesThisYear", method = RequestMethod.GET)
	public <T> Map<String, T> productSalesThisYear() {
		try {
			List<Map> list = statisticService.productSalesThisYear();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询本月产品销量成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询本月产品销量失败. " + e.getMessage());
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

	/**
	 * 外线本月销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/deliverySalesThisMonth", method = RequestMethod.GET)
	public <T> Map<String, T> deliverySalesThisMonth() {
		try {
			List<Map> list = statisticService.deliverySalesThisMonth();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询外线本月销量成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询外线本月销量失败. " + e.getMessage());
		}
	}

	/**
	 * 外线本月产品分析
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/deliveryProductsThisMonth", method = RequestMethod.GET)
	public <T> Map<String, T> deliveryProductsThisMonth() {
		try {
			List<Map> list = statisticService.deliveryProductsThisMonth();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询外线本月产品分析成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询外线本月产品分析失败. " + e.getMessage());
		}
	}

	/**
	 * 外线本年销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/deliverySalesThisYear", method = RequestMethod.GET)
	public <T> Map<String, T> deliverySalesThisYear() {
		try {
			List<Map> list = statisticService.deliverySalesThisYear();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询外线本年销量成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询外线本年销量失败. " + e.getMessage());
		}
	}

	/**
	 * 外线本年产品分析
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/deliveryProductsThisYear", method = RequestMethod.GET)
	public <T> Map<String, T> deliveryProductsThisYear() {
		try {
			List<Map> list = statisticService.deliveryProductsThisYear();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询外线本年产品分析成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询外线本年产品分析失败. " + e.getMessage());
		}
	}

	/**
	 * 本月专卖店销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/storeSalesThisMonth", method = RequestMethod.GET)
	public <T> Map<String, T> storeSalesThisMonth() {
		try {
			List<Map> list = statisticService.storeSalesThisMonth();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询本月专卖店销量成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询本月专卖店销量失败. " + e.getMessage());
		}
	}

	/**
	 * 本月专卖店产品销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/storeProductSalesThisMonth", method = RequestMethod.GET)
	public <T> Map<String, T> storeProductSalesThisMonth() {
		try {
			List<Map> list = statisticService.storeProductSalesThisMonth();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询本月专卖店产品销量成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询本月专卖店产品销量失败. " + e.getMessage());
		}
	}

	/**
	 * 今年专卖店销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/storeSalesThisYear", method = RequestMethod.GET)
	public <T> Map<String, T> storeSalesThisYear() {
		try {
			List<Map> list = statisticService.storeSalesThisYear();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询今年专卖店销量成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询今年专卖店销量失败. " + e.getMessage());
		}
	}

	/**
	 * 今年专卖店产品销量
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/storeProductSalesThisYear", method = RequestMethod.GET)
	public <T> Map<String, T> storeProductSalesThisYear() {
		try {
			List<Map> list = statisticService.storeProductSalesThisYear();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询今年专卖店产品销量成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询今年专卖店产品销量失败. " + e.getMessage());
		}
	}



}
