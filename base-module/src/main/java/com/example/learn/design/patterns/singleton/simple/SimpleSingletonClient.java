package com.example.learn.design.patterns.singleton.simple;

import com.example.learn.design.patterns.singleton.simple.create.threadlocal.ThreadLocalSingleton;

/**
 * <p>
 * 单例模式客户端
 * <p>
 * 1、饿汉式单例
 * 2、懒汉式单例
 * 3、注册式单例
 * 4、ThreadLocal单例
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/26 16:47
 **/
public class SimpleSingletonClient {

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        new Thread(pool).start();
        new Thread(pool).start();
    }


    static class ThreadPool implements Runnable {

        @Override
        public void run() {
            ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();
            System.out.println(ThreadLocalSingleton.getInstance());
            System.out.println(ThreadLocalSingleton.getInstance());
            System.out.println(ThreadLocalSingleton.getInstance());
            System.out.println(Thread.currentThread().getName() + "-" + instance);
        }
    }



}
