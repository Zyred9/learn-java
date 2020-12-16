package com.example.thread;

import java.util.ArrayList;
import java.util.concurrent.atomic.LongAdder;

/**
 * <p>
 *          线程计算偏移量
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class ThreadCalculateOffset {


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
    public static void calc (int totalSpace) {

        if (totalSpace <= 0) {
            return;
        }
        // 在方法未同步之前，将所有的线程全部放入集合中
        threads.add(Thread.currentThread());
        // 同步一个个执行，执行完毕一个后，删除该线程
        synchronized (ThreadCalculateOffset.class){

            if (temp.intValue() > 0) {
                if (temp.intValue() > totalSpace - 1) {
                    // 抛异常
                }
            }
            offset.set(temp.intValue());
            System.out.println(Thread.currentThread().getName() + " :" + offset.get());
            temp.add(1);
            threads.remove(Thread.currentThread());
        }

        // 如果线程总数为0的情况下，则说明当前线程执行完毕了，那么可以重置临时便令
        if (threads.size() == 0) {
            temp.reset();
        }
    }


}
