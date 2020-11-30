package com.atguigu.activemq.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @Author: lnj999
 * @Description:
 * @Time: created on 2020/10/11 11:09
 */
@Service
public class SpringMQ_Producer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Producer producer = (SpringMQ_Producer) applicationContext.getBean("springMQ_Producer");
        producer.jmsTemplate.send((session)->{
            TextMessage textMessage = session.createTextMessage("***spring和activeMQ的整case1111.....");
            return textMessage;
        });

        System.out.println("****send task over*******");
    }
}
