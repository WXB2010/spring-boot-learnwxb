package com.wxb.springbootlearnwxb.AsyncTask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author wangxianbing
 * @date 2021-05-09 21:57
 */
@Configuration
public class AsyncConfiguration {

    /**
     * 只要我们配置了这个线程池Bean，Spring 的异步任务都将会使用该线程池执行。
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("task-Executor-");
        executor.setMaxPoolSize(10);
        executor.setCorePoolSize(5);
        executor.setQueueCapacity(200);
        return executor;
    }
}
