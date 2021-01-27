package com.example.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *          可重入锁测试
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class ReentrantLockTest {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        try {
            lock.lock();

            // do something

        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
