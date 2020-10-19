package com.example.learn.design.patterns.prototype;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 16:23
 **/
public interface IPrototype<T> {

    /**
     * 对象克隆方法
     * @return
     */
    T clone();

}
