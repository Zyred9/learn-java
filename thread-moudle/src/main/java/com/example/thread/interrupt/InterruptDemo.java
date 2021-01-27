package com.example.thread.interrupt;

import com.example.thread.lock.ReentrantLockTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 线程中断
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class InterruptDemo implements Runnable {

    /**
     * t1.isInterrupted() : 判断线程是否被发送过中断请求, 因为它将线程中断标示位设置为true后，不会立刻清除中断标示位，即不会将中断标设置为false
     * t1.interrupted(): 该方法调用后会将中断标示位清除，即重新设置为false
     * t1.interrupt(): 将线程的中断标志位设置为 true
     * Thread.currentThread().sleep(n) : 抛出中断异常,并重置线程中断标志位为false
     */


    static Lock lock = new ReentrantLock(false);


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new InterruptDemo(), "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
        System.out.println("中断状态0：" + t1.isInterrupted());
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("中断状态1：" + Thread.currentThread().isInterrupted());
            TimeUnit.SECONDS.sleep(5);
            System.out.println("睡眠后执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("中断状态2：" + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println("中断状态3：" + Thread.currentThread().isInterrupted());
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " 执行完毕！");
    }

}
