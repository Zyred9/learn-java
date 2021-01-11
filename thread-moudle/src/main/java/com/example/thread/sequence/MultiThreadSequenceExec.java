package com.example.thread.sequence;

/**
 * <p>
 *          多条线程顺序执行
 *          A ->  B -> C -> D -> E
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class MultiThreadSequenceExec {

    public static void main(String[] args) {
        Thread[] threads = doCreateThread();
        doStartThread(threads);
    }


    public static Thread[] doCreateThread(){
        int size = 10;
        Thread[] threads = new Thread[size];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(
                    () -> System.out.println(Thread.currentThread().getName()), String.valueOf(i)
            );
        }
        return threads;
    }


    public static void doStartThread (Thread[] threads){
        try {
            for (Thread thread : threads) {
                thread.start();
                // 保证线程顺序执行
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
