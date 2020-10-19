package com.example.dome.configures.component;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class ComponentTest {

    @Test
    public  void run() {
        ApplicationContext app = new AnnotationConfigApplicationContext(ComponentConfig.class);
        String[] beanNames = app.getBeanDefinitionNames();
        System.out.println(
                Arrays.toString(beanNames)
                .replaceAll("\\[|\\]", "")
                .replaceAll(", ", "\n")
        );
    }

}
