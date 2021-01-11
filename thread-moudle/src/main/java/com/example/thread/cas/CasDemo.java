package com.example.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *          cas 小例子
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class CasDemo {

    static AtomicInteger a = new AtomicInteger();


    public static synchronized void increment (){
        a.incrementAndGet();
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(CasDemo::increment).start();
        }
        Thread.sleep(4000);

        System.out.println(a);
    }

}
