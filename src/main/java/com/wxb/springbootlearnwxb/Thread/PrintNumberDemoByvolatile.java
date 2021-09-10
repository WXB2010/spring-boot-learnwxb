package com.wxb.learn.Thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangxianbing
 * @date 2021-09-09 16:03:09
 */
public class PrintNumberDemoByvolatile implements PrintAB {
    // 打印何时结束需要设置一个上限，打印到100结束；
    private static final int MAX_PRINT_NUM = 10;
    private static volatile int count = 0;

    public static void main(String[] args) {
        new PrintNumberDemoByvolatile().printAB();
    }

    @Override
    public void printAB() {
        new Thread(() -> {
            while (count <= MAX_PRINT_NUM) {
                // 打印偶数
                if (count % 2 == 0) {
                    System.out.println("num:" + count);
                    // 需要在打印之后才自增，否则数字会乱
                    count++;
                }
            }
        }).start();

        new Thread(() -> {
            while (count < MAX_PRINT_NUM) {
                // 打印奇数
                if (count % 2 == 1) {
                    System.out.println("thread:" + Thread.currentThread().getName() + "--num:" + count);
                    count++;
                }
            }
        }).start();
    }
}
