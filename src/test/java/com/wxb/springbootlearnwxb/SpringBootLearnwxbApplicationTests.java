package com.wxb.springbootlearnwxb;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

@SpringBootTest
class SpringBootLearnwxbApplicationTests {

    @Test
    void contextLoads() {
        long a1 = 12;
        String s1 = a1 + "";                  // 法1：直接加空串
        System.out.println(s1 + 999);
        System.out.println((s1 + 999) instanceof String);


        Long l = null;
        StringUtils.isNoneBlank();
        System.out.println(String.valueOf(l));
        System.out.println(l);
        String s = l.toString();
        System.out.println(s);


    }

    @Test
    void testFuture() {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<String> running_task = executor.submit(() -> {
            try {
                System.out.println("running task");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ruturn task";
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        System.out.println("do something else");  //前面的的 Callable 在其他线程中运行着，可以做一些其他的事情

        try {
            System.out.println(running_task.get());  //等待 future 的执行结果，执行完毕之后打印出来
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {

        } finally {
            executor.shutdown();
        }
    }

    @Test
    void testCompletableFuture(){
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{

            System.out.println("hello");
        });

        try {
            completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("CompletableFuture");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("CompletableFuture");
    }


}
