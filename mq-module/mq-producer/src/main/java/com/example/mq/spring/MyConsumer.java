package com.example.mq.spring;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 *      MQ消费者
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class MyConsumer {

    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";
    private final static String QUEUE_NAME = "SIMPLE_QUEUE";

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

        // 交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", false, false, null);

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("等待接收消息......");

        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "zyred.best");

        // 创建消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msg = new String(body, StandardCharsets.UTF_8);

                System.out.println("接收到的消息:" + msg);
                System.out.println("消费者标签：" + consumerTag);
                System.out.println("发起者标签：" + envelope.getDeliveryTag());
                System.out.println();
            }
        };

        // 开始获取消息
        channel.basicConsume(QUEUE_NAME, consumer);
    }


}
