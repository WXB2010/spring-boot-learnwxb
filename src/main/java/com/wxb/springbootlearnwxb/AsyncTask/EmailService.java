package com.wxb.springbootlearnwxb.AsyncTask;

import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author wangxianbing
 * @date 2021-05-09 21:27
 */

@Slf4j
@Service
public class EmailService {

    /**
     * 异步发送任务
     */
    @SneakyThrows
    @Async("taskExecutor")// 配置多个线程池的时候需要指定使用哪一个
    public void sendEmailAsync() {
        log.info("使用 Spring 异步任务发送邮件示例");

        // 模拟邮件发送耗时
        TimeUnit.SECONDS.sleep(2l);
    }

    /**
     * 带有返回值的异步任务
     */
    @SneakyThrows
    @Async("taskExecutor")
    public Future<String> sendEmailAsyncWithResult(){

        log.info("使用 Spring 异步任务发送邮件，并且获取任务返回结果示例");
        TimeUnit.SECONDS.sleep(2l);

        return AsyncResult.forValue("sendEmailAsyncWithResult");
    }


    /**
     * 带有返回值的异步任务，非阻塞的方式获取返回值
     */
    @SneakyThrows
    @Async
    public ListenableFuture<String> sendEmailAsyncWithListenableFuture(){

        log.info("使用 Spring 异步任务发送邮件，并且获取任务返回结果示例");
        TimeUnit.SECONDS.sleep(2l);

        return AsyncResult.forValue("sendEmailAsyncWithListenableFuture");
    }
}
