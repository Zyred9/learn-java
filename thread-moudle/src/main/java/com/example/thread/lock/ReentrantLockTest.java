package com.example.thread.lock;

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

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }

}
