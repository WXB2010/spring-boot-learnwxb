package com.wxb.learn.Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wangxianbing
 * @date 2021-09-02 11:12:09
 */
public class CountDownLatchTest {
    private static final CountDownLatch DOWN_LATCH = new CountDownLatch(4);

    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i<4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " 运行"+"---"+System.currentTimeMillis());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        DOWN_LATCH.countDown();
                    }
                }
            }).start();
        }

        System.out.println("等待子线程运行结束");
        DOWN_LATCH.await(10, TimeUnit.SECONDS);
        System.out.println("子线程运行结束");
    }
}
