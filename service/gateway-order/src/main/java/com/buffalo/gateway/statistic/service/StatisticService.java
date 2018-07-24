
package com.buffalo.gateway.statistic.service;


import java.util.List;
import java.util.Map;

public interface StatisticService {

    public List<Map> todaySales() throws Exception;

    public List<Map> thisMonthSales() throws Exception;

    public List<Map> thisYearSales() throws Exception;

    public List<Map> storeSales(String startTime, String endTime) throws Exception;

    public List<Map> productSalesToday() throws Exception;
}
