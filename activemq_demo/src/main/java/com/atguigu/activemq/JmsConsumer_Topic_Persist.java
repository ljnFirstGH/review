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
public class JmsConsumer_Topic_Persist {
    public static final String ACTIVEMQ_URL = "tcp://192.168.10.130:61608";
    public static final String TOPIC_NAME = "topic-Persist";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("z3消费者**********");
        //创建链接工厂，按照给定的url地址，采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //通过链接工厂获得链接connection并启动连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //表明z3订阅
        connection.setClientID("z3");
        //创建会话session
        //两个参数，第一个叫事务，第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地，具体是队列还是主题topic
        Topic topic = (Topic) session.createTopic(TOPIC_NAME);
        //主题订阅者
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "remark....");
        //启动连接
        connection.start();
        //获取消息
        Message message = topicSubscriber.receive(4000L);
        while (null != message) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("*****收到的持久化topic:" + textMessage.getText());
            message = topicSubscriber.receive();
        }


        session.close();
        connection.close();















        /*
    //第一种方式
    //同步阻塞方式(receive())
    //订阅者或接受者调用MessageConsumer的receive方法来接收消息,receive方法能够在接收到消息之前(或超时之前)将一直阻塞
        while (true){
           TextMessage message  = (TextMessage) consumer.receive();
           if (message!=null){
               System.out.println("***消费者接收到消息***"+message.getText());
           }else {
               break;
           }
        }
        consumer.close();
        session.close();
        connection.close();*/


    }
}
