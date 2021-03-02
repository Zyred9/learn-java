package com.example.dome.configures.scope;

import com.example.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ScopeConfig {

    //prototype 多例
    //singleton 单例
    //com.exampl.rpc.v1.request 主要应用于web模块，同一次请求只创建一个实例
    //session 主要应用于web模块，同一个session只创建一个实例
    @Scope("prototype")
    @Bean
    public Person person(){
        //IoC实例化对象的时候，并不是简单地调用我们定义的方法
        return new Person("Tom",18);
    }
}
