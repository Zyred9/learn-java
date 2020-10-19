package com.example.learn.design.patterns.builder.simple;

import com.example.learn.design.patterns.builder.IBuilder;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/31 9:17
 **/
public class CourseBuilder implements IBuilder<Course> {

    private Course course = new Course();


    public CourseBuilder addName (String name){
        this.course.setName(name);
        return this;
    }

    public CourseBuilder addPpt (String ppt){
        this.course.setPpt(ppt);
        return this;
    }

    public CourseBuilder addHomework (String homework){
        this.course.setHomework(homework);
        return this;
    }

    public CourseBuilder addVideo (String video){
        this.course.setVideo(video);
        return this;
    }

    @Override
    public Course builder (){
        return this.course;
    }

}
