package com.wxb.springbootlearnwxb.AsyncTask.controller;

import com.wxb.springbootlearnwxb.AsyncTask.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wangxianbing
 * @date 2021-05-09 21:33
 */
@Slf4j
@RestController
public class RegisterController {

    @Resource
    private EmailService emailService;

    @RequestMapping("register")
    public String register() {
        log.info("注册流程开始");
        emailService.sendEmailAsync();
        return "success";
    }

    @RequestMapping("sendEmailWithResult")
    public String sendEmailWithResult() {

        Future<String> future = emailService.sendEmailAsyncWithResult();
        String result = "fail";
        try {
            result = future.get();
            log.info("阻塞获取结果{}",result);
            log.info("等待result获取完毕后主流程再执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("sendEmailAsyncWithListenableFuture")
    public String sendEmailAsyncWithListenableFuture() {

        ListenableFuture<String> future = emailService.sendEmailAsyncWithListenableFuture();
        final String[] result = {""};
        // 异步回调处理
        future.addCallback(new SuccessCallback<String>() {
            @Override
            public void onSuccess(String s) {
                log.info("异步回调处理返回值");
                result[0] = s;
                log.info("获取结果{}",s);
            }
            // 异常处理
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("异步回调处理异常",throwable);
            }
        });
        log.info("主流程执行");

        return result[0];
    }


}
