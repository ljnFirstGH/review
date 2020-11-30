package com.atguigu.activemq;



import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author LJN
 * @Date 2020/10/7 10:50
 */
public class JmsProduce_TX {
    public static final String ACTIVEMQ_URL="tcp://192.168.10.130:61616";
    public static final String  QUEUE_NAME="queue01";
    public static void main(String[] args) throws JMSException {

        //创建链接工厂，按照给定的url地址，采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //通过链接工厂获得链接connection并启动连接
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //创建会话session
        //两个参数，第一个叫事务，第二个叫签收
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建目的地，具体是队列还是主题topic
        Queue queue = session.createQueue(QUEUE_NAME);
        //创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        //队列 消息默认是持久化的persistent
        //messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

        //通过使用messageProducer生产三条消息发送到MQ的队列里
        for (int i = 1; i <=3; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage("Msg------" + i);
            //给消息设置属性
//            textMessage.setStringProperty("c1","vip");
            //通过消息生产者发布 发送给mq
            messageProducer.send(textMessage);

//            MapMessage mapMessage = session.createMapMessage();
//            mapMessage.setString("k1","mapMessage---v1");
//            messageProducer.send(mapMessage);

        }
        //关闭资源
        messageProducer.close();
        //session.commit();
        session.close();
        connection.close();

        System.out.println("****消息发送到MQ完成");


    }
}
