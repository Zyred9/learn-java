package com.example.thread.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * <p>
 *
 * </p>
 *
 * @author Zyred
 * @project spring-security
 * @date 2021/1/11 21:15
 **/
public class BlockQueueDome {

    static ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) throws InterruptedException {

        new Thread(
                () ->{
                    try {
                        Thread.sleep(5000);
                        q.put("qwet");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        ).start();

        System.out.println(q.take());
    }


}
