package com.example.dome.configures.condition;

import com.example.project.entity.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Tom.
 */
public class ConditionTest {
    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(ConditionConfig.class);
        System.out.println("IoC容器创建完成");

        //假设：
        //如果操作系统是Windows，那么就将James实例化到容器中
        //如果操作系统是Linux，那么就将Tom实例化到容器中
    }
}
