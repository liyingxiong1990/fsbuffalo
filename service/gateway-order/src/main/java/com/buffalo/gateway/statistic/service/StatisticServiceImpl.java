
package com.buffalo.gateway.statistic.service;

import com.buffalo.gateway.statistic.mapper.StatisticMapper;
import com.buffalo.gateway.statistic.model.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService {
	
	@Autowired
	private StatisticMapper statisticMapper;


	@Override
	public List<Map> todaySales() throws Exception {
		return statisticMapper.todaySales();
	}

	@Override
	public List<Map> thisMonthSales() throws Exception {
		return statisticMapper.thisMonthSales();
	}

	@Override
	public List<Map> thisYearSales() throws Exception {
		return statisticMapper.thisYearSales();
	}

	@Override
	public List<Map> todayCheckin() throws Exception {
		return statisticMapper.todayCheckin();
	}

	@Override
	public List<Map> storeSales(String startTime,String endTime) throws Exception {
		Period period = new Period();
		period.setStartTime(startTime);
		period.setEndTime(endTime);
		return statisticMapper.storeSales(period);
	}

	@Override
	public List<Map> productSalesThisMonth() throws Exception {
		return statisticMapper.productSalesThisMonth();
	}

	@Override
	public List<Map> salesEveryMonth() throws Exception {
		return statisticMapper.salesEveryMonth();
	}

	@Override
	public List<Map> salesEveryDay() throws Exception {
		return statisticMapper.salesEveryDay();
	}

	@Override
	public List<Map> deliverySalesThisMonth() throws Exception {
		return statisticMapper.deliverySalesThisMonth();
	}

	@Override
	public List<Map> deliveryProductsThisMonth() throws Exception {
		return statisticMapper.deliveryProductsThisMonth();
	}

	@Override
	public List<Map> deliverySalesThisYear() throws Exception {
		return statisticMapper.deliverySalesThisYear();
	}

	@Override
	public List<Map> deliveryProductsThisYear() throws Exception {
		return statisticMapper.deliveryProductsThisYear();
	}
}
