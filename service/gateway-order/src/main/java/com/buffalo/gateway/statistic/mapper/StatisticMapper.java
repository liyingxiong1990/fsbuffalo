
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

    public List<Map> storeSales(Period period) throws Exception;

    public List<Map> productSalesToday() throws Exception;
}
