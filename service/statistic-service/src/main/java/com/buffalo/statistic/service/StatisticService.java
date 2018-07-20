
package com.buffalo.statistic.service;


import java.util.List;
import java.util.Map;

public interface StatisticService {

    public List<Map> storeTop10(String startTime,String endTime) throws Exception;


}
