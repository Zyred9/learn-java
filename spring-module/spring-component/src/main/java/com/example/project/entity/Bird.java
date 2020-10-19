package com.example.project.entity;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Tom.
 */
public class Bird {

    //支持的类型
    //1、基本数据类型
    //3、Spring EL表达式
    //4、通过配置文件赋值
    @Value("鹦鹉")
    private String name;
    @Value("#{8-5}")
    private int age;

    @Value("${bird.color}")
    private String color;


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Bird() {
    }

    public Bird(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
