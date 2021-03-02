package com.example.rpc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Configuration
@ComponentScan(basePackages = {"com.example.rpc"})
public class RpcServerApplicationV2 {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(RpcServerApplicationV2.class);
    }

}
