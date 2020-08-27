package com.example.learn.design.patterns.singleton.simple.create.hungry;

/**
 * <p>
 *   饿汉式单例:
 *   优点：高性能
 *   缺点：浪费内存
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 9:30
 **/
public class HungrySingleton {

    private static HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return singleton;
    }

}
