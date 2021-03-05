package com.example.mq.boot.consumer;

import com.example.mq.boot.entity.Merchant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.Payload;

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
@RabbitListener(queues = {"${com.test.first_queue}"}, containerFactory = "rabbitListenerContainerFactory")
public class FirstConsumer {

    @RabbitHandler
    public void processor(@Payload Merchant merchant){
        System.out.println("First Queue : " + merchant);
    }


}
