package com.buffalo.message;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class OperateLogMessageSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String userId,String operateType,String operateContent){
        HashMap<String,String> messageMap = new HashMap<String,String>();
        messageMap.put("userId",userId);
        messageMap.put("operateType",operateType);
        messageMap.put("operateContent",operateContent);
        this.rabbitTemplate.convertAndSend("boss_operate_log",messageMap);

    }
}