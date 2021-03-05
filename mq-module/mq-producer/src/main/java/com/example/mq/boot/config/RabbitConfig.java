package com.example.mq.boot.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *      MQ 模板配置文件
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory conn){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(conn);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
