package com.example.learn.design.patterns.singleton.simple.create.lazy;

/**
 * <p>
 *    懒汉式单例
 *    优点：节约内存，在需要创建对象的时候才会创建
 *    缺点：线程不安全
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 9:32
 **/
public class LazySingleton {

    private LazySingleton(){}

    private static LazySingleton singleton;

    public static LazySingleton getInstance(){
        if (singleton == null){
            singleton = new LazySingleton();
        }
        return singleton;
    }

}
