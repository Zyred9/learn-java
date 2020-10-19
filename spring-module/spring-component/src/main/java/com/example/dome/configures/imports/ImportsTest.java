package com.example.dome.configures.imports;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * Created by Tom.
 */
public class ImportsTest {

    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(ImportsConfig.class);

        //通过FactoryBean注入的值
        System.out.println("============" + app.getBean("monkey").getClass());
        Object monkey1 = app.getBean("monkey");
        Object monkey2 = app.getBean("monkey");
        System.out.println("是否单例：" + monkey1 == monkey2);

        //拿到构建monkey的FactoryBean
        Object monkeyFactoryBean = app.getBean("&monkey");
        System.out.println(monkeyFactoryBean);

        String [] beanNames = app.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanNames)
                .replaceAll("\\[|\\]","")
                .replaceAll(", ","\n"));
    }

}
