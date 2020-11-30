package com.atguigu.activemq.Embed;

import org.apache.activemq.broker.BrokerService;

/**
 * @Author: lnj999
 * @Description:
 * @Time: created on 2020/10/11 10:30
 */
public class EmbedBroker {
    public static void main(String[] args) throws Exception {
        //activeMQ也支持在vm中通信,基于嵌入式的broker
        //相当于一个ActiveMQ服务器实例
        BrokerService brokerService= new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }

}
