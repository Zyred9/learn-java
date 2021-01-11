package com.example.thread.interrupt;

/**
 * <p>
 *      线程中断
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class InterruptDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    // 中断掉这个线程
                    Thread.currentThread().interrupt();
                    // 执行中断后，不杀死线程
                    System.out.println("线程是否存活 " + Thread.currentThread().isAlive());
                    // 查询线程中断状态， true
                    System.out.println("执行interrupt后中断标志 " + Thread.currentThread().isInterrupted());
                    // 清除中断状态，清楚成功  true
                    System.out.println("清除中断标志 " + Thread.interrupted());
                    // 查询线程中断状态，已经被上一句清除了 false
                    System.out.println("清除中断表示后 " + Thread.currentThread().isInterrupted());
                }
        );

        thread.start();

        System.out.println("outside " + thread.isInterrupted());
    }

}
