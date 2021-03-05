package com.example.mq.boot.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>
 *          MQ核心配置文件，主要配置队列和交换机
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Configuration
@PropertySource("classpath:testmq.properties")
public class RabbitConfig {

    @Value("${com.test.direct_exchange}")
    private String directExchange;
    @Value("${com.test.topic_exchange}")
    private String topicExchange;
    @Value("${com.test.fanout_exchange}")
    private String fanoutExchange;


    @Value("${com.test.first_queue}")
    private String firstQueue;
    @Value("${com.test.second_queue}")
    private String secondQueue;
    @Value("${com.test.third_queue}")
    private String thirdQueue;
    @Value("${com.test.fourth_queue}")
    private String fourthQueue;

    // *************************创建四个队列*************************** //
    @Bean("testFirstQueue")
    public Queue firstQueue(){
        return new Queue(this.firstQueue);
    }
    @Bean("testSecondQueue")
    public Queue secondQueue(){
        return new Queue(this.secondQueue);
    }
    @Bean("testThirdQueue")
    public Queue thirdQueue(){
        return new Queue(this.thirdQueue);
    }
    @Bean("testFourthQueue")
    public Queue fourthQueue(){
        return new Queue(this.fourthQueue);
    }

    // *************************创建三个交换机*************************** //
    @Bean("testDirectExchange")
    public DirectExchange directExchange(){
        return new DirectExchange(this.directExchange);
    }
    @Bean("testTopicExchange")
    public TopicExchange topicExchange(){
        return new TopicExchange(this.topicExchange);
    }
    @Bean("testFanoutExchange")
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(this.fanoutExchange);
    }

    // *************************交换机绑定队列*************************** //
    /** FirstQueue ->  DirectExchange **/
    @Bean
    public Binding bindFirst(@Qualifier("testFirstQueue") Queue q,
                             @Qualifier("testDirectExchange") DirectExchange d){
        return BindingBuilder.bind(q).to(d).with("test.best");
    }
    /** SecondQueue -> TopicExchange **/
    @Bean
    public Binding bindSecond(@Qualifier("testSecondQueue") Queue q,
                             @Qualifier("testTopicExchange") TopicExchange d){
        return BindingBuilder.bind(q).to(d).with("*.test.*");
    }
    /** ThirdQueue -> FanoutExchange **/
    @Bean
    public Binding bindThird(@Qualifier("testThirdQueue") Queue q,
                             @Qualifier("testFanoutExchange") FanoutExchange d){
        return BindingBuilder.bind(q).to(d);
    }
    /** FourthQueue -> FanoutExchange **/
    @Bean
    public Binding bindFourth(@Qualifier("testFourthQueue") Queue q,
                              @Qualifier("testFanoutExchange") FanoutExchange d){
        return BindingBuilder.bind(q).to(d);
    }


    /**
     * 在消费端转换JSON消息
     * 监听类都要加上containerFactory属性
     * @param con     连接工厂对象
     * @return        监听器
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory con) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(con);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setAutoStartup(true);
        return factory;
    }
}
