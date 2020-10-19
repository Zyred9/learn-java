package com.example.dome.injections.autowired;

import com.example.project.dao.MyDao;
import com.example.project.service.MyService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Tom.
 */
public class MyTest {
    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(AutowiredConfig.class);
        Object controller = app.getBean("myController");
        System.out.println(controller);

        MyService service = app.getBean(MyService.class);
        System.out.println(service);

        service.print();

        MyDao dao = app.getBean(MyDao.class);
        System.out.println(dao);
    }
}
