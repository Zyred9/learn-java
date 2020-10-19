package com.example.dome.configures.lazy;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Tom.
 */
public class LazyTest {
    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(LazyConfig.class);
        System.out.println("IoC容器创建完成");
        app.getBean("person");
    }
}
