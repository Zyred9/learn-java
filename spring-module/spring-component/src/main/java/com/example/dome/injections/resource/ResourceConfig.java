package com.example.dome.injections.resource;

import com.example.project.dao.MyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tom.
 */
@Configuration
@ComponentScan({
        "com.example.project.controller",
        "com.example.project.service",
        "com.example.project.dao"
            })
public class ResourceConfig {

    @Bean("dao")
    public MyDao dao(){
        MyDao dao = new MyDao();
        dao.setFlag("4");
        return dao;
    }
}
