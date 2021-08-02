package com.example.thread.juc;

import org.springframework.scheduling.concurrent.DefaultManagedAwareThreadFactory;

import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class CountDownLatchDemo {

    static CountDownLatch cdl = new CountDownLatch(550);

    static ThreadPoolExecutor ex = new ThreadPoolExecutor(5, 300, 0L, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(), new DefaultManagedAwareThreadFactory(), (r, executor) -> {

            }
    );

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <550 ; i++) {
            int j = i;
            ex.execute(
                    () -> {
                        try {
                            test(j);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            cdl.countDown();
                        }
                    }
            );
        }

        // 如果 cdl 中定义的计数大小小于线程总数，那么cdl一旦执行了被定义的次数后，就会调用 await
        cdl.await();
        ex.shutdown();
        System.out.println("<<<<<<<<<< finish >>>>>>>>>");
    }


    public static void test(int threadNum) throws InterruptedException {
        // 模拟请求的耗时操作
        Thread.sleep(1000);
        System.out.println("thread_num: " + threadNum);
        // 模拟请求的耗时操作
        Thread.sleep(1000);
    }

}
