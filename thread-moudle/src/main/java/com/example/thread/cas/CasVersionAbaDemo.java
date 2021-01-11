package com.example.thread.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *          原子操作，基于版本号控制 ABA 问题
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class CasVersionAbaDemo {

    static AtomicReference<Integer> a = new AtomicReference<>(0);

    public static synchronized void increment (){
        a.compareAndSet(a.get(), a.get() + 1);
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(CasVersionAbaDemo::increment).start();
        }
        Thread.sleep(4000);

        System.out.println(a.get());
    }

}
