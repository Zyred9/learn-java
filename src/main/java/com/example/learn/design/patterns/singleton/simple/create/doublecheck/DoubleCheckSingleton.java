package com.example.learn.design.patterns.singleton.simple.create.doublecheck;

import java.io.Serializable;

/**
 * <p>
 *  双重检查锁单例：
 *  优点：节约内存
 *  缺点：性能低,不够优雅
 *
 *
 * volatile: 涉及到质量重排序的问题，如果两个线程进入创建，当一个线程创建好对象后，创建好的对象是被另一个线程不可见的，
 * 如果另一个线程看不见第一个线程创建的对象，那么在 第二个 if 条件后，结果依然为true，所以为了保证只有一个线程创建对象
 * 让所有线程可见，添加 volatile关键字
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 9:34
 **/
public class DoubleCheckSingleton implements Serializable {

   private DoubleCheckSingleton(){
       // 防止反射破坏单例
        if (singleton != null){
            throw new IllegalStateException("can not invoke private constructor instance object.");
        }
   }

   private volatile static DoubleCheckSingleton singleton;

   public static DoubleCheckSingleton getInstance(){

       if (singleton == null){
           synchronized (DoubleCheckSingleton.class){
               if (singleton == null){
                   singleton = new DoubleCheckSingleton();
               }
           }
       }
       return singleton;
   }


    /**
     * 防止序列化和反序列化破坏单例
     * @return
     */
   public Object readResolve(){
        return singleton;
   }
}
