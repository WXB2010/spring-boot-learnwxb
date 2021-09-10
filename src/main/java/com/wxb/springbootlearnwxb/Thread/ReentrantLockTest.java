package com.wxb.learn.Thread;

/**
 * @author wangxianbing
 * @date 2021-09-09 17:36:49
 */
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private ReentrantLock lock = new ReentrantLock();

    public void testReentrantLock() {
        // 线程获得锁
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get lock");
            long beginTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - beginTime < 100) {
            }
            //线程再次获得该锁（可重入）
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lock again");
                long beginTime2 = System.currentTimeMillis();
                while (System.currentTimeMillis() - beginTime2 < 100) {
                }
            } finally {
                // 线程第一次释放锁
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " release lock");
            }
        } finally {
            // 线程再次释放锁
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " release lock again");
        }
    }

    public static void main(String[] args) {
        final ReentrantLockTest test = new ReentrantLockTest();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                test.testReentrantLock();
            }
        }, "A");
        thread.start();
    }

}
