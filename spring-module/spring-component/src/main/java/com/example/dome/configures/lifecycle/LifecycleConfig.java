package com.example.dome.configures.lifecycle;

import com.example.project.entity.Car;
import org.springframework.context.annotation.*;

@ComponentScans({
        @ComponentScan("com.example.project.entity"),
        @ComponentScan("com.example.dome.configures.lifecycle")
})
@Configuration
public class LifecycleConfig {

    @Lazy
    @Bean(initMethod = "addOil",destroyMethod = "close")
    public Car car(){
        return new Car();
    }

    //3种方式
    // 1.添加initMethod 和 destroyMethod
    // 2.实现InitializingBean和DisposableBean接口
    // 3.使用@PostConstruct和@PreDestroy注解
}
