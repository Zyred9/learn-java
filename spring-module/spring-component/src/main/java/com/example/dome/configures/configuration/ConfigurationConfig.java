package com.example.dome.configures.configuration;

import com.example.project.entity.Person ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tom.
 */
@Configuration
public class ConfigurationConfig {

    /**
     * 默认情况下是根据方法的名称作为 Bean 的name
     * 如果在 @Bean 注解中声明了 name 属性，那么将被采用
     * @return
     */
    @Bean(name = "zyred")
    public Person person1(){
        return new Person("Tom",18);
    }
}
