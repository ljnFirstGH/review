package com.atguigu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.jms.Message;
import java.io.IOException;

/**
 * @Author: lnj999
 * @Description:
 * @Time: created on 2020/10/9 21:00
 */
public class JmsConsumer {
    //    public static final String ACTIVEMQ_URL = "tcp://192.168.10.130:61616";
//    public static final String ACTIVEMQ_URL="nio://192.168.10.130:61618";
    public static final String ACTIVEMQ_URL = "tcp://192.168.10.130:61608";
    //public static final String ACTIVEMQ_URL="tcp://localhost:61616";
    public static final String QUEUE_NAME = "jdbc01";

    public static void main(String[] args) throws JMSException, IOException {
        //创建链接工厂，按照给定的url地址，采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //通过链接工厂获得链接connection并启动连接
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //创建会话session
        //两个参数，第一个叫事务，第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地，具体是队列还是主题topic
        Queue queue = session.createQueue(QUEUE_NAME);
        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
    //第一种方式
    //同步阻塞方式(receive())
    //订阅者或接受者调用MessageConsumer的receive方法来接收消息,receive方法能够在接收到消息之前(或超时之前)将一直阻塞
        while (true){
           TextMessage message  = (TextMessage) consumer.receive(4000L);
           if (message!=null){
               System.out.println("***消费者接收到消息***"+message.getText());
           }else {
               break;
           }
        }
        consumer.close();
        session.close();
        connection.close();

        //第二种方式
        //通过监听的方式来消费消息
//        consumer.setMessageListener(new MessageListener() {
//            public void onMessage(Message message) {
//
//                try {
//                    if (null != message && message instanceof TextMessage) {
//                        TextMessage message1 = (TextMessage) message;
//                        System.out.println("**通过监听接收到消息****" + message1.getText());
////                        System.out.println("**通过监听接收到消息属性****" + message1.getStringProperty("c1"));
//                    }
////                    if (null != message && message instanceof MapMessage) {
////                        MapMessage mapMessage = (MapMessage) message;
////                        System.out.println("**通过监听接收到mapMessage消息****" + mapMessage.getString("k1"));
////                    }
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        System.in.read();
//        consumer.close();
//        session.close();
//        connection.close();


    }
}
