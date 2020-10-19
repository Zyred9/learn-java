package com.exapmle.spring;

import com.example.spring.framework.context.AnnotationApplicationContext;
import com.example.spring.framework.context.ApplicationContext;
import com.example.test.ControllerTest;
import org.junit.Test;

import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class TestSpring {

    @Test
    public void run () {
        ApplicationContext app = new AnnotationApplicationContext("classpath:application.properties");
//        ControllerTest bean = app.getBean(ControllerTest.class);
//        System.out.println(bean);
        Set<String> allBean = app.getAllBean();
        allBean.stream().forEach(System.out::println);
    }

}
