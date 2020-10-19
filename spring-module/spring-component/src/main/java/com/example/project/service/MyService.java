package com.example.project.service;

import com.example.project.dao.MyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Tom.
 */
@Service
public class MyService {
    /**
     * @Qualifier("dao") 该注解指定注入某个接口多实现类中其中的一个实现类，通过名称注入
     */
    @Qualifier("dao")
    @Autowired
    private MyDao myDao;

    public void print(){
        System.out.println(myDao);
    }
}
