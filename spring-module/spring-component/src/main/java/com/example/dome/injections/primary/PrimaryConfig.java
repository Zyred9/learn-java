package com.example.dome.injections.primary;

import com.example.project.dao.MyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by Tom.
 */
@Configuration
@ComponentScan({
        "com.example.project.controller",
        "com.example.project.service",
        "com.example.project.dao"
            })
public class PrimaryConfig {


    @Primary
    @Bean("myDao")
    public MyDao dao(){
        MyDao dao = new MyDao();
        dao.setFlag("9");
        return dao;
    }

    @Bean("myDao")
    public MyDao myDao(){
        MyDao dao = new MyDao();
        dao.setFlag("3");
        return dao;
    }
}
