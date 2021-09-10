package com.wxb.springbootlearnwxb.test;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * @author wangxianbing
 * @date 2021-03-27 15:05
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // 创建异步执行任务:
        //CompletableFuture<Double> cf = CompletableFuture.supplyAsync(Main::fetchPrice);
        CompletableFuture<Object> cf = CompletableFuture.supplyAsync(()->{
            throw new RuntimeException();
        })
        //cf.handle(new BiFunction<Double, Throwable, Object>() {
        //    @Override
        //    public Object apply(Double aDouble, Throwable throwable) {
        //        if(throwable!=null){
        //            //throwable.printStackTrace();
        //            System.out.println(throwable.getCause());
        //            System.out.println(throwable.getLocalizedMessage());
        //            System.out.println(throwable.getMessage());
        //            System.out.println(throwable.getStackTrace());
        //            System.out.println(throwable.fillInStackTrace());
        //            System.out.println(throwable.getSuppressed());
        //
        //            return "throwable";
        //        }
        //        return aDouble;
        //    }
        //});
        // 如果执行异常:
        .exceptionally((e) -> {
            //e.printStackTrace();
            return "ex";
        })
        // 如果执行成功:
        //cf.thenAccept((result) -> {
        //    System.out.println("price: " + result);
        //});
        .thenApply((result)->{
            System.out.println("---"+result);
           return result;
        }
        );

        System.out.println(cf.join());
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);

    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 10.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }
}
