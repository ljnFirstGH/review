package com.at.boot.activemq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.util.UUID;

/**
 * @Author: lnj999
 * @Description:
 * @Time: created on 2020/10/12 7:10
 */
@Component
public class Queue_Produce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    // 调用一次一个信息发出
    public void produceMessage() {
        jmsMessagingTemplate.convertAndSend(queue, "****" + UUID.randomUUID().toString().substring(0, 6));
    }

    // 带定时投递的业务方法  需要启动主启动类才行MainApp_Produce
    @Scheduled(fixedDelay = 3000)    // 每3秒自动调用
    public void produceMessageScheduled(){
        jmsMessagingTemplate.convertAndSend(queue,"** scheduled **"+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("  produceMessage  send   ok   ");
    }
}
