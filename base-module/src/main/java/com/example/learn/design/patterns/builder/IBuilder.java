package com.example.learn.design.patterns.builder;

/**
 * <p>
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/31 9:30
 **/
public interface IBuilder<T> {

    /**
     * 创建方法
     * @return
     */
    T builder();

}
