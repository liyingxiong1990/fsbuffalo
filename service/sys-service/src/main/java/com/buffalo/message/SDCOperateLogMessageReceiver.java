package com.buffalo.message;


import com.buffalo.sys.service.OperateLogService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RabbitListener(queues="sdc_operate_log")
public class SDCOperateLogMessageReceiver {

    @Autowired
    private OperateLogService operateLogService;

    @RabbitHandler
    public void process(HashMap<String,String> message){

        try {
            operateLogService.addSDCLog(message.get("userId"),message.get("operateType"),message.get("operateContent"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}