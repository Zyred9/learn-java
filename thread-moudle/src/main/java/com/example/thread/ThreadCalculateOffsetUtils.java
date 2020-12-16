package com.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;


/**
 * <p>
 *      根据线程计算偏移量
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Slf4j
public class ThreadCalculateOffsetUtils {

    /** 存储线程单位级别的偏移量 **/
    public static ThreadLocal<Integer> offset = new ThreadLocal<>();

    /** 原子操作，使用 synchronized 关键字保证线程之间可见 **/
    private static final LongAdder temp = new LongAdder();

    /** 存储所有进入 cacl() 的线程，运行完毕一条删一条，知道运行完毕，清除 temp 的值**/
    private static final ArrayList<Thread> threads = new ArrayList<>();


    /**
     * 计算偏移量, 如果不同线程进入当前方法，会排队获取锁，
     * 拿到锁的进入方法计算偏移量，然后保存到线程副本中
     * @param totalSpace    总车位数
     */
    public static void calc(int totalSpace) {
        // 在方法未同步之前，将所有的线程全部放入集合中
        threads.add(Thread.currentThread());


        // 同步一个个执行，执行完毕一个后，删除该线程
        synchronized (ThreadCalculateOffsetUtils.class) {

            if (temp.intValue() > 0) {
                // TODO
            }
            offset.set(temp.intValue());
//            log.info(Thread.currentThread().getName() + " :" + offset.get());
            System.out.println(Thread.currentThread().getName() + " :" + offset.get());
            temp.add(1);
            threads.remove(Thread.currentThread());
        }

        // 如果线程总数为0的情况下，则说明当前线程执行完毕了，那么可以重置临时便令
        if (threads.size() == 0) {
            temp.reset();
        }
    }

    // 测试方法如下：
    public static void main(String[] args) throws Exception {
        int max = 10;
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1);
            new Thread(()->calc(max)).start();
        }

        TimeUnit.SECONDS.sleep(2);

        for (int i = 0; i < 10; i++) {
//            Thread.sleep(1);
            new Thread(()-> calc(max)).start();
        }
    }
}
