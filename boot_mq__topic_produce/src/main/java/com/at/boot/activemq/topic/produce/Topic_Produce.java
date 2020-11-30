package com.at.boot.activemq.topic.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

/**
 * @Author: lnj999
 * @Description:
 * @Time: created on 2020/10/13 21:03
 */
@Component
public class Topic_Produce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 3000)
    public  void  produceTopic(){
        jmsMessagingTemplate.convertAndSend(topic,"主题消息:"+ UUID.randomUUID().toString().substring(0,6));

    }

}
