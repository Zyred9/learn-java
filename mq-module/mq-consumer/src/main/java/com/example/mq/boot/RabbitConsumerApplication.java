package com.example.mq.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@SpringBootApplication
public class RabbitConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitConsumerApplication.class, args);
    }

}
