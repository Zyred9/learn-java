package com.example.thread.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 *      当调用interrupt方法时，会把中断状态设置为true，
 *      然后park方法会去判断中断状态，如果为true，就直接
 *      返回，然后往下继续执行，并不会抛出异常。注意，这里
 *      并不会清除中断标志。
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ParkThread());
        t.start();
        Thread.sleep(100); //①
        // false
        System.out.println(Thread.currentThread().getName()+"开始唤醒阻塞线程:" + Thread.currentThread().isInterrupted());
        t.interrupt();
        //
        System.out.println(Thread.currentThread().getName()+"结束唤醒:" + Thread.currentThread().isInterrupted());

    }

    static class ParkThread implements Runnable{

        @Override
        public void run() {
            // false
            System.out.println(Thread.currentThread().getName()+"开始阻塞:" + Thread.currentThread().isInterrupted());
            LockSupport.park();
            // true
            System.out.println(Thread.currentThread().getName()+"第一次结束阻塞:" + Thread.currentThread().isInterrupted());
            LockSupport.park();
            // true
            System.out.println("第二次结束阻塞:" + Thread.currentThread().isInterrupted());
        }
    }

}
