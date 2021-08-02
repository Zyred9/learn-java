package com.example.thread.interrupt;

import com.example.thread.lock.ReentrantLockTest;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author Zyred
 * @project spring-security
 * @date 2021/1/20 22:18
 **/
public class InterruptDome02 implements Runnable {
    @Override
    public void run() {
        // 判断中断标志： 默认为false -> !false = true
        while (!Thread.currentThread().isInterrupted()){
            try {
                // 睡眠线程，抛出中断异常，用于处理中断
                // 在睡眠期间，如果当前线程被中断，那么将会抛出异常
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                // 如果外部中断该线程，那么这里会抛出异常，但是不会结束线程
                e.printStackTrace();
                // 如果在异常中，再次中断线程，那么线程会结束中断，运行结束
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("process end");
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptDome02());
        thread.start();
        TimeUnit.SECONDS.sleep(1);
//         thread线程在睡眠期间进行中断，此操作会抛出中断异常，但是当前线程并不会结束
        thread.interrupt();
//        Thread.interrupted();
    }

}
