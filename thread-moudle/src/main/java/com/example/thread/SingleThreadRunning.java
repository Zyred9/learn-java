package com.example.thread;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 *      n个线程，只允许一个线程执行某个方法
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class SingleThreadRunning {

    private static boolean isRun = true;

    private static int size = 0;

    public synchronized static void print(int i) {
        if (isRun){
            size = i;
            isRun = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int max = 10;
        CountDownLatch begin = new CountDownLatch(max);

        for (int i = 0; i < max; i++) {
            int finalI = i;
            new Thread(() ->{
                try {
                    runs(finalI);
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    begin.countDown();
                }
            }).start();
        }

        begin.await();
    }


    public static void runs (int arg){
        print(arg);
        int getSize = size;
        System.out.println(getSize);
    }
}
