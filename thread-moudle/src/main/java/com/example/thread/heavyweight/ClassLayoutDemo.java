package com.example.thread.heavyweight;

import org.openjdk.jol.info.ClassLayout;

public class ClassLayoutDemo {

    public static void main(String[] args) throws Exception {
        ClassLayoutDemo testDemo = new ClassLayoutDemo();
        Thread t1 = new Thread(() -> {
            synchronized (testDemo) {
                System.out.println("t1 lock ing");
                System.out.println(ClassLayout.parseInstance(testDemo).toPrintable());
            }
        });
        t1.start();
        synchronized (testDemo) {
            System.out.println("main lock ing");
            System.out.println(ClassLayout.parseInstance(testDemo).toPrintable());
        }
    }
}