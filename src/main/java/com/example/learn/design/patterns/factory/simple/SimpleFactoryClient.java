package com.example.learn.design.patterns.factory.simple;

/**
 * <p>
 *      简单工厂实例客户端
 *
 *      只关心对象创建的结果，并不关心创建的过程，所以，这里只需要调用工厂中的创建对象方法即可
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/26 15:29
 **/
public class SimpleFactoryClient {


    public static void main(String[] args) {
        // ICourse course = new SimpleCourseFactory().createCourse(PythonCourse.class);
        ICourse course = new SimpleCourseFactory().createCourse(JavaCourse.class);
        course.answer();
        course.recordVideo();
    }

}
