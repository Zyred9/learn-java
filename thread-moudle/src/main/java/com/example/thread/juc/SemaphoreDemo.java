package com.example.thread.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <p>
 *      信号量
 *
 *      假设有10个人在银行办理业务，只有2个工作窗口
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class SemaphoreDemo {

    // 排队总人数（请求总数）
    public static int clientTotal = 10;

    // 可同时受理业务的窗口数量（同时并发执行的线程数）
    public static int windowTotal = 2;


    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 一次只允许两个线程同时执行
        final Semaphore semaphore = new Semaphore(windowTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {

            final int count = i;
            executorService.execute(() -> {

                try {
                    semaphore.acquire(1);
                    resolve(count);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

                // 执行一次，倒计一次
                countDownLatch.countDown();
            });
        }
        // 等待子线程执行完毕
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void resolve(int i) throws InterruptedException {
        System.out.println("服务号: " +  i + " 受理业务中");
        Thread.sleep(2000);
    }
}
