package com.at.boot.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: lnj999
 * @Description:
 * @Time: created on 2020/10/12 7:09
 */
@SpringBootApplication
@EnableScheduling      // 是否开启
public class MainApp_Produce {
    public static void main(String[] args) {
        SpringApplication.run(MainApp_Produce.class,args);
    }
}
