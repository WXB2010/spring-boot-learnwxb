package com.wxb.springbootlearnwxb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync // 开启异步任务
@SpringBootApplication
public class SpringBootLearnwxbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearnwxbApplication.class, args);
    }



}
