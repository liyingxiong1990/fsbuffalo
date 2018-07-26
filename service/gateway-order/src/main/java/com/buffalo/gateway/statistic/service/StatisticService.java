
package com.buffalo.gateway.statistic.service;


import java.util.List;
import java.util.Map;

public interface StatisticService {

    public List<Map> todaySales() throws Exception;

    public List<Map> thisMonthSales() throws Exception;

    public List<Map> thisYearSales() throws Exception;

    public List<Map> todayCheckin()  throws Exception;

    public List<Map> productSalesThisMonth() throws Exception;

    public List<Map> productSalesThisYear() throws Exception;

    public List<Map> salesEveryMonth() throws Exception;

    public List<Map> salesEveryDay() throws Exception;

    public List<Map> deliverySalesThisMonth() throws Exception;

    public List<Map> deliveryProductsThisMonth() throws Exception;

    public List<Map> deliverySalesThisYear() throws Exception;

    public List<Map> deliveryProductsThisYear() throws Exception;

    public List<Map> storeSalesThisMonth() throws Exception;

    public List<Map> storeProductSalesThisMonth() throws Exception;

    public List<Map> storeSalesThisYear() throws Exception;

    public List<Map> storeProductSalesThisYear() throws Exception;

    public List<Map> storeLineSalesThisYear() throws Exception;
}
