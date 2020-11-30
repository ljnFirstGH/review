package com.at.boot.activemq.topic.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @Author: lnj999
 * @Description:
 * @Time: created on 2020/10/13 21:22
 */
@Component
public class Topic_Consumer {
    @JmsListener(destination = "${myTopic}")
    public void  receive(TextMessage textMessage) throws JMSException {
        System.out.println("消费者收到订阅的主题:"+textMessage.getText());
    }
}
