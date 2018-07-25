
package com.buffalo.gateway.statistic.service;


import java.util.List;
import java.util.Map;

public interface StatisticService {

    public List<Map> todaySales() throws Exception;

    public List<Map> thisMonthSales() throws Exception;

    public List<Map> thisYearSales() throws Exception;

    public List<Map> todayCheckin()  throws Exception;

    public List<Map> storeSales(String startTime, String endTime) throws Exception;

    public List<Map> productSalesThisMonth() throws Exception;

    public List<Map> salesEveryMonth() throws Exception;

    public List<Map> salesEveryDay() throws Exception;
}
