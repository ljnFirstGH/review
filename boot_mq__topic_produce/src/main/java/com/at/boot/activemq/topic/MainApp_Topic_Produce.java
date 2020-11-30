package com.at.boot.activemq.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: lnj999
 * @Description:
 * @Time: created on 2020/10/13 20:52
 */
@SpringBootApplication
@EnableScheduling
public class MainApp_Topic_Produce {
    public static void main(String[] args) {
        SpringApplication.run(MainApp_Topic_Produce.class,args);
    }
}
