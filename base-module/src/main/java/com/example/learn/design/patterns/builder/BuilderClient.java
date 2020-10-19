package com.example.learn.design.patterns.builder;

import com.example.learn.design.patterns.builder.simple.CourseBuilder;

/**
 * <p>
 * 建造者模式： 创建型模式
 * 定义：创建复杂对象，条件却非常多的情况下，可以使用建造者模式创建对象。
 * <p>
 * 不满足原型模式
 * </p>
 *
 *
 * @author zyred
 * @createTime 2020/8/31 9:05
 **/
public class BuilderClient {


    public static void main(String[] args) {
        CourseBuilder builder = new CourseBuilder()
                .addName("Java 课程")
                .addPpt("Java P6.PPT")
                .addHomework("2020 期家庭作业")
                .addVideo("Java p6.mp4");

        System.out.println(builder.builder());
    }


}
