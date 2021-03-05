package com.example.mq.spring;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * <p>
 *      MQ 消息生产者
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class MyProducer {

    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("182.92.175.117");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("123456");

        // 创建新的连接
        Connection connection = factory.newConnection();
        // 创建连接管道
        Channel channel = connection.createChannel();

        for (int i = 0; i < 10; i++) {
            String msg = "msg => " + (i << i);
            Thread.sleep(3000);
            channel.basicPublish(EXCHANGE_NAME, "zyred.best", null, msg.getBytes());
        }

        channel.close();
        connection.close();

    }

}
