package com.example.dome.configures.configuration;

import com.example.project.entity.Person ;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * Created by Tom.
 */
public class ConfigurationTest {
    @Test
    public void test() {
        ApplicationContext app = new AnnotationConfigApplicationContext(ConfigurationConfig.class);
        Object bean = app.getBean("zyred");
        System.out.println(bean);

        String [] beanNames = app.getBeanNamesForType(Person.class);
        System.out.println(Arrays.toString(beanNames));

    }
}
