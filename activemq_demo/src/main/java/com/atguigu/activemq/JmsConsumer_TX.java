package com.atguigu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Message;
import javax.jms.*;
import java.io.IOException;

/**
 * @Author: lnj999
 * @Description:
 * @Time: created on 2020/10/9 21:00
 */
public class JmsConsumer_TX {
    public static final String ACTIVEMQ_URL = "tcp://192.168.10.130:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException, IOException {
        //创建链接工厂，按照给定的url地址，采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //通过链接工厂获得链接connection并启动连接
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //创建会话session
        //两个参数，第一个叫事务，第二个叫签收
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        //创建目的地，具体是队列还是主题topic
        Queue queue = session.createQueue(QUEUE_NAME);
        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);

        //第一种方式
        //同步阻塞方式(receive())
        //订阅者或接受者调用MessageConsumer的receive方法来接收消息,receive方法能够在接收到消息之前(或超时之前)将一直阻塞
        while (true) {
            TextMessage message = (TextMessage) consumer.receive(4000L);
            if (message != null) {
                System.out.println("***消费者接收到消息***" + message.getText());
                //告知已收到
                message.acknowledge();
            } else {
                break;
            }
        }
        consumer.close();
        //session.commit();
        session.close();
        connection.close();

    }
}
