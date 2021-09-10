package com.wxb.learn.Thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangxianbing
 * @date 2021-09-09 16:03:09
 */
public class PrintNumberDemo implements PrintAB {
    // 打印何时结束需要设置一个上限，打印到100结束；
    private static final int MAX_PRINT_NUM = 10;
    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        new PrintNumberDemo().printAB();
    }

    @Override
    public void printAB() {
        new Thread(() -> {
            while (atomicInteger.get() <= MAX_PRINT_NUM) {
                // 打印偶数
                if (atomicInteger.get() % 2 == 0) {
                    System.out.println("num:" + atomicInteger.get());
                    // 需要在打印之后才自增，否则数字会乱
                    atomicInteger.incrementAndGet();
                }
            }
        }).start();

        new Thread(() -> {
            while (atomicInteger.get() < MAX_PRINT_NUM) {
                // 打印奇数
                if (atomicInteger.get() % 2 == 1) {
                    System.out.println("thread:"+Thread.currentThread().getName()+"--num:" + atomicInteger.get());
                    atomicInteger.incrementAndGet();
                }
            }
        }).start();
    }
}
