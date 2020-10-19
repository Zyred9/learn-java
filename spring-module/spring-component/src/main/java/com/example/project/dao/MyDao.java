package com.example.project.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

/**
 * Created by Tom.
 *
 * 如果一个Bean使用类注解加载对象到IOC容器的时候，存在类配置使用@Bean
 * 和 new来创建对象并且注入到IOC容器中，会默认采用 @Bean 初始化的对象最为IOC容器对象
 *
 *
 */
@Repository("dao")
public class MyDao implements InitializingBean {
    private String flag = "1";

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "MyDao{" +
                "flag='" + flag + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("bean 初始化: " + flag);
    }
}
