package com.wxb.springbootlearnwxb.test;

import java.util.concurrent.CompletableFuture;

/**
 * @author wangxianbing
 * @date 2021-03-28 12:57
 */
public class Test {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException();
        })
                // 必须使用这种链路来调用，不然上一步方法结束就会抛异常出去
                .exceptionally(ex -> "errorResultA")
                .thenApply(resultA -> resultA + " resultB")
                .thenApply(resultB -> resultB + " resultC")
                .thenApply(resultC -> resultC + " resultD");

        System.out.println(future.join());
    }
}
