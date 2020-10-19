package com.example.dome.configures.lazy;

import com.example.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class LazyConfig {

    //懒加载只针对单例Bean起作用
    //默认容器启动时不创建对象，调用对象的功能时才创建
    @Lazy
    @Bean(initMethod = "lazyInitMethod", destroyMethod = "")
    public Person person(){
        System.out.println("将对象添加到IoC容器中");
        return new Person("Tom",18);
    }


}
