package com.wxb.springbootlearnwxb.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author wangxianbing
 * @date 2021-03-28 10:32
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<List<String>> supplyAsync = CompletableFuture.supplyAsync(() -> queryCode("中国石油"));
        CompletableFuture<List<String>> thenApplyAsync = supplyAsync.thenApplyAsync((code) -> {
            //return code;
            return queryName(code);
        });
        thenApplyAsync.thenAcceptAsync((result) -> System.out.println("result:" + result));
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    static List<String> queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return Arrays.asList("601857", "601858");
    }

    static List<String> queryName(List<String> code) {
        List<String> list = new ArrayList<>();
        code.stream().forEach((l) -> {
            if (l == "601857")
                list.add("中国");
            if (l == "601858")
                list.add("石油");
        });
        return list;
    }

    static List<Double> fetchPrice (List<String> code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return Arrays.asList(5 + Math.random() * 20);
    }
}
