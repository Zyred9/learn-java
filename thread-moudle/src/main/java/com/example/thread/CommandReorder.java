package com.example.thread;

/**
 * <p>
 *      指令重排序
 *      分析：按照道理先创建出线程，那么线程肯定是比number
 *      先运行，然后main线程针对 ready和 number赋值，此时
 *      对于 Command 是不可见的，但是输出确是22，说明再此
 *      过程中发生了指令重排序的问题
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class CommandReorder {

    private static boolean ready;

    private static int number;

    public static void main(String[] args) throws InterruptedException {
        new Command().start();
        Thread.sleep(300);
        number = 22;
        ready = false;
    }

    static class Command extends Thread {
        @Override
        public void run() {
            while (!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }
}


