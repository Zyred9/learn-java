package com.example.learn.design.patterns.singleton.simple.create.threadlocal;

/**
 * <p>
 *      在同一个线程中，创建的对象是相同的，在不同的线程中创建的对象是不相同的
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 15:22
 * @company 中再云图技术有限公司
 **/
public class ThreadLocalSingleton {

    private ThreadLocalSingleton(){}

    private static ThreadLocal<ThreadLocalSingleton> threadLocal = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    public static ThreadLocalSingleton getInstance (){
        return threadLocal.get();
    }

}
