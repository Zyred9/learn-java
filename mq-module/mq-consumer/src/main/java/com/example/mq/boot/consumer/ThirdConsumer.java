package com.example.mq.boot.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>
 *      first_queue 队列监听器
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Configuration
@PropertySource("classpath:testmq.properties")
@RabbitListener(queues = {"${com.test.third_queue}"}, containerFactory = "rabbitListenerContainerFactory")
public class ThirdConsumer {

    @RabbitHandler
    public void processor(String msg){
        System.out.println("Third Queue : " + msg);
    }


}
