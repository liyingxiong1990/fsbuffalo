
package com.buffalo.gateway.statistic.mapper;

import com.buffalo.gateway.statistic.model.Period;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface StatisticMapper {

    public List<Map> todaySales() throws Exception;

    public List<Map> thisMonthSales() throws Exception;

    public List<Map> thisYearSales() throws Exception;

    public List<Map> todayCheckin() throws Exception;

    public List<Map> storeSales(Period period) throws Exception;

    public List<Map> productSalesThisMonth() throws Exception;

    public List<Map> salesEveryMonth() throws Exception;

    public List<Map> salesEveryDay() throws Exception;

    public List<Map> deliverySalesThisMonth() throws Exception;

    public List<Map> deliveryProductsThisMonth() throws Exception;

    public List<Map> deliverySalesThisYear() throws Exception;

    public List<Map> deliveryProductsThisYear() throws Exception;

}
