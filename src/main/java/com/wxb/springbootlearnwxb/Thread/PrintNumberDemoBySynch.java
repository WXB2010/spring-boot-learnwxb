package com.wxb.learn.Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * @author wangxianbing
 * @date 2021-09-09 16:03:09
 */
public class PrintNumberDemoBySynch {

    private static int number = 1;
    private static final Object object = new Object();
    final static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        // 奇数线程
        Thread threadOdd = new Thread(() -> {
            synchronized (object) {
                latch.countDown();
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " : " + number++);
                    try {
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        });

        // 偶数线程
        Thread threadEven = new Thread(() -> {
            try {
                latch.await();
                synchronized (object) {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(Thread.currentThread().getName() + " : " + number++);
                        object.notify();
                        object.wait();
                    }
                    object.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadOdd.start();
        threadEven.start();
    }
}
