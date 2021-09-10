package com.wxb.springbootlearnwxb.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wangxianbing
 * @date 2021-03-28 11:17
 */
public class CompletableFutureParallel {
    public static void main(String[] args) throws InterruptedException {
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina  = CompletableFuture.supplyAsync(() -> {
            //try {
            //    TimeUnit.SECONDS.sleep(1);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            return queryCode("中国石油sina", "https://finance.sina.com.cn/code/");
        });
        CompletableFuture<String> cfQueryFrom163   = CompletableFuture.supplyAsync(() -> {

            return queryCode("中国石油163", "https://money.163.com/code/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery  = CompletableFuture.anyOf(cfQueryFrom163, cfQueryFromSina);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina  = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://finance.sina.com.cn/price/");
        });
        CompletableFuture<Double> cfFetchFrom163  = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://money.163.com/price/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch  = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);
        cfFetch.thenAcceptAsync((result)->{
            System.out.println("price:"+result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);


    }
    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        if("中国石油sina".equals(name))
        return "601857";
        if("中国石油163".equals(name))
            return "601858";
        return null;
    }

    static Double fetchPrice(String code, String url) {
        System.out.println("query price from " + url + "...");
        //try {
        //    Thread.sleep((long) (Math.random() * 100));
        //} catch (InterruptedException e) {
        //}
        //return 5 + Math.random() * 20;
        if("601857".equals(code))
            return 601857D;
        if("601858".equals(code))
            return 601858D;
        return null;
    }
}
