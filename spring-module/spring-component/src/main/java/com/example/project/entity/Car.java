package com.example.project.entity;

/**
 * Created by Tom.
 */
public class Car {
    public Car() {
        System.out.println("调用Car的构造方法");
    }

    public void addOil(){
        System.out.println("行驶前加油");
    }

    public void run(){
        System.out.println("正在开车");
    }

    public void close(){
        System.out.println("停车熄火");
    }
}
