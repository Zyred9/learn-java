package com.example.dome.injections.qualifier;

import com.example.project.service.MyService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Tom.
 */
public class QualifierTest {
    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(QualifierConfig.class);


        MyService service = app.getBean(MyService.class);
        service.print();


    }
}
