package com.example.thread.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *      Condition 主要是通过 await() 进行释放资源，然后通过 single/singleAll 方法执行通知其他线程
 *
 *      如下执行顺序：
 *          线程1获得锁
 *          线程1执行 await 释放锁
 *          线程2等待获取锁
 *          线程2获取到锁
 *          线程2执行完毕，执行 single 通知
 *          线程1重新拿到资源
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class ConditionDemo {

    static Lock lock = new ReentrantLock();
    static Condition con = lock.newCondition();

    public static void main(String[] args) {

        new Thread(
                () -> {
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "获得了锁");
                        Thread.sleep(3000);
                        // 释放锁资源
                        System.out.println(Thread.currentThread().getName() + "释放资源");
                        con.await();
                        System.out.println(Thread.currentThread().getName() + "重新获得锁");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }

                }, "线程1"
        ).start();

        new Thread(
                () -> {
                    lock.lock();
                    try {
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "获得了锁");
                        con.signal();
                        System.out.println(Thread.currentThread().getName() + "发送一个信号");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }

                }, "线程2"
        ).start();

    }

}
