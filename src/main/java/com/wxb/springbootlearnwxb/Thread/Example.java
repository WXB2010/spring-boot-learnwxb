package com.wxb.learn.Thread;

/**
 * @author wangxianbing
 * @date 2021-09-08 15:52:59
 */
class Counter {
    long count = 0;

    public synchronized void add(long value) {
        this.count += value;
    }
}

class CounterThread extends Thread {

    protected Counter counter = null;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.add(i);
        }
        System.out.println(counter.count);
    }
}

public class Example {

    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread threadA = new CounterThread(counter);
        Thread threadB = new CounterThread(counter);

        threadA.start();
        threadB.start();
    }
}

