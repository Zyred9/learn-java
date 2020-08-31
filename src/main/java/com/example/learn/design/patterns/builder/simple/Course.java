package com.example.learn.design.patterns.builder.simple;

/**
 * <p>
 *      课程
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/31 9:16
 **/
public class Course {

    private String name;

    private String ppt;

    private String video;

    private String homework;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPpt() {
        return ppt;
    }

    public void setPpt(String ppt) {
        this.ppt = ppt;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", ppt='" + ppt + '\'' +
                ", video='" + video + '\'' +
                ", homework='" + homework + '\'' +
                '}';
    }
}
