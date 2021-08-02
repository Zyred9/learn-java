package com.example.thread.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class ReentrantLockDemo {

    /** 构造一个锁，类变量 **/
    static Lock lock = new ReentrantLock(true);

    static int a = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(ReentrantLockDemo::incr, "线程1").start();
        new Thread(ReentrantLockDemo::incr, "线程2").start();
        Thread t3 = new Thread(ReentrantLockDemo::incr, "线程3");
        t3.start();

        t3.join();
        System.out.println(a);
    }


    public static void incr () {

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 进入 >>>> ");
            a = a + 1;
        }finally {
            lock.unlock();
        }
    }

}
