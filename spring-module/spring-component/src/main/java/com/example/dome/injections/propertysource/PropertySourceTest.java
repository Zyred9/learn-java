package com.example.dome.injections.propertysource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * Created by Tom.
 */
public class PropertySourceTest {
    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(PropertySourceConfig.class);

        System.out.println(app.getBean("bird"));

        String [] beanNames = app.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanNames)
                .replaceAll("\\[|\\]","")
                .replaceAll(", ","\n"));

        Environment evn = app.getEnvironment();

        System.out.println("从环境变量中取值================" + evn.getProperty("bird.color"));
    }
}
