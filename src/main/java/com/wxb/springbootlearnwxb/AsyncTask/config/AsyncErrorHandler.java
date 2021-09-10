package com.wxb.springbootlearnwxb.AsyncTask.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;

/**
 * @author wangxianbing
 * @date 2021-05-09 21:52
 */
@Slf4j
@Configuration
public class AsyncErrorHandler extends AsyncConfigurerSupport {
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        AsyncUncaughtExceptionHandler handler = (throwable, method, objects) -> {
            log.error("全局异常捕获", throwable);
        };
        return handler;
    }
}
