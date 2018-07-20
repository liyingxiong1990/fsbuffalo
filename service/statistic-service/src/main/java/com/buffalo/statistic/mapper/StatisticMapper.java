
package com.buffalo.statistic.mapper;

import com.buffalo.statistic.model.Period;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface StatisticMapper {


    public List<Map> storeTop10(Period period) throws Exception;
}
