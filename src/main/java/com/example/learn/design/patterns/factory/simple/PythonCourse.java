package com.example.learn.design.patterns.factory.simple;

/**
 * Python课程
 *
 * @author zyred
 */
public class PythonCourse implements ICourse {

    @Override
    public void recordVideo() {
        System.out.println("Python课程录制视频");
    }

    @Override
    public void answer() {
        System.out.println("Python 课后答疑");
    }
}