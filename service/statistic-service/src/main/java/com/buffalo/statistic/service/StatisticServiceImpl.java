
package com.buffalo.statistic.service;

import com.buffalo.statistic.model.Period;
import com.buffalo.statistic.mapper.StatisticMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService {
	
	@Autowired
	private StatisticMapper statisticMapper;


	@Override
	public List<Map> storeTop10(String startTime,String endTime) throws Exception {
		Period period = new Period();
		period.setStartTime(startTime);
		period.setEndTime(endTime);
		return statisticMapper.storeTop10(period);
	}
}
