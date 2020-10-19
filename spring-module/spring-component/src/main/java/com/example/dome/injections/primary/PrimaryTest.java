package com.example.dome.injections.primary;

import com.example.project.service.MyService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Tom.
 */
public class PrimaryTest {
    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(PrimaryConfig.class);

        MyService service = app.getBean(MyService.class);
        service.print();


    }
}
