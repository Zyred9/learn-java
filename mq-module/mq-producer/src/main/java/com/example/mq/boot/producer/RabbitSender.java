package com.example.mq.boot.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.mq.boot.entity.Merchant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: qingshan
 * @Description: 咕泡学院，只为更好的你
 */
@Component
@PropertySource("classpath:testmq.properties")
public class RabbitSender {

    @Value("${com.test.direct_exchange}")
    private String directExchange;

    @Value("${com.test.topic_exchange}")
    private String topicExchange;

    @Value("${com.test.fanout_exchange}")
    private String fanoutExchange;

    @Value("${com.test.direct_routing_key}")
    private String directRoutingKey;

    @Value("${com.test.topic_routing_key1}")
    private String topicRoutingKey1;

    @Value("${com.test.topic_routing_key2}")
    private String topicRoutingKey2;


    // 自定义的模板，所有的消息都会转换成JSON发送
    @Autowired
    AmqpTemplate rabbitTemplate;

    public void send() throws JsonProcessingException {
        Merchant merchant =  new Merchant(1001,"a direct msg : 中原镖局","汉中省解放路266号");
        rabbitTemplate.convertAndSend(directExchange,directRoutingKey, merchant);

        rabbitTemplate.convertAndSend(topicExchange,topicRoutingKey1, "a topic msg : hh.test.teacher");
        rabbitTemplate.convertAndSend(topicExchange,topicRoutingKey2, "a topic msg : ll.test.student");

        // 发送JSON字符串
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(merchant);
        System.out.println(json);
        rabbitTemplate.convertAndSend(fanoutExchange,"", json);
    }


}
