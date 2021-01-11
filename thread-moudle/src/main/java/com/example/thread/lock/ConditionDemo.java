package com.example.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class ConditionDemo {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        new Thread(
                () -> {

                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "获取锁");
                        System.out.println(Thread.currentThread().getName() + "等待信号");
                        condition.await();
                        System.out.println(Thread.currentThread().getName() + "拿到信号");
                        condition.signalAll();
                        System.out.println(Thread.currentThread().getName() + "发出信号");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }

                }, "thread A "
        ).start();

        new Thread(
                () -> {
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "获取锁");
                        condition.signal();
                        System.out.println(Thread.currentThread().getName() + "发出信号");
                        condition.await();
                        System.out.println(Thread.currentThread().getName() + "收到信号，结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }, "Thread B "
        ).start();

        new Thread(
                () -> {
                    lock.lock();
                    try {
                        condition.await();
                        System.out.println(Thread.currentThread().getName() + "获取锁");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }, "Thread C "
        ).start();
    }

}
