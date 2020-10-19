package com.example.learn.design.patterns.factory.simple;

/**
 * Java课程
 * @author zyred
 */
public class JavaCourse implements ICourse{

    @Override
    public void recordVideo() {
        System.out.println("Java课程录制视频");
    }

    @Override
    public void answer() {
        System.out.println("Java 课后答疑");
    }
}