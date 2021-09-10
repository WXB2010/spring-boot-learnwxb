package com.wxb.springbootlearnwxb.test;

import java.util.concurrent.CompletableFuture;

/**
 * @author wangxianbing
 * @date 2021-03-28 14:28
 */
public class Combine {
    public static void main(String[] args) {
        CompletableFuture<String> cfA = CompletableFuture.supplyAsync(() -> "resultA");
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> "resultB");

        CompletableFuture<Void> completableFuture = cfA.thenAcceptBoth(cfB, (resultA, resultB) -> {
        });
        CompletableFuture<String> stringCompletableFuture = cfA.thenCombine(cfB, (resultA, resultB) -> "result A + B");
        cfA.runAfterBoth(cfB, () -> {});

        completableFuture.thenAccept(result-> System.out.println(result));
        stringCompletableFuture.thenAccept(result-> System.out.println(result));
    }
}
