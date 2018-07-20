
package com.buffalo.statistic.controller;

import com.buffalo.statistic.service.StatisticService;
import com.buffalo.util.ResponseUtil;
import feign.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private StatisticService statisticService;


	/**
	 * 专卖店前10
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/storeTop10", method = RequestMethod.GET)
    public <T> Map<String, T> storeTop10(@RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime) {
		try {
			List<Map> list = statisticService.storeTop10(startTime,endTime);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "查询专卖店前10成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "查询专卖店前10失败. " + e.getMessage());
		}
    }



}
