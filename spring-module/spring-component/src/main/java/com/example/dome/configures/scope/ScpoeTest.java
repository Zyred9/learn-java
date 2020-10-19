package com.example.dome.configures.scope;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Tom.
 */
public class ScpoeTest {
    @Test
    public void test() {
        ApplicationContext app = new AnnotationConfigApplicationContext(ScopeConfig.class);
        Object bean1 = app.getBean("person");
        System.out.println(bean1);

        Object bean2 = app.getBean("person");
        System.out.println(bean2);

        System.out.println(bean1 == bean2);

    }
}
