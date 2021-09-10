package com.wxb.learn.Thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wangxianbing
 * @date 2021-09-09 16:03:09
 */
public class PrintNumberDemoByLockSupport {

    private static int number = 1;
    private static Thread threadOdd = null;
    private static Thread threadEven = null;

    public static void main(String[] args) {
        // 奇数线程
        threadOdd = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + number++);
                // 唤醒threadEven线程
                LockSupport.unpark(threadEven);
                // 阻塞当前线程
                LockSupport.park();
            }
        });

        // 偶数线程
        threadEven = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                // 阻塞threadEven，使threadOdd先运行
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + " : " + number++);
                // 唤醒threadOdd线程
                LockSupport.unpark(threadOdd);
            }
        });

        threadOdd.start();
        threadEven.start();
    }
}
