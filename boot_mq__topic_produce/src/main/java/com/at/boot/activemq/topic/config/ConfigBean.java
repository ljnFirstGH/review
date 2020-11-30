package com.at.boot.activemq.topic.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

/**
 * @Author: lnj999
 * @Description:
 * @Time: created on 2020/10/13 20:58
 */
@Component
public class ConfigBean {
    @Value("${myTopic}")
    private  String topicName;

    @Bean
    public Topic topic(){
        return  new ActiveMQTopic(topicName);
    }
}
