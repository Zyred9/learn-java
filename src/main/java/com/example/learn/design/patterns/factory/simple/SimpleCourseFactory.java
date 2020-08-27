package com.example.learn.design.patterns.factory.simple;

import java.util.Objects;

/**
 * <p>
 *      创建实例的简单工厂
 *
 *      简单工厂模式适用场景：
 *          适用于业务不变的场景
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/26 14:55
 **/
public class SimpleCourseFactory {

    public ICourse createCourse(Class<? extends ICourse> clazz){
        if (Objects.isNull(clazz)){
            throw new RuntimeException(this.getClass().getSimpleName() + ": parameter is empty !");
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
