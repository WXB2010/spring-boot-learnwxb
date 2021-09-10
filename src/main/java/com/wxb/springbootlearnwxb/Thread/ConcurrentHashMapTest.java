package com.wxb.learn.Thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author wangxianbing
 * @date 2021-09-08 12:24:30
 */

//@Slf4j
public class ConcurrentHashMapTest {

    private static final Integer ITEM_COUNT = 1000;
    private static final Integer THREAD_COUNT = 10;

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        System.out.println("init size " + concurrentHashMap.size());

        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        // 使用线程池并发处理逻辑
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(i -> {
                    // 查询还需要补充多少个元素
                    int gap = ITEM_COUNT - concurrentHashMap.size();
                    System.out.println("线程名字"+Thread.currentThread().getName()+" ,gap size====" + gap);
                    // 补充元素
                    concurrentHashMap.putAll(getData(gap));
                }));

        // 等待所有任务完成
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("finish size====" + concurrentHashMap.size());


    }

    private static ConcurrentHashMap<String, Long> getData(int i) {
        ConcurrentHashMap<String, Long> concurrentHashMap = new ConcurrentHashMap<>();
        for (long j = 0; j < i; j++) {
            concurrentHashMap.put("" + j, j);
        }
        return concurrentHashMap;
    }


}
