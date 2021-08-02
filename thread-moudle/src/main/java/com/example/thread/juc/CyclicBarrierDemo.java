package com.example.thread.juc;

import java.util.concurrent.*;

/**
 * <p>
 *      循环栅栏：parties 就代表了有拦截的线程的数量，当拦截的线程数量达到这个值的时候就打开栅栏，让所有线程通过
 *
 *      与 CountDownLatch 区别：
 *          CountDownLatch： 一个或者多个线程，等待其他多个线程完成某件事情之后才能执行
 *          CyclicBarrier: 多个线程互相等待，直到到达同一个同步点，再继续一起执行.
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class CyclicBarrierDemo {


    static CyclicBarrier cb = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            int j = i;
            service.execute(
                    () -> test(j)
            );
        }
        service.shutdown();
    }


    public static void test(int threadNum) {
        try {
            // 等待60秒，保证子线程完全执行结束
            cb.await(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("thread_num: " + threadNum + " is finish");
    }

}
