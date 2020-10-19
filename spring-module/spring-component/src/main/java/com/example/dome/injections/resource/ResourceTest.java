package com.example.dome.injections.resource;

import com.example.project.service.MyService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Tom.
 */
public class ResourceTest {
    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(ResourceConfig.class);


        MyService service = app.getBean(MyService.class);
        service.print();


    }
}
