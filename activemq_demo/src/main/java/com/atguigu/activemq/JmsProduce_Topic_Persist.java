package com.atguigu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: lnj999
 * @Description:
 * @Time: created on 2020/10/10 6:30
 */
public class JmsProduce_Topic_Persist {
    public static final String ACTIVEMQ_URL = "tcp://192.168.10.130:61608";
    public static final String TOPIC_NAME = "topic-Persist";

    public static void main(String[] args) throws JMSException {

        //创建链接工厂，按照给定的url地址，采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //通过链接工厂获得链接connection并启动连接
        Connection connection = activeMQConnectionFactory.createConnection();

        //创建会话session
        //两个参数，第一个叫事务，第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地，具体是队列还是主题topic
        Topic topic = (Topic) session.createTopic(TOPIC_NAME);
        //创建消息的生产者
        MessageProducer messageProducer = session.createProducer(topic);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

        connection.start();
        //通过使用messageProducer生产三条消息发送到MQ的队列里
        for (int i = 1; i <= 3; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage(TOPIC_NAME+"------" + i);
            //通过消息生产者发布 发送给mq
            messageProducer.send(textMessage);

        }
        //关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("****TOPIC_NAME消息发送到MQ完成");

        /**
         * 1.topic持久化,一定要先运行一次消费者,等于向MQ注册,类似我订阅了这个主题
         * 2.然后在运行生产者发送消息,此时
         * 3.无论消费这个是否在线,都会接收到,不在线的话,下次连接时,会把没有接收到的消息都接收下来.
         * */


    }
}
