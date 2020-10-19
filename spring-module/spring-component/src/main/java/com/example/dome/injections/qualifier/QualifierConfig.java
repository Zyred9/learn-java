package com.example.dome.injections.qualifier;

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
public class QualifierConfig {

    @Bean("dao")
    public MyDao dao(){
        MyDao dao = new MyDao();
        dao.setFlag("2");
        return dao;
    }
}
